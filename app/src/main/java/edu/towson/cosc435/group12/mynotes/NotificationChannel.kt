package edu.towson.cosc435.group12.mynotes

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

fun createNotification(ctx: Context, projectName: String): Notification {
    createNotificationChannel(ctx)
    val builder = NotificationCompat.Builder(ctx, "edu.towson.cosc435.group12.mynotes.channel")
        .setSmallIcon(R.drawable.baseline_note_24)
        .setContentTitle("New Project Created")
        .setContentText(projectName)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    return builder.build()
}

fun createNotificationChannel(ctx: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Project Creation"
        val descriptionText = "Notifies when a new project is created"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(
            "edu.towson.cosc435.group12.mynotes.channel",
            name,
            importance
        ).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
