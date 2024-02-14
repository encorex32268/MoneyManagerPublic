package lihan.chen.moneymanager.feature.core

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import lihan.chen.moneymanager.feature.core.domain.model.Constants

@HiltAndroidApp
class MoneyManagerApp : Application(){

    override fun onCreate() {
        super.onCreate()
        val channelName = "ExpenseNotification"
        val descriptionText = ""
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(
            Constants.CHANNEL,channelName,importance
        ).apply {
            description = descriptionText
        }
        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}








