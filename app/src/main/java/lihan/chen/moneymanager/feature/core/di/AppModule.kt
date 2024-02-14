package lihan.chen.moneymanager.feature.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lihan.chen.moneymanager.feature.add.data.AddNewExpenseRepositoryImpl
import lihan.chen.moneymanager.feature.add.domain.AddNewExpenseRepository
import lihan.chen.moneymanager.feature.analysis.data.AnalysisRepositoryImpl
import lihan.chen.moneymanager.feature.analysis.domain.AnalysisRepository
import lihan.chen.moneymanager.feature.chart.data.ChartRepositoryImpl
import lihan.chen.moneymanager.feature.chart.domain.ChartRepository
import lihan.chen.moneymanager.feature.core.data.CategoryDao
import lihan.chen.moneymanager.feature.core.data.ExpenseDao
import lihan.chen.moneymanager.feature.core.data.MoneyManagerDatabase
import lihan.chen.moneymanager.feature.edit.data.EditExpenseRepositoryImpl
import lihan.chen.moneymanager.feature.edit.domain.EditExpenseRepository
import lihan.chen.moneymanager.feature.home.data.HomeRepositoryImpl
import lihan.chen.moneymanager.feature.home.domain.HomeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesMoneyManagerDatabase(
        @ApplicationContext context : Context
    ) : MoneyManagerDatabase{
        return Room.databaseBuilder(
            context,
            MoneyManagerDatabase::class.java,
            "money_manager_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesHomeRepository(
        dao: MoneyManagerDatabase
    ) : HomeRepository {
        return HomeRepositoryImpl(dao.expenseDao)
    }

    @Provides
    @Singleton
    fun providesCategoryRepository(
        dao: MoneyManagerDatabase
    ) : AddNewExpenseRepository {
        return AddNewExpenseRepositoryImpl(dao.expenseDao)
    }

    @Provides
    @Singleton
    fun providesEditExpenseRepository(
        dao: MoneyManagerDatabase
    ) : EditExpenseRepository {
        return EditExpenseRepositoryImpl(dao.expenseDao)
    }

    @Provides
    @Singleton
    fun providesChartRepository(
        dao: MoneyManagerDatabase
    ) : ChartRepository {
        return ChartRepositoryImpl(dao.expenseDao)
    }


    @Provides
    @Singleton
    fun providesAnalysisRepository(
        dao: MoneyManagerDatabase
    ) : AnalysisRepository {
        return AnalysisRepositoryImpl(dao.expenseDao)
    }


}