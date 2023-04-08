package com.qihoo360.replugin.sample.demo1.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class PluginDemoService extends Service {

    public static final String TAG = "demo.service";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "PluginDemoService1 onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        Toast.makeText(this, "PluginDemoService1.action = " + action, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "PluginDemoService1 onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
