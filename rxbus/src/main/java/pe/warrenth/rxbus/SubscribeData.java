package pe.warrenth.rxbus;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by 152317 on 2018-01-24.
 */

public class SubscribeData {
    private List<SubscriberMethod> subscriberMethods;
    private List<Disposable> disposables;

    public SubscribeData(List<SubscriberMethod> subscriberMethods, List<Disposable> disposables) {
        this.subscriberMethods = subscriberMethods;
        this.disposables = disposables;
    }

    public List<SubscriberMethod> getSubscriberMethods() {
        return subscriberMethods;
    }

    public List<Disposable> getDisposables() {
        return disposables;
    }
}
