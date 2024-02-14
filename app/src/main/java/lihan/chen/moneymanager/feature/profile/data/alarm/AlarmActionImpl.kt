package lihan.chen.moneymanager.feature.profile.data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
import android.widget.Toast
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.profile.domain.alarm.AlarmAction
import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem
import java.text.SimpleDateFormat
import java.util.Calendar

class AlarmActionImpl(
    private val context : Context
)  : AlarmAction{

    private val alarmManager by lazy {
        context.getSystemService(AlarmManager::class.java)
    }


    override fun schedule(alarmItem: AlarmItem) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY,alarmItem.timeString.split(":")[0].toIntOrNull()?:0)
            set(Calendar.MINUTE,alarmItem.timeString.split(":")[1].toIntOrNull()?:0)
        }
        alarmItem.id?.let {id->
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    PendingIntent.getBroadcast(
                        context,
                        id,
                        Intent(
                            context ,
                            AlarmReceiver::class.java).apply {
                            putExtra("title",alarmItem.title)
                        },
                        PendingIntent.FLAG_MUTABLE
                    )
                )
        }
    }

    override fun cancel(alarmItem: AlarmItem) {
        alarmItem.id?.let {id->
            alarmManager.cancel(
                PendingIntent.getBroadcast(
                    context,
                    alarmItem.id,
                    Intent(context, AlarmReceiver::class.java),
                    PendingIntent.FLAG_MUTABLE
                ).also {
                    it.cancel()
                }
            )
        }
    }

    override fun reSchedule(alarmItem: AlarmItem) {
        cancel(alarmItem)
        schedule(alarmItem)
    }

    private fun getTimeFrom(time : Long) : String {
        val simpleDateFormat = SimpleDateFormat("YYY-MM-DD HH:mm:ss")
        return simpleDateFormat.format(time)
    }
}