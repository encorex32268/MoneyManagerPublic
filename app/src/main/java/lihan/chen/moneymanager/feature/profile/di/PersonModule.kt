package lihan.chen.moneymanager.feature.profile.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lihan.chen.moneymanager.feature.core.data.MoneyManagerDatabase
import lihan.chen.moneymanager.feature.profile.data.alarm.AlarmActionImpl
import lihan.chen.moneymanager.feature.profile.data.preference.LocalAlarmPreferencesImpl
import lihan.chen.moneymanager.feature.profile.data.preference.LocalThemeModePreferencesImpl
import lihan.chen.moneymanager.feature.profile.data.repository.NotificationRepositoryImpl
import lihan.chen.moneymanager.feature.profile.data.repository.ProfileRepositoryImpl
import lihan.chen.moneymanager.feature.profile.domain.alarm.AlarmAction
import lihan.chen.moneymanager.feature.profile.domain.preference.LocalAlarmPreferences
import lihan.chen.moneymanager.feature.profile.domain.preference.LocalThemeModePreferences
import lihan.chen.moneymanager.feature.profile.domain.repository.NotificationRepository
import lihan.chen.moneymanager.feature.profile.domain.repository.ProfileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersonModule {

    @Provides
    @Singleton
    fun provideAlarmAction(
        @ApplicationContext context : Context
    ) : AlarmAction {
        return AlarmActionImpl(context)
    }

    @Provides
    @Singleton
    fun provideLocalAlarmPreferences(
        @ApplicationContext context : Context
    ) : LocalAlarmPreferences {
        return LocalAlarmPreferencesImpl(context)
    }


    @Provides
    @Singleton
    fun provideNotificationRepository(
        alarmAction: AlarmAction,
        localAlarmPreferences: LocalAlarmPreferences
    ) : NotificationRepository {
        return NotificationRepositoryImpl(
            alarmAction, localAlarmPreferences
        )
    }


    @Provides
    @Singleton
    fun provideProfileRepository(
        database: MoneyManagerDatabase
    ) : ProfileRepository {
        return ProfileRepositoryImpl(
            database.expenseDao
        )
    }

    @Provides
    @Singleton
    fun provideLocalThemeModePreferences(
        @ApplicationContext context : Context
    ) : LocalThemeModePreferences {
        return LocalThemeModePreferencesImpl(context)
    }


}