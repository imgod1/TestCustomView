package com.kk.imgod.testcustomview.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.kk.imgod.testcustomview.R;
import com.kk.imgod.testcustomview.views.CustomView;
import com.kk.imgod.testcustomview.views.ProgressView;
import com.kk.imgod.testcustomview.views.ProgressViewLine;

/**
 * 进度视图fragment
 */
public class ProgressViewFragment extends BaseFragment implements View.OnClickListener {

    private ProgressView pv_main;
    private ProgressViewLine pvl_main;
    private Button btn_start;
    private Button btn_pause;
    private Button btn_reset;

    public static final int MSG_LOOP = 0x00;

    private int currentProgress = 0;//进度
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_LOOP:
                    if (!isPause) {//如果当前不是暂停状态
                        if (currentProgress >= 0 && currentProgress <= 100) {
                            currentProgress++;
                            pv_main.updateProgress(currentProgress);
                            pvl_main.updateProgress(currentProgress);
                            handler.sendEmptyMessageDelayed(MSG_LOOP, 1000);
                        }
                    }

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public int getResLayoutId() {
        return R.layout.fragment_progressview;
    }

    @Override
    public void initViews() {
        pv_main = (ProgressView) findViewById(R.id.pv_main);
        pvl_main = (ProgressViewLine) findViewById(R.id.pvl_main);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_reset = (Button) findViewById(R.id.btn_reset);
    }

    @Override
    public void initEvent() {
        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
    }

    @Override
    public void initValue() {
    }

    private boolean isPause = false;//是否是暂停

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                isPause = false;
                handler.sendEmptyMessage(MSG_LOOP);
                break;
            case R.id.btn_pause:
                isPause = true;
                break;
            case R.id.btn_reset:
                currentProgress = 0;
                pv_main.updateProgress(0);
                pvl_main.updateProgress(0);
                break;
            default:
                break;
        }
    }
}
