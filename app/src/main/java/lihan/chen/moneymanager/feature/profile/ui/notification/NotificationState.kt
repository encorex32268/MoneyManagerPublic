package lihan.chen.moneymanager.feature.profile.ui.notification

import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem

data class NotificationState(
    val alarmItemList : List<AlarmItem> = emptyList(),
    val currentAlarm : AlarmItem = AlarmItem()
)
