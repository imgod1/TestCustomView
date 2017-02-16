package com.kk.imgod.testcustomview.fragment;

import android.view.View;

import com.kk.imgod.testcustomview.R;
import com.kk.imgod.testcustomview.views.BlurImageView;

/**
 * 测试模糊的ImageView
 */
public class BlurImageViewFragment extends BaseFragment implements View.OnClickListener {
    private BlurImageView blur_main;

    @Override
    public int getResLayoutId() {
        return R.layout.fragment_blur_imageview;
    }

    @Override
    public void initViews() {
        blur_main = (BlurImageView) findViewById(R.id.blur_main);
    }

    @Override
    public void initEvent() {
        blur_main.setOnClickListener(this);
        blur_main.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aaa);
                blur_main.setImageResource(R.drawable.aaa, true);
                return true;
            }
        });
    }

    @Override
    public void initValue() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blur_main:
                if (blur_main.isBlurMode()) {
                    blur_main.showNormal();
                } else {
                    blur_main.showBlur();
                }
                break;
            default:
                break;
        }
    }
}
