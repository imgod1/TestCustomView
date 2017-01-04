package com.kk.imgod.testcustomview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.kk.imgod.testcustomview.R;


/**
 * 项目名称：TestProgress
 * 类描述：自定义进度条(自定义控件)
 * 创建人：gk
 * 创建时间：2016/12/29 10:45
 * 修改人：gk
 * 修改时间：2016/12/29 10:45
 * 修改备注：
 */
public class ProgressViewLine extends View {
    private int progressPadding;
    private Paint paint;
    private Rect mBound;
    //一些属性
    private int lineHeight = 1;
    private int completedColor = Color.GREEN;
    private int waitngColor = Color.GRAY;
    private int progressTextColor = Color.BLUE;
    private int progressTextSize = 12;

    public ProgressViewLine(Context context) {
        this(context, null);
    }

    public ProgressViewLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressViewLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        progressPadding = 4;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        lineHeight = (int) typedArray.getDimension(R.styleable.ProgressView_lineHeight, lineHeight);
        completedColor = typedArray.getColor(R.styleable.ProgressView_completedColor, completedColor);
        waitngColor = typedArray.getColor(R.styleable.ProgressView_waitngColor, waitngColor);
        progressTextColor = typedArray.getColor(R.styleable.ProgressView_progressTextColor, progressTextColor);
        progressTextSize = (int) typedArray.getDimension(R.styleable.ProgressView_progressTextSize, progressTextSize);
        typedArray.recycle();

        paint = new Paint();
        paint.setStrokeWidth(lineHeight);
        paint.setTextSize(progressTextSize);
        mBound = new Rect();
        updateProgress(0);
    }

    private int currentProgress = 0;

    /**
     * 更新进度条
     *
     * @param progress 当前的进度数 0-100
     */
    public void updateProgress(int progress) {
        currentProgress = progress;
        if (currentProgress < 0) {
            currentProgress = 0;
        }
        if (currentProgress > 100) {
            currentProgress = 100;
        }
        invalidate();//重绘视图
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final String progressInfo = currentProgress + "%";
        float[] textWidths = new float[progressInfo.length()];
        paint.getTextWidths(progressInfo, textWidths);//得到这个字符串里面,每一个字符的长度.然后保存到一个数组里面
        float textTotalWidth = calcTotal(textWidths);//数组里面的值循环相加.就得到了该字符串总的长度
        float cellWidth = (getWidth() - (textTotalWidth + progressPadding * 2)) / 100;

        paint.setAntiAlias(true);//抗锯齿,如果没有这句话.画出来的线段看起来并不是太直
        paint.setColor(completedColor);
        canvas.drawLine(0f, getHeight() / 2, cellWidth * currentProgress, getHeight() / 2, paint);
        paint.setColor(progressTextColor);
        paint.setTextSize(progressTextSize);
        paint.getTextBounds(progressInfo, 0, progressInfo.length(), mBound);
        int baseLineHeight1 = getHeight() / 2 + mBound.height() / 2;//文字baseline的高度,这样的计算方法虽然看上起挺居中的.但是实际还是靠下了一点点(几乎可以忽略)
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int baseLineHeight2 = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;//这样的居中.才是真的居中
        canvas.drawText(progressInfo, cellWidth * currentProgress + progressPadding, baseLineHeight2, paint);//drawtext的第三个参数y,是说文字baseline的y坐标
        paint.setColor(waitngColor);
        canvas.drawLine(getWidth() - cellWidth * (100 - currentProgress), getHeight() / 2, getWidth(), getHeight() / 2, paint);
    }

    private float calcTotal(float[] arrFloat) {
        float result = 0f;
        for (float f : arrFloat) {
            result += f;
        }
        return result;
    }

}
