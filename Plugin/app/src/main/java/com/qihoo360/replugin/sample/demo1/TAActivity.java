package com.qihoo360.replugin.sample.demo1;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * taskAffinity 用于指定Activity属于哪个Task, 默认属于包名Task
 * taskAffinity 的使用配合 Activity 的启动模式 LaunchMode 来一起使用
 * singleTask singleInstance
 */
public class TAActivity extends ThemeBlackNoTitleBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView.setText("TAActivity");
    }

}
