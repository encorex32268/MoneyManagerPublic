package lihan.chen.moneymanager.feature.analysis.ui

import lihan.chen.moneymanager.feature.core.domain.model.Expense

data class AnalysisState(
    val items :  List<Pair<Int, Float>> = emptyList(),
    val nowDateYear : String = "",
)
