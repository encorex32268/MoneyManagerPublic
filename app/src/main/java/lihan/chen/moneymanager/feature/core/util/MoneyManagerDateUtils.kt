package lihan.chen.moneymanager.feature.core.util

import android.util.Log
import java.util.Calendar
import java.util.Locale

object MoneyManagerDateUtils {

    fun getTodayCalendar(): Calendar = Calendar.getInstance()

    /**
     *  getStartAndEndTime :
     *  Parameter : calendar : User Picked Time
     *  return : startTime and endTime  (TimeInMillis)
     *  ex : pick : 2024/03
     *  return start  03/01  end 03/31
     */
    fun getStartAndEndTime(
        calendar : Calendar
    ) : Pair<Long,Long> {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH))
        val startTime: Long = calendar.timeInMillis
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        val endTime: Long = calendar.timeInMillis
        return Pair(startTime,endTime)
    }

    fun getDayAndDayText(
        timestamp: Long
    ): String? {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        val year = calendar.get(Calendar.YEAR).toString()
        val month = (calendar.get(Calendar.MONTH) + 1).toString()
        val dayText = calendar.get(Calendar.DAY_OF_MONTH).toString()
        // 获取星期的显示名称
        return "$dayText " +calendar.getDisplayName(
            Calendar.DAY_OF_WEEK,
            Calendar.SHORT,
            Locale.getDefault()
        )

    }

    fun getStringDateFromCalendar(calendar: Calendar) : String {
        val calendar = Calendar.getInstance()
        return run {
            val year = calendar.get(Calendar.YEAR).toString()
            val month = (calendar.get(Calendar.MONTH)+1).toString()
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH).toString()
            "$year/$month/$dayOfMonth"
        }
    }

    fun getStringDateFromLong(dateTime : Long) : String{
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateTime
        return run {
            val year = calendar.get(Calendar.YEAR).toString()
            val month = (calendar.get(Calendar.MONTH)+1).toString()
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH).toString()
            "$year/$month/$dayOfMonth"+calendar.getDisplayName(
                Calendar.DAY_OF_WEEK,
                Calendar.SHORT,
                Locale.getDefault()
            )
        }
    }

    fun getStringDateFromLongWithoutDisplayName(dateTime : Long) : String{
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateTime
        return run {
            val year = calendar.get(Calendar.YEAR).toString()
            val month = (calendar.get(Calendar.MONTH)+1).toString()
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH).toString()
            "$year/$month/$dayOfMonth"
        }
    }


}