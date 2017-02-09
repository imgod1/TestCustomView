package com.kk.imgod.testcustomview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 项目名称：TestCustomView
 * 类描述：
 * 创建人：gk
 * 创建时间：2017/2/8 15:58
 * 修改人：gk
 * 修改时间：2017/2/8 15:58
 * 修改备注：
 */
public class LayerView extends View {
    private Paint mPaint;
    private int mViewWidth;
    private int mViewHeight;

    public LayerView(Context context) {
        this(context, null);
    }

    public LayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //----------------

       /*
     * 保存并裁剪画布填充绿色
     */
        int saveID1 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 300, mViewHeight / 2F - 300, mViewWidth / 2F + 300, mViewHeight / 2F + 300);
        canvas.drawColor(Color.YELLOW);

    /*
     * 保存并裁剪画布填充绿色
     */
        int saveID2 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200, mViewWidth / 2F + 200, mViewHeight / 2F + 200);
        canvas.drawColor(Color.GREEN);

    /*
     * 保存画布并旋转后绘制一个蓝色的矩形
     */
        int saveID3 = canvas.save(Canvas.MATRIX_SAVE_FLAG);

        // 旋转画布
        canvas.rotate(5);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100, mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);

        mPaint.setColor(Color.CYAN);
        canvas.drawRect(mViewWidth / 2F, mViewHeight / 2F, mViewWidth / 2F + 200, mViewHeight / 2F + 200, mPaint);
    }
}
