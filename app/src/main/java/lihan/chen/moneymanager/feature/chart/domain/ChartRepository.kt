package lihan.chen.moneymanager.feature.chart.domain

import kotlinx.coroutines.flow.Flow
import lihan.chen.moneymanager.feature.core.domain.model.Expense

interface ChartRepository {

    fun getExpenseByStartTimeAndEndTime(
        startTimeOfMonth : Long , endTimeOfMonth : Long
    ) : Flow<List<Expense>>
}