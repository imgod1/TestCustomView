package com.kk.imgod.testcustomview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * 项目名称：TestCustomView
 * 类描述：钟表面板
 * 创建人：gk
 * 创建时间：2017/3/30 11:16
 * 修改人：gk
 * 修改时间：2017/3/30 11:16
 * 修改备注：
 */
public class ClockView extends View {
    private int mHeight, mWidth;
    private int padding = 10;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initObject();
    }

    private void initObject() {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 获取宽高参数
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        // 画外圆
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
        canvas.drawCircle(mWidth / 2,
                mHeight / 2, mWidth / 2 - padding, paintCircle);
        // 画刻度
        Paint painDegree = new Paint();
        paintCircle.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            // 区分整点与非整点
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                painDegree.setStrokeWidth(5);
                painDegree.setTextSize(60);
                painDegree.setColor(Color.RED);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2 + padding,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 60 + padding,
                        painDegree);
                String degree = String.valueOf(i);
                //第一个和第四个参数分别要绘制的文本和画笔对象
                //第二个参数是文本左边的位置,y坐标是文本baseline的坐标
                canvas.drawText(degree,
                        mWidth / 2 - painDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 150 + padding,
                        painDegree);

            } else {
                painDegree.setStrokeWidth(3);
                painDegree.setTextSize(40);
                painDegree.setColor(Color.GREEN);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2 + padding,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + padding + 30,
                        painDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - painDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 90 + padding,
                        painDegree);
            }
            //每次旋转一定的次数
            // 通过旋转画布简化坐标运算
            //15=360/24
            canvas.rotate(15, mWidth / 2, mHeight / 2);
        }
        // 画圆心
        Paint paintPointer = new Paint();
        paintPointer.setStrokeWidth(10);
        paintPointer.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth / 2, mHeight / 2, 20, paintPointer);
        // 画指针
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);

        canvas.save();
        //先进行偏移和旋转操作,然后draw
        canvas.translate(mWidth / 2, mHeight / 2);//将画布的挪至中心点
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        Log.e("test", "hour:" + hour + "minute:" + minute);
        float hourRotate = 360 * hour / 24 + (360 / 24 * minute / 60);//如果是30分钟的话 那时针要再走上到下一刻度的一半
        Log.e("test", "hourRotate:" + hourRotate);
        canvas.rotate(hourRotate);
        canvas.drawLine(0, 0, 0, -100, paintHour);
        canvas.restore();

        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);//将画布的挪至中心点
        canvas.rotate(360 * minute / 60);
        canvas.drawLine(0, 0, 0, -200, paintMinute);
        canvas.restore();//将画布重置 和 刚才保存的画面重叠起来
    }
}
