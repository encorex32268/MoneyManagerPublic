package lihan.chen.moneymanager.feature.chart.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import lihan.chen.moneymanager.feature.chart.domain.ChartRepository
import lihan.chen.moneymanager.feature.core.data.ExpenseDao
import lihan.chen.moneymanager.feature.core.data.mapper.toExpense
import lihan.chen.moneymanager.feature.core.data.mapper.toExpenseEntity
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import lihan.chen.moneymanager.feature.home.domain.HomeRepository

class ChartRepositoryImpl(
    private val dao : ExpenseDao
) : ChartRepository {

    override fun getExpenseByStartTimeAndEndTime(startTimeOfMonth : Long , endTimeOfMonth : Long): Flow<List<Expense>> {
        return dao.getExpenseByStartTimeAndEndTime(
            startTimeOfMonth ,
            endTimeOfMonth
        ).map {
            it.map {
                it.toExpense()
            }
        }
    }

}