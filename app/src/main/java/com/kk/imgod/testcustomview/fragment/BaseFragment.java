package com.kk.imgod.testcustomview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by imgod on 2017/1/3.
 */

public abstract class BaseFragment extends Fragment {
    public View parentView;//父布局

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = LayoutInflater.from(getContext()).inflate(getResLayoutId(), null, false);
        initViews();
        initEvent();
        initValue();
        return parentView;
    }

    /**
     * @return 返回当前界面布局的id
     */
    public abstract int getResLayoutId();

    /**
     * 初始化view
     */
    public abstract void initViews();

    /**
     * 初始化事件
     */
    public abstract void initEvent();

    /**
     * 初始化数据
     */
    public abstract void initValue();
}
