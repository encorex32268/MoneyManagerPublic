package lihan.chen.moneymanager.feature.analysis.domain

import kotlinx.coroutines.flow.Flow
import lihan.chen.moneymanager.feature.core.domain.model.Expense

interface AnalysisRepository {
    suspend fun getExposeItemsByYearTime(
        startTimeOfYear : Long , endTimeOfYear : Long
    ) : Flow<List<Expense>>

}