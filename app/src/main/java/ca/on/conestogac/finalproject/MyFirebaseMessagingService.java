package ca.on.conestogac.finalproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by yumil on 2017-12-28.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getNotification() != null) {
            Intent intent = new Intent(this, FirebaseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            String body = remoteMessage.getNotification().getBody();
            NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Firebase Messaging")
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);
            NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(0, notifiBuilder.build());
        }
    }

}
