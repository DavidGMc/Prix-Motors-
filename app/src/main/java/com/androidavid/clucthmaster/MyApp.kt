package com.androidavid.clucthmaster

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class MyApp : Application() {

    companion object {
        const val FCM_CHANNEL_ID = "FCM_CHANNEL_ID"
    }

    override fun onCreate() {
        super.onCreate()
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val fcmChannel = NotificationChannel(FCM_CHANNEL_ID, "Notificaciones FCM", NotificationManager.IMPORTANCE_HIGH)
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(fcmChannel)
        }
    }


}