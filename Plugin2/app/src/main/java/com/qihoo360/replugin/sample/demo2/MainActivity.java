package com.qihoo360.replugin.sample.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setPadding(30, 30, 30, 30);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        root.setLayoutParams(lp);

        setContentView(root);

        // textView
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        textView.setText("MainActivity of Plugin-Demo-2");
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        textView.setLayoutParams(lp2);
        root.addView(textView);

    }
}
