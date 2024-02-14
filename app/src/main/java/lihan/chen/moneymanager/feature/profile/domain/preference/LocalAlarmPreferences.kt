package lihan.chen.moneymanager.feature.profile.domain.preference

import kotlinx.coroutines.flow.Flow
import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem

interface LocalAlarmPreferences {
    suspend fun saveAlarmList(
        alarmList : List<AlarmItem>
    )
    fun readAlarmList() : Flow<List<AlarmItem>>
}