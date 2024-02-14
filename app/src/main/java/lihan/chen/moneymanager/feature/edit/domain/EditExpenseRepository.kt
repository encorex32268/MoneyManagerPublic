package lihan.chen.moneymanager.feature.edit.domain

import lihan.chen.moneymanager.feature.core.domain.model.Expense

interface EditExpenseRepository {
    suspend fun getExpenseById(expenseId : Int) : Expense?
    suspend fun deleteExpense(expense: Expense)

}