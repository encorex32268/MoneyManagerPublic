package lihan.chen.moneymanager.feature.core.domain.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import lihan.chen.moneymanager.feature.core.domain.model.Constants

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = Constants.USER_DATA)