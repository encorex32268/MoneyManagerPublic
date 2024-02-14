package lihan.chen.moneymanager.feature.profile.data.alarm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import lihan.chen.moneymanager.MainActivity
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.domain.model.Constants.CHANNEL
import lihan.chen.moneymanager.feature.core.domain.model.Constants.NOTIFICATION_ID

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmTitle  = intent?.getStringExtra("title")?:""
        context?.let {
            sendNotification(
                it,alarmTitle
            )
        }
    }
    private fun sendNotification(
        context: Context ,
        alarmTitle : String
    ){
        val intent = Intent(context , MainActivity::class.java).apply {
            flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP // 起動中のアプリがあってもこちらを優先する
                    or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED // 起動中のアプリがあってもこちらを優先する
                    or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        }
        val pendingIntent = PendingIntent.getActivities(
            context,
            0,
            arrayOf(intent),
            PendingIntent.FLAG_MUTABLE
        )
        val notification = NotificationCompat.Builder(
            context,CHANNEL
        )
            .setSmallIcon(R.mipmap.app_icon_foreground)
            .setContentTitle(
                "${context.resources.getString(R.string.notification_title)}:$alarmTitle"
            )
            .setContentText(context.resources.getString(R.string.notification_content))
            .setContentIntent(pendingIntent)
            .build()
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.notify(NOTIFICATION_ID,notification)

    }
}