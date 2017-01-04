package com.kk.imgod.testcustomview.fragment;

import com.kk.imgod.testcustomview.R;
import com.kk.imgod.testcustomview.views.CustomView;

public class CustomViewFragment extends BaseFragment {

    private CustomView custom_main;
    private Thread thread;

    @Override
    public int getResLayoutId() {
        return R.layout.fragment_customview;
    }

    @Override
    public void initViews() {
        custom_main = (CustomView) parentView.findViewById(R.id.custom_main);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initValue() {
        thread = new Thread(custom_main);
        thread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != thread) {
            try {
                thread.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
