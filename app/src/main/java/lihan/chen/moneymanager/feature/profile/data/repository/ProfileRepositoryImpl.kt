package lihan.chen.moneymanager.feature.profile.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import lihan.chen.moneymanager.feature.core.data.ExpenseDao
import lihan.chen.moneymanager.feature.core.data.mapper.toExpense
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import lihan.chen.moneymanager.feature.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val dao : ExpenseDao
) : ProfileRepository {
    override fun getExpenseByStartTimeAndEndTime(
        startTime: Long,
        endTime: Long
    ): Flow<List<Expense>> {
        return dao.getExpenseByStartTimeAndEndTime(
            startTime , endTime
        ).map {
            it.map {
                it.toExpense()
            }
        }
    }
}