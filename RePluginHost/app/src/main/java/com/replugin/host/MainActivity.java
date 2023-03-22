package com.replugin.host;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.qihoo360.replugin.RePlugin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start_demo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 刻意以“包名”来打开
                RePlugin.startActivity(MainActivity.this, RePlugin.createIntent("com.replugin.plugin", "com.replugin.plugin.MainActivity"));
            }
        });
    }
}