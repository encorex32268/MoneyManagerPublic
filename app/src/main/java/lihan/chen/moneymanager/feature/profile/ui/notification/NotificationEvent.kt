package lihan.chen.moneymanager.feature.profile.ui.notification

import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem

sealed class NotificationEvent {
    object AddNewAlarm : NotificationEvent()

    object DeleteAlarm : NotificationEvent()

    data class SwitchChange(
        val alarm : AlarmItem , val enable : Boolean
    ) : NotificationEvent()

    data class OnItemClick(
        val alarmItem : AlarmItem
    ) : NotificationEvent()

    data class SheetNameChange(val name: String) : NotificationEvent()
    data class SheetTimeChange(val time: String) : NotificationEvent()
}