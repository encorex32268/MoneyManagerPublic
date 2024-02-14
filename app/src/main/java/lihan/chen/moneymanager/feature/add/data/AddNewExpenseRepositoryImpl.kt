package lihan.chen.moneymanager.feature.add.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import lihan.chen.moneymanager.feature.add.domain.AddNewExpenseRepository
import lihan.chen.moneymanager.feature.core.data.CategoryDao
import lihan.chen.moneymanager.feature.core.data.ExpenseDao
import lihan.chen.moneymanager.feature.core.data.mapper.toCategory
import lihan.chen.moneymanager.feature.core.data.mapper.toCategoryEntity
import lihan.chen.moneymanager.feature.core.data.mapper.toExpense
import lihan.chen.moneymanager.feature.core.data.mapper.toExpenseEntity
import lihan.chen.moneymanager.feature.core.domain.model.Category
import lihan.chen.moneymanager.feature.core.domain.model.Expense

class AddNewExpenseRepositoryImpl(
    private val dao : ExpenseDao
) : AddNewExpenseRepository {
    override suspend fun getExpenseById(expenseId: Int): Expense? {
       return dao.getExpenseById(expenseId)?.toExpense()
    }

    override suspend fun updateExpense(expense: Expense) {
        dao.updateExpense(expense.toExpenseEntity())
    }

    override suspend fun insertExpense(expense: Expense) {
        dao.insertExpense(expense.toExpenseEntity())
    }

    //TODO : Wait
    override suspend fun getExpenseAtTime(startTime: Long, endTime: Long): Flow<List<Expense>> {
       return flow { emit(emptyList()) }
    }
    //TODO : Wait
    override fun getExpenseLastEightItems(): Flow<List<Expense>> {
        return dao.getExpenseLastEightItems().map {
            it.map {
                it.toExpense()
            }
        }
    }

}