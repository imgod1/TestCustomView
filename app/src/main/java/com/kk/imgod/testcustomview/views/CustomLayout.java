package com.kk.imgod.testcustomview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * 项目名称：TestCustomView
 * 类描述：
 * 创建人：gk
 * 创建时间：2017/2/9 13:52
 * 修改人：gk
 * 修改时间：2017/2/9 13:52
 * 修改备注：
 */
public class CustomLayout extends ViewGroup {
    private int padingLeft;
    private int padingRight;
    private int padingTop;
    private int padingBottom;

    public CustomLayout(Context context) {
        this(context, null);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        padingLeft = getPaddingLeft();
        padingTop = getPaddingTop();
        padingRight = getPaddingRight();
        padingBottom = getPaddingBottom();
        Log.e("test", "pading:" + padingTop);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() > 0) {
            measureChildren(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private int heightSum = 0;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                CustomLayoutParams params = (CustomLayoutParams) child.getLayoutParams();
                child.layout(padingLeft + params.leftMargin, heightSum + padingTop + params.topMargin, child.getMeasuredWidth() + padingLeft + params.leftMargin, child.getMeasuredHeight() + heightSum + padingTop + params.topMargin);//模仿垂直的LinearLayout
                heightSum += child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            }
        }
    }


    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }

    /**
     * 自己类里面的LayoutParams
     */
    public static class CustomLayoutParams extends MarginLayoutParams {


        public CustomLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public CustomLayoutParams(LayoutParams source) {
            super(source);
        }

        public CustomLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public CustomLayoutParams(int width, int height) {
            super(width, height);
        }
    }

}
