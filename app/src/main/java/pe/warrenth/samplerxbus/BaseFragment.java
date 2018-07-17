package pe.warrenth.samplerxbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import pe.warrenth.rxbus2.OnRxBusFindDataInterface;
import pe.warrenth.rxbus2.RxBus;
import pe.warrenth.rxbus2.RxBusHelper;
import pe.warrenth.rxbus2.Subscribe;
import pe.warrenth.samplerxbus.event.EventType;

/**
 * Created by 152317 on 2018-02-26.
 */

public class BaseFragment extends Fragment implements OnRxBusFindDataInterface{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this, RxBusHelper.getRegistClass(this, BaseFragment.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unResister(this, RxBusHelper.getRegistClass(this, BaseFragment.class));
    }

    @Subscribe(eventTag = EventType.TAG.ALL)
    public void receiveAll2() {  // BaseClass
        Log.d("RXBUS2", "receiveAll()");
    }

    @Override
    public Object getObject() {
        return this;
    }

    @Override
    public int getHashCode() {
        return System.identityHashCode(this);
    }
}
