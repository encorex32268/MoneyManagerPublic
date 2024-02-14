package lihan.chen.moneymanager.feature.edit.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import lihan.chen.moneymanager.feature.core.data.ExpenseDao
import lihan.chen.moneymanager.feature.core.data.mapper.toExpense
import lihan.chen.moneymanager.feature.core.data.mapper.toExpenseEntity
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import lihan.chen.moneymanager.feature.edit.domain.EditExpenseRepository

class EditExpenseRepositoryImpl(
    private val dao : ExpenseDao
) : EditExpenseRepository{

    override suspend fun getExpenseById(expenseId: Int): Expense? {
        return dao.getExpenseById(expenseId)?.toExpense()

    }

    override suspend fun deleteExpense(expense: Expense) {
        dao.deleteExpense(expense.toExpenseEntity())
    }
}