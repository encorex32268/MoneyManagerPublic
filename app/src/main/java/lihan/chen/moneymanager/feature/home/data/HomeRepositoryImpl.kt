package lihan.chen.moneymanager.feature.home.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import lihan.chen.moneymanager.feature.core.data.ExpenseDao
import lihan.chen.moneymanager.feature.core.data.mapper.toExpense
import lihan.chen.moneymanager.feature.core.data.mapper.toExpenseEntity
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import lihan.chen.moneymanager.feature.home.domain.HomeRepository

class HomeRepositoryImpl(
    private val dao : ExpenseDao
) : HomeRepository {

    override suspend fun insertExpense(expense: Expense) {
       dao.insertExpense(expense.toExpenseEntity())
    }

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


    override suspend fun updateExpense(expense: Expense) {
        dao.updateExpense(expense.toExpenseEntity())
    }
}