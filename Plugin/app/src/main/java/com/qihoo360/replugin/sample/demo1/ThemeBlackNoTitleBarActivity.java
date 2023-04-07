package com.qihoo360.replugin.sample.demo1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * @author liuyang 2023/4/6 8:25 PM
 */
public class ThemeBlackNoTitleBarActivity extends Activity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = new LinearLayout(this);
        root.setPadding(30, 30, 30, 30);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        root.setLayoutParams(lp);

        setContentView(root);

        // textView
        textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        textView.setText("Theme: BlackNoTitleBar");
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        textView.setLayoutParams(lp2);
        root.addView(textView);
    }
}
