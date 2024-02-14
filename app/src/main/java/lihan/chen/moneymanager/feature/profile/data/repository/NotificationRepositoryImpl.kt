package lihan.chen.moneymanager.feature.profile.data.repository

import kotlinx.coroutines.flow.Flow
import lihan.chen.moneymanager.feature.profile.domain.alarm.AlarmAction
import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem
import lihan.chen.moneymanager.feature.profile.domain.preference.LocalAlarmPreferences
import lihan.chen.moneymanager.feature.profile.domain.repository.NotificationRepository

class NotificationRepositoryImpl(
    private val alarmAction : AlarmAction,
    private val localAlarmPreferences: LocalAlarmPreferences
) : NotificationRepository {
    override fun schedule(alarmItem: AlarmItem) {
        alarmAction.schedule(alarmItem)
    }

    override fun cancel(alarmItem: AlarmItem) {
        alarmAction.cancel(alarmItem)
    }

    override suspend fun saveAlarmList(alarmList: List<AlarmItem>) {
        localAlarmPreferences.saveAlarmList(alarmList)
    }

    override fun readAlarmList(): Flow<List<AlarmItem>> {
       return localAlarmPreferences.readAlarmList()
    }
}