package com.kk.imgod.testcustomview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kk.imgod.testcustomview.R;
import com.kk.imgod.testcustomview.utils.DensityUtils;

/**
 * 项目名称：TestProgress
 * 类描述：自定义进度条(组合控件)
 * 创建人：gk
 * 创建时间：2016/12/29 10:45
 * 修改人：gk
 * 修改时间：2016/12/29 10:45
 * 修改备注：
 */
public class ProgressView extends LinearLayout {
    private View view_part_completed;
    private TextView txt_progress;
    private View view_part_waiting;

    private LayoutParams completedLayoutParams;
    private LayoutParams waitingLayoutParams;

    //一些属性
    private int lineHeight = 1;
    private int completedColor = Color.GREEN;
    private int waitngColor = Color.GRAY;
    private int progressTextColor = Color.BLUE;
    private int progressTextSize = 12;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_progress, this);
        view_part_completed = findViewById(R.id.view_part_completed);
        txt_progress = (TextView) findViewById(R.id.txt_progress);
        view_part_waiting = findViewById(R.id.view_part_waiting);
        completedLayoutParams = (LayoutParams) view_part_completed.getLayoutParams();
        waitingLayoutParams = (LayoutParams) view_part_waiting.getLayoutParams();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        lineHeight = (int) typedArray.getDimension(R.styleable.ProgressView_lineHeight, lineHeight);
        completedColor = typedArray.getColor(R.styleable.ProgressView_completedColor, completedColor);
        waitngColor = typedArray.getColor(R.styleable.ProgressView_waitngColor, waitngColor);
        progressTextColor = typedArray.getColor(R.styleable.ProgressView_progressTextColor, progressTextColor);
        progressTextSize = (int) typedArray.getDimension(R.styleable.ProgressView_progressTextSize, progressTextSize);
        typedArray.recycle();

        completedLayoutParams.height = lineHeight;
        view_part_completed.setLayoutParams(completedLayoutParams);
        waitingLayoutParams.height = lineHeight;
        view_part_waiting.setLayoutParams(waitingLayoutParams);

        view_part_completed.setBackgroundColor(completedColor);
        view_part_waiting.setBackgroundColor(waitngColor);
        txt_progress.setTextColor(progressTextColor);
        txt_progress.setTextSize(DensityUtils.px2sp(context, progressTextSize));
        updateProgress(0);
    }

    /**
     * 更新进度条
     *
     * @param progress 当前的进度数 0-100
     */
    public void updateProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        }
        if (progress > 100) {
            progress = 100;
        }

        txt_progress.setText(progress + "%");
        completedLayoutParams.weight = progress;
        view_part_completed.setLayoutParams(completedLayoutParams);
        waitingLayoutParams.weight = 100 - progress;
        view_part_waiting.setLayoutParams(waitingLayoutParams);
    }
}
