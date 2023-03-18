package com.example.quickcash.Notification;

import androidx.core.app.NotificationCompat;

import com.example.quickcash.R;

public interface NotificationCreator {
    public void sendNotification(String title, String content);
}
