package com.kk.imgod.testcustomview.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.kk.imgod.testcustomview.R;
import com.kk.imgod.testcustomview.fragment.ColorMatrixViewFragment;
import com.kk.imgod.testcustomview.fragment.CustomViewFragment;
import com.kk.imgod.testcustomview.fragment.HeartLinePathEffectFragment;
import com.kk.imgod.testcustomview.fragment.PathEffectFragment;
import com.kk.imgod.testcustomview.fragment.ProgressViewFragment;
import com.kk.imgod.testcustomview.fragment.WaveFragment;

public class CustomViewActivity extends AppCompatActivity {

    public static final int COMETYPE_TEST_CUSTOM = 0;//测试自定义view
    public static final int COMETYPE_TEST_PROGRESS = 1;//测试自定义progressview
    public static final int COMETYPE_TEST_COLORMATRIX = 2;//测试ColorMatrix
    public static final int COMETYPE_TEST_PATHEFFECT = 3;//测试PathEffect
    public static final int COMETYPE_TEST_HEART_LINE_PATHEFFECT = 4;//测试心电图PathEffect
    public static final int COMETYPE_TEST_WAVE = 5;//测试杯水效果
    private int currentComeType = COMETYPE_TEST_CUSTOM;
    private Fragment currentFragment;
    private String title;

    /**
     * 快速跳转的方法
     *
     * @param context  上下文
     * @param comeType 跳转方式
     */
    public static void actionStart(Context context, int comeType) {
        Intent intent = new Intent(context, CustomViewActivity.class);
        intent.putExtra("currentComeType", comeType);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//这句代码仅仅把回退按钮展示出来
        initValue();
    }

    private void initValue() {
        currentComeType = getIntent().getIntExtra("currentComeType", currentComeType);
        switch (currentComeType) {
            case COMETYPE_TEST_CUSTOM:
                title = getString(R.string.start_in_custom_view);
                currentFragment = new CustomViewFragment();
                break;
            case COMETYPE_TEST_PROGRESS:
                title = getString(R.string.start_in_custom_progress_view);
                currentFragment = new ProgressViewFragment();
                break;
            case COMETYPE_TEST_COLORMATRIX:
                title = getString(R.string.start_in_color_matrix);
                currentFragment = new ColorMatrixViewFragment();
                break;
            case COMETYPE_TEST_PATHEFFECT:
                title = getString(R.string.start_in_path_effect);
                currentFragment = new PathEffectFragment();
                break;
            case COMETYPE_TEST_HEART_LINE_PATHEFFECT:
                title = getString(R.string.start_in_heart_line_path_effect);
                currentFragment = new HeartLinePathEffectFragment();
                break;
            case COMETYPE_TEST_WAVE:
                title = getString(R.string.start_in_wave);
                currentFragment = new WaveFragment();
                break;
            default:
                break;
        }
        getSupportActionBar().setTitle(title);
        addFragment2Content();
    }

    private void addFragment2Content() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.flayout_content, currentFragment);
        transaction.commit();
    }

    //为回退按钮(home)添加响应事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
