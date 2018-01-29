package pe.warrenth.samplerxbus;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import pe.warrenth.rxbus2.RxBus;
import pe.warrenth.rxbus2.Subscribe;
import pe.warrenth.samplerxbus.event.EventType;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;

    private TextView mTextContent;
    private Button mBtnLeft, mBtnAll, mBtnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);

        setContentView(R.layout.activity_main);
        initView();
        initDrawerLayout();
    }

    private void initView() {
        mTextContent = findViewById(R.id.text_middel);
        mBtnLeft = findViewById(R.id.btn_send_left);
        mBtnAll = findViewById(R.id.btn_send_all);
        mBtnRight = findViewById(R.id.btn_send_right);
        mBtnLeft.setOnClickListener(this);
        mBtnAll.setOnClickListener(this);
        mBtnRight.setOnClickListener(this);
    }


    private void initDrawerLayout() {
        mDrawerLayout =  findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        findViewById(R.id.btn_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        findViewById(R.id.btn_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                    mDrawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_left:
                RxBus.get().send(EventType.TAG.LEFT_MENU, new ArrayList<>());
                break;
            case R.id.btn_send_all:
                RxBus.get().send(EventType.TAG.ALL, "Send ALL");
                break;
            case R.id.btn_send_right:
                RxBus.get().send(EventType.TAG.RIGHT_MENU, 2018);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unResister(this);
    }

    @Subscribe(eventTag = EventType.TAG.MIDDLE)
    public void receiveMiddle(String receivedMsg) {
        mTextContent.append(receivedMsg + "\n");
    }

    @Subscribe(eventTag = EventType.TAG.ALL)
    public void receiveAll(String allMsg) {
        mTextContent.append(allMsg + "\n");
    }
}
