package com.example.myapplication.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import java.lang.StringBuilder


class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(ctx: Context?, intent: Intent?) {

        val statusOn : Boolean =
            Settings.Global.getInt(ctx?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

        if (ctx != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val importance = NotificationManager.IMPORTANCE_DEFAULT

                val channel = NotificationChannel(NotificationUtil.CHANNEL_ID, NotificationUtil.CHANNEL_ID, importance).apply {
                    description = "description"
                }

                val notificationManager: NotificationManager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }

            if (statusOn) {
                NotificationUtil.showSimpleNotification(ctx, "AIR PLANE STATUS - ON")
            } else {
                NotificationUtil.showSimpleNotification(ctx, "AIR PLANE STATUS - OFF")
            }
        }

    }
}