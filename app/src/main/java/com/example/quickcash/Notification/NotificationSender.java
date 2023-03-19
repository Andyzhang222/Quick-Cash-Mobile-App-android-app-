package com.example.quickcash.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.quickcash.R;

public class NotificationSender {
    // Define the notification channel properties
    private static final String CHANNEL_ID = "channel_1";
    private static final String CHANNEL_NAME = "New Job";
    private static final String CHANNEL_DESCRIPTION = "Alert for employee";

    private final NotificationManager notificationManager;
    private final Context context;


    /**
     * Creates a new `NotificationSender` instance for sending notifications in the given context.
     *
     * @param context the context in which to send notifications
     */
    public NotificationSender(Context context) {
        this.context = context;
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel();
    }

    /**
     * Creates the notification channel for this app, if necessary.
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(CHANNEL_DESCRIPTION);
            notificationManager.createNotificationChannel(channel);
        }
    }


    /**
     * Sends a notification to the user with the specified title and content.
     *
     * @param title   the title of the notification
     * @param content the content of the notification
     */
    public void sendNotification(String title, String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.notifications_active)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationManager.notify(1, builder.build());
    }

}

