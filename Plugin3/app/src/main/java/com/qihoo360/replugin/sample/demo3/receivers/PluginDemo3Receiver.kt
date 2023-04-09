package com.qihoo360.replugin.sample.demo3.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast

class PluginDemo3Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (!TextUtils.isEmpty(action)) {
            if (action == ACTION) {
                val name = intent.getStringExtra("name")
                Toast.makeText(context, "Plugin3-action: $action, name = $name", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        val ACTION = "com.qihoo360.repluginapp.replugin.receiver.ACTION3"
    }
}