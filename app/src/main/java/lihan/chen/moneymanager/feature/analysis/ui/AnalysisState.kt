package lihan.chen.moneymanager.feature.analysis.ui

data class AnalysisState(
    val items :  List<Pair<Int, Float>> = emptyList(),
    val nowDateYear : String = "",
)
