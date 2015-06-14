package nyc.c4q.handlertest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity
{

    public static final int NOTIFICATION_ID = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNotification();
        View startDelayButton = findViewById(R.id.startDelay);
        startDelayButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startDelay();
            }
        });

        View cancelButton = findViewById(R.id.cancelNotification);
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cancelNotification();
            }
        });
    }

    private void startDelay()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                showNotification();
            }
        }, 1000);
    }

    private void showNotification()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        updateNotification("Time is...", simpleDateFormat.format(new Date()));
    }

    private void updateNotification(String titleText, String contentText)
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setAutoCancel(true);
        builder.setContentTitle(titleText);
        builder.setContentText(contentText);
        builder.setSmallIcon(R.drawable.ic_stat_action_accessibility);
        Intent resultIntent = new Intent(this, AnotherActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void cancelNotification()
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

}
