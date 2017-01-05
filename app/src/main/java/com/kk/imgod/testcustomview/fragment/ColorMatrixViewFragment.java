package com.kk.imgod.testcustomview.fragment;

import android.graphics.ColorMatrix;

import com.kk.imgod.testcustomview.R;
import com.kk.imgod.testcustomview.views.ColorMatrixView;
import com.kk.imgod.testcustomview.views.CustomView;

/**
 * http://blog.csdn.net/aigestudio/article/details/41316141
 * 色彩矩阵
 */
public class ColorMatrixViewFragment extends BaseFragment {

    private ColorMatrixView cmv_1;
    private ColorMatrixView cmv_2;
    private ColorMatrixView cmv_3;
    private ColorMatrixView cmv_4;
    private ColorMatrixView cmv_5;
    private ColorMatrixView cmv_6;

    @Override
    public int getResLayoutId() {
        return R.layout.fragment_colormatrixview;
    }

    @Override
    public void initViews() {
        cmv_1 = (ColorMatrixView) findViewById(R.id.cmv_1);
        cmv_2 = (ColorMatrixView) findViewById(R.id.cmv_2);
        cmv_3 = (ColorMatrixView) findViewById(R.id.cmv_3);
        cmv_4 = (ColorMatrixView) findViewById(R.id.cmv_4);
        cmv_5 = (ColorMatrixView) findViewById(R.id.cmv_5);
        cmv_6 = (ColorMatrixView) findViewById(R.id.cmv_6);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initValue() {
        ColorMatrix colorMatrix1 = new ColorMatrix(new float[]{//1为保持原图的RGB值
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        });
        cmv_1.setColorFilter(colorMatrix1);

        ColorMatrix colorMatrix2 = new ColorMatrix(new float[]{
                0.5f, 0, 0, 0, 0,
                0, 0.5f, 0, 0, 0,
                0, 0, 0.5f, 0, 0,
                0, 0, 0, 1, 0,
        });
        cmv_2.setColorFilter(colorMatrix2);

        ColorMatrix colorMatrix3 = new ColorMatrix(new float[]{
                0.33f, 0.59f, 0.11f, 0, 0,
                0.33f, 0.59f, 0.11f, 0, 0,
                0.33f, 0.59f, 0.11f, 0, 0,
                0, 0, 0, 1, 0,
        });
        cmv_3.setColorFilter(colorMatrix3);

        ColorMatrix colorMatrix4 = new ColorMatrix(new float[]{
                -1, 0, 0, 1, 1,
                0, -1, 0, 1, 1,
                0, 0, -1, 1, 1,
                0, 0, 0, 1, 0,
        });
        cmv_4.setColorFilter(colorMatrix4);

        ColorMatrix colorMatrix5 = new ColorMatrix(new float[]{
                0, 0, 1, 0, 0,
                0, 1, 0, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
        });
        cmv_5.setColorFilter(colorMatrix5);

        ColorMatrix colorMatrix6 = new ColorMatrix(new float[]{
                1.5F, 1.5F, 1.5F, 0, -1,
                1.5F, 1.5F, 1.5F, 0, -1,
                1.5F, 1.5F, 1.5F, 0, -1,
                0, 0, 0, 1, 0,
        });
        cmv_6.setColorFilter(colorMatrix6);
    }

}
