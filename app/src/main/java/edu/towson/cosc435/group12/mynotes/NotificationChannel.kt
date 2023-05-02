package edu.towson.cosc435.group12.mynotes

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

    fun createNotification(ctx: Context): Notification {
        createNotificationChannel(ctx)
        val builder = NotificationCompat.Builder(ctx, "edu.towson.cosc435.group12.mynotes.channel")
            .setSmallIcon(R.drawable.baseline_flip_to_back_24)
            .setContentTitle("Image Download")
            .setContentText("Downloading image...")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        return builder.build()
    }

    fun createNotificationChannel(ctx: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "name"
            val descriptionText = "descriptionText"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("edu.towson.cosc435.group12.mynotes.channel", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
