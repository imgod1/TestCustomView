package com.kk.imgod.testcustomview.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kk.imgod.testcustomview.R;
import com.kk.imgod.testcustomview.fragment.CustomViewFragment;

public class CustomViewActivity extends AppCompatActivity {

    public static final int COMETYPE_TEST_CUSTOM = 0x00;//测试自定义view
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
        initValue();
    }

    private void initValue() {
        currentComeType = getIntent().getIntExtra("currentComeType", currentComeType);
        switch (currentComeType) {
            case 0:
                title = getString(R.string.start_in_custom_view);
                currentFragment = new CustomViewFragment();
                break;
            case 1:
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
}
