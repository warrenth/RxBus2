package pe.warrenth.rxbus;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by warrenth on 2018-01-23.
 */

public enum ThreadMode {

    MAIN_THREAD,  NEW_THREAD;

    public static Scheduler getScheduler(SubscriberMethod subscriberMethod) {
        Scheduler scheduler;
        switch (subscriberMethod.getThreadMode()) {
            case MAIN_THREAD:
                scheduler = AndroidSchedulers.mainThread();
                break;
            case NEW_THREAD:
                scheduler = Schedulers.newThread();
                break;
            default:
                scheduler = AndroidSchedulers.mainThread();
                break;
        }
        return scheduler;
    }
}
