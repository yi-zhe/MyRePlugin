package com.qihoo360.replugin.sample.demo1;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @author liuyang 2023/4/7 2:15 PM
 */
public class IntentFilterActivity extends ThemeBlackNoTitleBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView.setText("IntentFilterActivity");
    }
}