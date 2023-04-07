package com.qihoo360.replugin.sample.demo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private final List<TestItem> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initData();

        ListView lv = findViewById(R.id.list_view);
        lv.setAdapter(new TestAdapter());
    }

    private void initData() {
        // =========
        // Activity
        // =========
        mItems.add(new TestItem("Jump 2 Host", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开宿主Activity
                Intent intent = new Intent();
                intent.setClassName("com.qihoo360.replugin.sample.host", "com.qihoo360.replugin.sample.host.PluginFragmentActivity");
                v.getContext().startActivity(intent);
            }
        }));

        mItems.add(new TestItem("start plugin Activity: Standard", v -> {
            Intent intent = new Intent(v.getContext(), StandardActivity.class);
            v.getContext().startActivity(intent);
        }));

        mItems.add(new TestItem("Activity: Theme BlackNoTitleBar", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ThemeBlackNoTitleBarActivity.class);
                v.getContext().startActivity(intent);
            }
        }));
        mItems.add(new TestItem("Activity: Theme BlackNoTitleBarFullscreen", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ThemeBlackNoTitleBarFullscreenActivity.class);
                v.getContext().startActivity(intent);
            }
        }));
    }

    private class TestAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public TestItem getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new Button(MainActivity.this);
            }

            TestItem item = getItem(position);
            ((Button) convertView).setText(item.title);
            convertView.setOnClickListener(item.mClickListener);
            return convertView;
        }
    }
}
