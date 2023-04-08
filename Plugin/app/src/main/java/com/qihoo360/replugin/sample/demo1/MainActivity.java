package com.qihoo360.replugin.sample.demo1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.sample.demo1.service.PluginDemoService;

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

        mItems.add(new TestItem("Activity: SingleTop", v -> {
            Intent intent = new Intent(v.getContext(), SingleTopActivity.class);
            v.getContext().startActivity(intent);
        }));

        mItems.add(new TestItem("Activity: SingleInstance", v -> {
            Intent intent = new Intent(v.getContext(), SingleInstanceActivity.class);
            v.getContext().startActivity(intent);
        }));

        mItems.add(new TestItem("Activity: Theme BlackNoTitleBar", v -> {
            Intent intent = new Intent(v.getContext(), ThemeBlackNoTitleBarActivity.class);
            v.getContext().startActivity(intent);
        }));
        mItems.add(new TestItem("Activity: Theme BlackNoTitleBarFullscreen", v -> {
            Intent intent = new Intent(v.getContext(), ThemeBlackNoTitleBarFullscreenActivity.class);
            v.getContext().startActivity(intent);
        }));

        mItems.add(new TestItem("Activity: Theme Dialog", v -> {
            Intent intent = new Intent(v.getContext(), ThemeDialogActivity.class);
            v.getContext().startActivity(intent);
        }));

        mItems.add(new TestItem("Activity: Open by Application", v -> {
            Context app = v.getContext().getApplicationContext();
            Intent intent = new Intent(app, ThemeDialogActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            app.startActivity(intent);
        }));

        mItems.add(new TestItem("Activity: By Intent Filter", v -> {
            Intent intent = new Intent("android.intent.action.demo1");
            intent.addCategory("category_demo");
            v.getContext().startActivity(intent);
        }));

        mItems.add(new TestItem("Activity: By Action", v -> {
            Intent intent = new Intent();
            intent.setAction("com.qihoo360.replugin.sample.demo1.action.theme_fullscreen");
            /*
                若 Intent 中声明 category, manifest 中未声明，则无法找到 Activity;
                若 manifest 中声明 category, Intent 中未声明，则可以找到 Activity;
            */
            intent.addCategory("com.qihoo360.repluginapp.replugin.demo1.CATEGORY1");
            v.getContext().startActivity(intent);
        }));

        mItems.add(new TestItem("Activity: start Host activity(Main) NOT WORKING", v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(RePlugin.getHostContext().getPackageName(), "com.qihoo360.replugin.sample.host.MainActivity"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.this.startActivity(intent);
        }));


        mItems.add(new TestItem("Activity: Task Affinity", v -> {
            Intent intent = new Intent(v.getContext(), TAActivity.class);
            v.getContext().startActivity(intent);
        }));

        mItems.add(new TestItem("Broadcast: Send (to All)", v -> {
            Intent intent = new Intent();
            intent.setAction("com.qihoo360.repluginapp.replugin.receiver.ACTION1");
            intent.putExtra("name", "jerry");
            v.getContext().sendBroadcast(intent);
        }));

        mItems.add(new TestItem("Service: Start (at :p0 process)", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PluginDemoService.class);
                intent.setAction("action1");
                v.getContext().startService(intent);
            }
        }));

        mItems.add(new TestItem("Service: Implicit Start (at :UI process)", v -> {
            Intent intent = new Intent();
            intent.setPackage("com.qihoo360.replugin.sample.demo1");
            intent.setAction("com.qihoo360.replugin.sample.demo1.action.XXXX");
            v.getContext().startService(intent);
        }));

        mItems.add(new TestItem("Provider: Query (at UI process)", v -> {
            Uri uri = Uri.parse("content://com.qihoo360.replugin.sample.demo1.provider/" + "test");

            ContentValues cv = new ContentValues();
            cv.put("name", "RePlugin Team");
            cv.put("address", "beijing");

            Uri urii = v.getContext().getContentResolver().insert(uri, cv);
            Log.d("a4", "result=" + urii);
            if (urii != null) {
                Toast.makeText(v.getContext(), urii.toString(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "null", Toast.LENGTH_SHORT).show();
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
