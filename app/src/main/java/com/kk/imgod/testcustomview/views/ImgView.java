package com.kk.imgod.testcustomview.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 项目名称：TestCustomView
 * 类描述：
 * 创建人：gk
 * 创建时间：2017/2/9 9:50
 * 修改人：gk
 * 修改时间：2017/2/9 9:50
 * 修改备注：
 */
public class ImgView extends View {
    private Bitmap mBitmap;

    public ImgView(Context context) {
        this(context, null);
    }

    public ImgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //宽度
        int resultWidth = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {//如果为精准mode,那真实宽度就是测量到的宽度:指定了宽高或者match_parent
            resultWidth = widthSize;
            Log.e("test", "当前为EXACTLY模式");
        } else if (widthMode == MeasureSpec.AT_MOST) {//wrap_content
            resultWidth = Math.min(mBitmap.getWidth(), widthSize);
            Log.e("test", "当前为AT_MOST模式");
        } else {
            resultWidth = mBitmap.getWidth();
            Log.e("test", "当前为UNSPECIFIED模式");
        }

        //高度
        int resultHeight = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            resultHeight = heightSize;
        } else {
            resultHeight = mBitmap.getHeight();
            if (heightMode == MeasureSpec.AT_MOST) {
                resultHeight = Math.min(mBitmap.getHeight(), heightSize);
            }
        }
        Log.e("test", "getPaddingLeft:" + getPaddingLeft());
        resultWidth += getPaddingLeft() + getPaddingRight();
        resultHeight += getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(resultWidth, resultHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null != mBitmap) {
            canvas.drawBitmap(mBitmap, getPaddingLeft(), getPaddingTop(), null);
        }
    }
}
