package com.example.quickcash;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.service.notification.StatusBarNotification;

import androidx.core.app.NotificationCompat;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.quickcash.Notification.NotificationSender;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NotificationSenderTest {

    private NotificationSender notificationSender;
    private NotificationManager notificationManager;
    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        notificationSender = new NotificationSender(context);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * Tests the {@link NotificationSender#sendNotification(String, String)} method by sending a new
     * notification with the specified title and content, and verifying that the notification is
     * correctly displayed on the user's device.
     */
    @Test
    public void testSendNotification() {
        // Define the title and content of the notification to be sent
        String title = "New Job Alert";
        String content = "A new job is available";

        // Send the notification
        notificationSender.sendNotification(title, content);

        // Search for the notification in the notification manager
        Notification foundNotification = null;
        for (StatusBarNotification statusBarNotification : notificationManager.getActiveNotifications()) {
            Notification notification = statusBarNotification.getNotification();
            if (title.equals(notification.extras.getCharSequence(NotificationCompat.EXTRA_TITLE).toString()) &&
                    content.equals(notification.extras.getCharSequence(NotificationCompat.EXTRA_TEXT).toString())) {
                foundNotification = notification;
                break;
            }
        }

        // Assert that the notification was found
        Assert.assertNotNull(foundNotification);
    }

}

