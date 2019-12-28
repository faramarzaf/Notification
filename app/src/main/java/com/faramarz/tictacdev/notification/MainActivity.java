package com.faramarz.tictacdev.notification;

import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSimple, btnDownload, btnExpand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        clickEvents();
    }

    private void bind() {
        btnSimple = findViewById(R.id.btnSimple);
        btnDownload = findViewById(R.id.btnDownload);
        btnExpand = findViewById(R.id.btnExpand);

    }

    private void clickEvents() {
        btnSimple.setOnClickListener(this);
        btnDownload.setOnClickListener(this);
        btnExpand.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSimple:
                showSimpleNotification(this, "title", "bodyyyyyyy");
                break;
            case R.id.btnDownload:
                downloadNotification();
                break;
            case R.id.btnExpand:
                expandNotification(this, "Expandable", "This is expandable notification");
                break;

            default:
                break;

        }

    }


    public void showSimpleNotification(Context context, String title, String body) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name1";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_mail)
                .setContentTitle(title)
                .setColor(Color.parseColor("#FF157EB1"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.img1))
                .setContentText(body);
        mBuilder.setAutoCancel(true);
        notificationManager.notify(notificationId, mBuilder.build());
    }


    void downloadNotification() {
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("https://cdn.vox-cdn.com/thumbor/eVoUeqwkKQ7MFjDCgrPrqJP5ztc=/0x0:2040x1360/1200x800/filters:focal(860x1034:1186x1360)/cdn.vox-cdn.com/uploads/chorus_image/image/59377089/wjoel_180413_1777_android_001.1523625143.jpg");
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Long reference = downloadManager.enqueue(request);

    }

    public void expandNotification(Context context, String title, String body) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = 2;
        String channelId = "channel-02";
        String channelName = "Channel Name2";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_mail)
                .setContentTitle(title)
                .setColor(Color.parseColor("#BB2A2A"))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.img1))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText(body);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("List of changes");

        String[] changes = new String[5];
        changes[0] = "Delete option";
        changes[1] = "Update option";
        changes[2] = "Share option";
        changes[3] = "Introduction option";

        for (int i = 0; i < changes.length; i++) {
            inboxStyle.addLine(changes[i]);
        }

        mBuilder.setStyle(inboxStyle);
        mBuilder.setAutoCancel(true);
        notificationManager.notify(notificationId, mBuilder.build());
    }

}
