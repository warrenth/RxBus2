package pe.warrenth.samplerxbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import pe.warrenth.rxbus2.RxBus;
import pe.warrenth.rxbus2.Subscribe;
import pe.warrenth.samplerxbus.event.EventType;

/**
 * Created by 152317 on 2017-12-04.
 */

public class LeftMenuFragment extends Fragment {

    private Button mBtnSendMiddle;
    private TextView mTextContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_menu, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unResister(this);
    }

    private void initView(View view) {
        mBtnSendMiddle = view.findViewById(R.id.btn_send_middle);
        mTextContent = view.findViewById(R.id.text_content);
        mBtnSendMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.get().send(EventType.TAG.MIDDLE, "This message came from left fragment");
            }
        });
    }


    @Subscribe(eventTag = EventType.TAG.LEFT_MENU)
    public void receiveLeft(ArrayList arrayList) {
        mTextContent.append("This message came from Middle : " + arrayList + "\n");
    }

    @Subscribe(eventTag = EventType.TAG.ALL)
    public void receiveAll(String allMsg) {
        mTextContent.append(allMsg + "\n");
    }
}
