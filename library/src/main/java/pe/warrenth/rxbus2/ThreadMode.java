package pe.warrenth.rxbus2;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by warrenth on 2018-01-23.
 */

public enum ThreadMode {

    MAIN_THREAD, SINGLE;

    public static Scheduler getScheduler(SubscriberMethod subscriberMethod) {
        Scheduler scheduler;
        switch (subscriberMethod.getThreadMode()) {
            case SINGLE:
                scheduler = Schedulers.single();
                break;
            default:
                scheduler = AndroidSchedulers.mainThread();
                break;
        }
        return scheduler;
    }
}
