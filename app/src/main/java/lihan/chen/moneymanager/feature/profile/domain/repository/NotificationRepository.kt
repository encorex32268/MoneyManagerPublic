package lihan.chen.moneymanager.feature.profile.domain.repository

import kotlinx.coroutines.flow.Flow
import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem

interface NotificationRepository {
    fun schedule(alarmItem: AlarmItem)
    fun cancel(alarmItem: AlarmItem)
    suspend fun saveAlarmList(
        alarmList : List<AlarmItem>
    )
    fun readAlarmList() : Flow<List<AlarmItem>>
}