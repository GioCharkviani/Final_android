package com.example.myapplication.activities

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.myapplication.R

object NotificationUtil {
    const val CHANNEL_ID = "CHANNEL"

    fun showSimpleNotification(ctx: Context, text: String) {
        val notification = NotificationCompat.Builder(ctx, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_map_24)
            .setContentTitle("air_plane")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val manager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification.build())
    }
}