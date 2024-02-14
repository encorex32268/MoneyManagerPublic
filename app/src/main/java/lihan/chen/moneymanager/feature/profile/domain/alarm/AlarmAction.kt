package lihan.chen.moneymanager.feature.profile.domain.alarm

import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem

interface AlarmAction {
    fun schedule(alarmItem: AlarmItem)
    fun cancel(alarmItem: AlarmItem)

    fun reSchedule(alarmItem: AlarmItem)
}