package lihan.chen.moneymanager.feature.profile.domain.repository

import kotlinx.coroutines.flow.Flow
import lihan.chen.moneymanager.feature.core.domain.model.Expense

interface ProfileRepository {

    fun getExpenseByStartTimeAndEndTime(
        startTime : Long , endTime : Long
    ) : Flow<List<Expense>>
}