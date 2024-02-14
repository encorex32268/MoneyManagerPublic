package lihan.chen.moneymanager.feature.profile.domain.preference

import kotlinx.coroutines.flow.Flow

interface LocalThemeModePreferences {

    suspend fun saveMode(
        mode : Int
    )

    fun readMode() : Flow<Int>
}