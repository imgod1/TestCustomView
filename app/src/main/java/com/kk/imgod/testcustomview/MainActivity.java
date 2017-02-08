package com.kk.imgod.testcustomview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.kk.imgod.testcustomview.activity.CustomViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list_item;
    private List<String> titleList = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomViewActivity.actionStart(MainActivity.this, position);
            }
        });
    }

    private void initData() {
        titleList.add(getString(R.string.start_in_custom_view));
        titleList.add(getString(R.string.start_in_custom_progress_view));
        titleList.add(getString(R.string.start_in_color_matrix));
        titleList.add(getString(R.string.start_in_path_effect));
        titleList.add(getString(R.string.start_in_heart_line_path_effect));
        titleList.add(getString(R.string.start_in_wave));
        adapter = new MyAdapter(MainActivity.this, titleList);
        list_item.setAdapter(adapter);
    }

    private void initView() {
        list_item = (ListView) findViewById(R.id.list_item);
        getSupportActionBar().setTitle(R.string.list);
    }

    /**
     * 适配器,
     */
    public static class MyAdapter extends BaseAdapter {

        private Context context;
        private List<String> list;

        public MyAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHoler = null;
            if (null == convertView) {
                viewHoler = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null, false);
                viewHoler.title = (TextView) convertView;
                convertView.setTag(viewHoler);
            } else {
                viewHoler = (ViewHolder) convertView.getTag();
            }
            viewHoler.title.setText((String) getItem(position));
            return convertView;
        }
    }

    public static class ViewHolder {
        TextView title;
    }
}
