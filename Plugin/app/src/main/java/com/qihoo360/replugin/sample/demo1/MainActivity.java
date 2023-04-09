package com.qihoo360.replugin.sample.demo1;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.sample.demo1.service.PluginDemoService;
import com.qihoo360.replugin.sample.demo2.IDemo2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private final List<TestItem> mItems = new ArrayList<>();

    private static final int REQUEST_CODE_DEMO2 = 0x021;
    private static final int RESULT_CODE_DEMO2 = 0x022;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_DEMO2 && resultCode == RESULT_CODE_DEMO2) {
            Toast.makeText(this, data.getStringExtra("data"), Toast.LENGTH_SHORT).show();
        }
    }

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


        // FileProvider
        mItems.add(new TestItem("FileProvider Activity", v -> {
            Intent intent = new Intent(v.getContext(), FileProviderActivity.class);
            v.getContext().startActivity(intent);
        }));

        // Notification
        mItems.add(new TestItem("Send Notification(Not Working)", v -> NotifyUtils.sendNotification(v.getContext().getApplicationContext())));

        mItems.add(new TestItem("Activity: AppCompat (to Demo2)", v -> RePlugin.startActivity(v.getContext(), new Intent(), "demo2", "com.qihoo360.replugin.sample.demo2.activity.appcompat.AppCompatActivityDemo")));

        mItems.add(new TestItem("Activity: DataBinding (to Demo2)", v -> RePlugin.startActivity(v.getContext(), new Intent(), "demo2", "com.qihoo360.replugin.sample.demo2.databinding.DataBindingActivity")));

        mItems.add(new TestItem("Activity: startForResult (to Demo2)", v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("demo2", "com.qihoo360.replugin.sample.demo2.activity.for_result.ForResultActivity"));
            MainActivity.this.startActivityForResult(intent, REQUEST_CODE_DEMO2);
            // 也可以这么用
            // RePlugin.startActivityForResult(MainActivity.this, intent, REQUEST_CODE_DEMO2);
        }));

        mItems.add(new TestItem("Activity: By Action (to Demo2)", v -> {
            Intent intent = new Intent("com.qihoo360.replugin.sample.demo2.action.theme_fullscreen_2");
            RePlugin.startActivity(v.getContext(), intent, "demo2", null);
        }));

        mItems.add(new TestItem("Use Demo2 Method: Reflection (Recommend)", v -> {
            // 这是RePlugin的推荐玩法：反射调用Demo2，这样"天然的"做好了"版本控制"
            // 避免出现我们当年2013年的各种问题
            ClassLoader cl = RePlugin.fetchClassLoader("demo2");
            if (cl == null) {
                Toast.makeText(v.getContext(), "Not install Demo2", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Class clz = cl.loadClass("com.qihoo360.replugin.sample.demo2.MainApp");
                Method m = clz.getDeclaredMethod("helloFromDemo1", Context.class, String.class);
                m.invoke(null, v.getContext(), "Demo1");
            } catch (Exception e) {
                // 有可能Demo2根本没有这个类，也有可能没有相应方法（通常出现在"插件版本升级"的情况）
                Toast.makeText(v.getContext(), "", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }));
        mItems.add(new TestItem("Resources: Use demo2's layout", v -> {
            LinearLayout contentView = RePlugin.fetchViewByLayoutName("demo2", "from_demo1", null);
            if (contentView == null) {
                Toast.makeText(v.getContext(), "from_demo1 Not Found", Toast.LENGTH_SHORT).show();
                return;
            }
            Dialog d = new Dialog(v.getContext());
            d.setContentView(contentView);
            d.show();
        }));

        mItems.add(new TestItem("Binder: Fast-Fetch (to Demo2)", v -> {
            IBinder b = RePlugin.fetchBinder("demo2", "demo2test");
            if (b == null) {
                return;
            }
            IDemo2 demo2 = IDemo2.Stub.asInterface(b);
            try {
                demo2.hello("helloooooooooooo");
            } catch (RemoteException e) {
                e.printStackTrace();
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
