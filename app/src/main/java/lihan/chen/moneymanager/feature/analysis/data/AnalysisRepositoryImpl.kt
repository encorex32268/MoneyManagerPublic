package lihan.chen.moneymanager.feature.analysis.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import lihan.chen.moneymanager.feature.analysis.domain.AnalysisRepository
import lihan.chen.moneymanager.feature.core.data.ExpenseDao
import lihan.chen.moneymanager.feature.core.data.mapper.toExpense
import lihan.chen.moneymanager.feature.core.domain.model.Expense

class AnalysisRepositoryImpl(
    private val dao : ExpenseDao
) : AnalysisRepository {
    override suspend fun getExposeItemsByYearTime(
        startTimeOfYear : Long , endTimeOfYear : Long
    ): Flow<List<Expense>> {
        return dao.getExpenseByStartTimeAndEndTime(
            startTimeOfYear,endTimeOfYear
        ).map {
            it.map {
                it.toExpense()
            }
        }
    }

}