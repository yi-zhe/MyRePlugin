package com.qihoo360.replugin.sample.demo1.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

public class PluginDemo1Receiver extends BroadcastReceiver {
    public static final String ACTION = "com.qihoo360.repluginapp.replugin.receiver.ACTION1";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            if (action.equals(ACTION)) {
                String name = intent.getStringExtra("name");
                Toast.makeText(context, "Plugin1-action: " + action + ", name = " + name, Toast.LENGTH_LONG).show();
            }
        }
    }
}
