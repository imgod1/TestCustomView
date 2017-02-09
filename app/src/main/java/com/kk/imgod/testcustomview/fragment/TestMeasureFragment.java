package com.kk.imgod.testcustomview.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.kk.imgod.testcustomview.R;
import com.kk.imgod.testcustomview.views.ImgView;

/**
 * 项目名称：TestCustomView
 * 类描述：
 * 创建人：gk
 * 创建时间：2017/2/9 9:53
 * 修改人：gk
 * 修改时间：2017/2/9 9:53
 * 修改备注：
 */
public class TestMeasureFragment extends BaseFragment {
    private ImgView imgview;

    @Override
    public int getResLayoutId() {
        return R.layout.fragment_test_measure;
    }

    @Override
    public void initViews() {
        imgview = (ImgView) findViewById(R.id.imgview);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initValue() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image09);
        imgview.setBitmap(bitmap);
    }
}
