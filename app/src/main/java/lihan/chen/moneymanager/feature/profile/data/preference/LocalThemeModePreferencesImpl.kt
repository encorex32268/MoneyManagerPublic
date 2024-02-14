package lihan.chen.moneymanager.feature.profile.data.preference

import android.content.Context
import android.content.res.Configuration
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import lihan.chen.moneymanager.feature.core.domain.model.Constants
import lihan.chen.moneymanager.feature.core.domain.util.dataStore
import lihan.chen.moneymanager.feature.profile.domain.preference.LocalThemeModePreferences

class LocalThemeModePreferencesImpl(
    private val context : Context
) : LocalThemeModePreferences{

    companion object{
        private val MODE_KEY = intPreferencesKey(Constants.MODE)
    }

    override suspend fun saveMode(mode: Int) {
        context.dataStore.edit {
            it[MODE_KEY] = mode
        }
    }

    override fun readMode(): Flow<Int> {
       return context.dataStore.data.map {preferences->
           preferences[MODE_KEY]?.toInt()?:1

       }
    }
}