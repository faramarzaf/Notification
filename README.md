# Notification  

<p align="center">
  <img src="https://developer.android.com/images/ui/notifications/notification-drawer_2x.png" height="300" width="300" /> 
</p>

A notification is a message that Android displays outside your app's UI to provide the user with reminders, communication from other people, or other timely information from your app.  
Users can tap the notification to open your app or take an action directly from the notification.  


<p align="center">
  <img src="https://developer.android.com/images/ui/notifications/notification-callouts_2x.png" height="120" width="350" /> 
</p>

The most common parts of a notification are indicated in figure 7 as follows:  

1- Small icon: This is required and set with `setSmallIcon()`.  

2- App name: This is provided by the system.  

3- Time stamp: This is provided by the system but you can override with `setWhen()` or hide it with `setShowWhen(false)`.  

4- Large icon: This is optional (usually used only for contact photos; do not use it for your app icon) and set with `setLargeIcon()`.  

5- Title: This is optional and set with `setContentTitle()`.  

6- Text: This is optional and set with `setContentText()`.  

**Expandable notification**  

<p align="center">
  <img src="https://developer.android.com/images/ui/notifications/notification-expanded_2x.png" height="270" width="300" /> 
</p>

By default, the notification's text content is truncated to fit on one line. If you want your notification to be longer, you can enable a larger text area that's expandable by applying an additional template.  


**Set the notification content**

To get started, you need to set the notification's content and channel using a `NotificationCompat.Builder` object. The following example shows how to create a notification with the following:  

A small icon, set by `setSmallIcon()`. This is the only user-visible content that's required.  
A title, set by `setContentTitle()`.   
The body text, set by `setContentText()`.  
The notification priority, set by `setPriority()`.  
The priority determines how intrusive the notification should be on Android *7.1* and lower.  
- For *Android 8.0* and higher, you must instead set the channel importance—shown in the next section.   

```java
NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.notification_icon)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
```


If you want your notification to be longer, you can enable an expandable notification by adding a style template with `setStyle()`.    
For example, the following code creates a larger text area:  

```java
NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.notification_icon)
        .setContentTitle("My notification")
        .setContentText("Much longer text that cannot fit one line...")
        .setStyle(new NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
```


**Create a channel and set the importance**  

Before you can deliver the notification on *Android 8.0* and higher, you must register your app's notification channel with the system by passing an instance of NotificationChannel to `createNotificationChannel()`. 
So the following code is blocked by a condition on the *SDK_INT* version:

```java
private void createNotificationChannel() {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
```









