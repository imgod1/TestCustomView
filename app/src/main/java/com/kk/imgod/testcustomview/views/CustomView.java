package com.kk.imgod.testcustomview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.kk.imgod.testcustomview.R;

/**
 * 博客地址:http://blog.csdn.net/aigestudio/article/details/41212583
 */
public class CustomView extends View implements Runnable {
    private int maxRadius = 200;//最大半径,这里的200是指200px
    private int radius = maxRadius;//圆的半径
    private Paint paint;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取到我们自定义的一些属性
        //第一个参数是我们的AttributeSet,第二个参数是我们在res/values目录下的attrs里面定义的自定义控件的名字了
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        maxRadius = (int) typedArray.getDimension(R.styleable.CustomView_maxRadiu, maxRadius);
        typedArray.recycle();//用完之后记得及时回收
        radius = maxRadius;
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);//设置抗锯齿,这样画出来的线段更加平滑
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
    }

    @Override
    public void run() {
        while (true) {
            if (radius <= maxRadius) {//如果小于最大值那就累加并重新绘制视图
                radius += 10;
                postInvalidate();
            } else {
                radius = 10;//如果大于等于最大值了.那就从10开始继续算起
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
