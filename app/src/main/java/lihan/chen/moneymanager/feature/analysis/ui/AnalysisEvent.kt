package lihan.chen.moneymanager.feature.analysis.ui

sealed class AnalysisEvent {
    object OnNextYear : AnalysisEvent()
    object OnBeforeYear : AnalysisEvent()
}