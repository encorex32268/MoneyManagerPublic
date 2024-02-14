package lihan.chen.moneymanager.feature.edit.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lihan.chen.moneymanager.feature.core.domain.util.UiEvent
import lihan.chen.moneymanager.feature.edit.domain.EditExpenseRepository
import javax.inject.Inject

@HiltViewModel
class EditExpenseViewModel @Inject constructor(
    private val editExpenseRepository: EditExpenseRepository
): ViewModel() {

    private val _state = MutableStateFlow(EditExpenseState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onDelete(){
        viewModelScope.launch {
            val expense = state.value.currentExpense
            expense?.let {
                editExpenseRepository.deleteExpense(it)
                _uiEvent.send(UiEvent.Success)
            }
        }
    }

    fun setState(
        expenseId: Int?
    ){
        expenseId?.let {
            viewModelScope.launch {
                val expense = editExpenseRepository.getExpenseById(it)
                expense?.let {
                    _state.update { state ->
                        state.copy(
                            currentExpense = it
                        )
                    }
                }
            }
        }
    }

}