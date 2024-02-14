package lihan.chen.moneymanager.feature.profile.data.preference

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import lihan.chen.moneymanager.feature.core.domain.model.Constants
import lihan.chen.moneymanager.feature.core.domain.util.dataStore
import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem
import lihan.chen.moneymanager.feature.profile.domain.preference.LocalAlarmPreferences

class LocalAlarmPreferencesImpl(
   private val context : Context
) : LocalAlarmPreferences {
    private val gson by lazy {
        Gson()
    }
    companion object{
        private val ALARM_LIST_KEY = stringPreferencesKey(Constants.ALARM_LIST)
    }

    override suspend fun saveAlarmList(alarmList: List<AlarmItem>) {
        context.dataStore.edit { settings ->
            settings[ALARM_LIST_KEY] = gson.toJson(alarmList)
        }
    }
    override fun readAlarmList(): Flow<List<AlarmItem>> {
        return context.dataStore.data.map { preferences ->
            val alarmListString = preferences[ALARM_LIST_KEY]
            val type = object : TypeToken<List<AlarmItem>>(){}.type
            gson.fromJson(alarmListString,type)?: emptyList()
        }
    }
}

