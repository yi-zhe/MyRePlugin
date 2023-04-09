package com.qihoo360.replugin.sample.demo3.activity.theme

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

class ThemeBlackNoTitleBarActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this)
        root.setPadding(30, 30, 30, 30)
        val lp = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        root.layoutParams = lp

        setContentView(root)

        // textView
        val textView = TextView(this)
        textView.gravity = Gravity.CENTER
        textView.textSize = 30f
        textView.text = "Theme3: BlackNoTitleBar"
        val lp2 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        textView.layoutParams = lp2
        root.addView(textView)
    }
}