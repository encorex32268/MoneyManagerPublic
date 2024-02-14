package lihan.chen.moneymanager.feature.add.domain

import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import lihan.chen.moneymanager.feature.core.domain.model.Category
import lihan.chen.moneymanager.feature.core.domain.model.Expense

interface AddNewExpenseRepository {

    suspend fun getExpenseById(expenseId : Int) : Expense?
    suspend fun updateExpense(expense: Expense)
    suspend fun insertExpense(expense: Expense)
    suspend fun getExpenseAtTime(startTime : Long , endTime:Long): Flow<List<Expense>>
    fun getExpenseLastEightItems() : Flow<List<Expense>>

}