package lihan.chen.moneymanager.feature.home.domain


import kotlinx.coroutines.flow.Flow
import lihan.chen.moneymanager.feature.core.domain.model.Expense

interface HomeRepository {

    suspend fun insertExpense(expense: Expense)

    fun getExpenseByStartTimeAndEndTime(
        startTimeOfMonth : Long , endTimeOfMonth : Long
    ) : Flow<List<Expense>>

    suspend fun updateExpense(expense: Expense)
}