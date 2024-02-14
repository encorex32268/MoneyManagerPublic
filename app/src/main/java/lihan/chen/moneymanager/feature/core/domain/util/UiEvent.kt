package lihan.chen.moneymanager.feature.core.domain.util

sealed class UiEvent {
    object Success : UiEvent()
    data class Failed(
        val errorMessage : String?=null
    ) : UiEvent()
}