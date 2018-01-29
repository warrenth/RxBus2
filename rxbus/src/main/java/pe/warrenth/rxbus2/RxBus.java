package pe.warrenth.rxbus2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by warrenth on 2018-01-23.
 */

public class RxBus {

    private volatile static RxBus INTANCE;
    private Map<Class<?>, SubscribeData> mSubscribeDataMap = new HashMap<>();
    private final FlowableProcessor<Object> mFlowableProcessoer;

    private final SubscriberMethodFinder mSubscriberMethodFinder;

    public RxBus() {
        mFlowableProcessoer = PublishProcessor.create().toSerialized();
        mSubscriberMethodFinder = new SubscriberMethodFinder();
    }

    public static RxBus get() {
        if(INTANCE == null) {
            synchronized(RxBus.class) {
                if(INTANCE == null) {
                    INTANCE = new RxBus();
                }
            }
        }
        return INTANCE;
    }

    public void register(Object subscriber) {
        List<SubscriberMethod> subscriberMethodsList = mSubscriberMethodFinder.getSubscriberMethods(subscriber);

        List<Disposable> disposableList = new ArrayList<>();

        for(SubscriberMethod subscriberMethod : subscriberMethodsList) {
            Disposable disposable = getDisposable(subscriberMethod);
            if( ! disposableList.contains(disposable)) {
                disposableList.add(disposable);
            }
        }

        setSubscribeData(subscriber.getClass(), subscriberMethodsList, disposableList);
    }

    private void setSubscribeData(Class<?> subClass, List<SubscriberMethod> subscriberMethodsList, List<Disposable> disposableList) {
        SubscribeData subscribeData = mSubscribeDataMap.get(subClass);
        if (subscribeData == null) {
            subscribeData = new SubscribeData(subscriberMethodsList, disposableList);
            mSubscribeDataMap.put(subClass, subscribeData);
        }
    }

    public void unResister(Object subscriber) {
        Class<?> subscriberClass = subscriber.getClass();
        if(mSubscribeDataMap.get(subscriberClass) != null) {
            mSubscribeDataMap.remove(subscriberClass);
        }
    }

    private Disposable getDisposable(final SubscriberMethod subscriberMethod) {
        return mFlowableProcessoer.ofType(EventMessage.class)
                .filter(new Predicate<EventMessage>() {
                    @Override
                    public boolean test(EventMessage eventMessage) throws Exception {
                        return eventMessage.getEventType().equals(subscriberMethod.getEventTag())
                                && subscriberMethod.getParamType().isInstance(eventMessage.getObject());
                    }
                })
                .observeOn(ThreadMode.getScheduler(subscriberMethod))
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        invokeMethod(subscriberMethod, ((EventMessage)o).getObject());
                    }
                });
    }

    private void invokeMethod(SubscriberMethod method, Object object) {
        List<SubscriberMethod> methods = mSubscribeDataMap.get(method.getSubscriber().getClass()).getSubscriberMethods();
        if (methods != null && methods.size() > 0) {
            for (SubscriberMethod subscriberMethod : methods) {
                Subscribe sub = subscriberMethod.method.getAnnotation(Subscribe.class);
                String c = sub.eventTag();
                if (c.equals(method.eventTag) && method.subscriber.equals(subscriberMethod.subscriber) && method.method.equals(subscriberMethod.method)) {
                    subscriberMethod.invoke(object);
                }
            }
        }
    }

    public void send(String eventType) {
        send(eventType, new EmptyParam());
    }

    public void send(String eventType, Object o) {
        if(mFlowableProcessoer.hasSubscribers()) {
            mFlowableProcessoer.onNext(new EventMessage(eventType, o));
        }
    }

    private static class EventMessage {
        private String eventType;
        private Object object;

        public EventMessage(String eventType, Object object) {
            this.eventType = eventType;
            this.object = object;
        }

        public String getEventType() {
            return eventType;
        }

        public Object getObject() {
            return object;
        }
    }

    public static class EmptyParam {

    }
}
