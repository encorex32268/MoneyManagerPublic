package lihan.chen.moneymanager.feature.chart.presentation

import lihan.chen.moneymanager.feature.chart.domain.model.Chart
import lihan.chen.moneymanager.feature.core.domain.model.Expense

data class ChartState(
    val items :  List<Chart> = emptyList(),
    val expensesTypeList : List<Pair<Int,List<Expense>>> = emptyList(),
    val nowDateYear : String = "",
    val nowDateMonth : String = "",
    val isIncomeShow : Boolean = false
)