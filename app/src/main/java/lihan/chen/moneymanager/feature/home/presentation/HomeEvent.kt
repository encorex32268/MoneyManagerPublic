package lihan.chen.moneymanager.feature.home.presentation

import lihan.chen.moneymanager.feature.home.presentation.components.NormalDate

sealed class HomeEvent{
    data class OnDatePick(
        val normalDate: NormalDate
    ) : HomeEvent()

    data class OnArrowLeftClick(
        val normalDate: NormalDate
    ): HomeEvent()

    data class OnArrowRightClick(
        val normalDate: NormalDate
    ): HomeEvent()
}
