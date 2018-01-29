package pe.warrenth.rxbus2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by warrenth on 2018-01-23.
 */

public class SubscriberMethodFinder {

    public List<SubscriberMethod> getSubscriberMethods(Object subscriber) {
        List<SubscriberMethod> subscriberMethods = new ArrayList<>();
        Method[] methods = subscriber.getClass().getDeclaredMethods();

        for(Method method : methods) {
            if(method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] parametaType = method.getParameterTypes();
                Subscribe subscribeAnnotaion = method.getAnnotation(Subscribe.class);
                ThreadMode threadMode = subscribeAnnotaion.threadMode();
                Class<?> paramType = null;
                if(parametaType != null && parametaType.length > 0) {
                    paramType = parametaType[0];
                } else {
                    paramType = RxBus.EmptyParam.class;
                }
                SubscriberMethod subscriberMethod
                        = new SubscriberMethod(subscriber, paramType, method, threadMode, subscribeAnnotaion.eventTag());
                subscriberMethods.add(subscriberMethod);
            }

        }
        return subscriberMethods;
    }

}
