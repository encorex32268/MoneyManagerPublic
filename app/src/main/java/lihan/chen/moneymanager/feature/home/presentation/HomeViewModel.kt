package lihan.chen.moneymanager.feature.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lihan.chen.moneymanager.feature.core.util.MoneyManagerDateUtils
import lihan.chen.moneymanager.feature.home.domain.HomeRepository
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun getData(){
        viewModelScope.launch {
            val nowDate = Calendar.getInstance()
            val (startTime , endTime) = MoneyManagerDateUtils.getStartAndEndTime(nowDate)
            homeRepository.getExpenseByStartTimeAndEndTime(
                startTime,endTime
            ).collectLatest {
                val data = it
                val dataGorp = data.sortedByDescending { it.timestamp }.groupBy {
                    MoneyManagerDateUtils.getDayAndDayText(it.timestamp)?:""
                }.toList()
                val incomeItems = data.filter { it.isIncome }
                val expenseItems = data.filterNot { it.isIncome }
                val income = incomeItems.sumOf { it.cost }
                val expense = expenseItems.sumOf { it.cost }
                val total = -expense + income

                _state.update { state ->
                    state.copy(
                        income = income,
                        expense = expense,
                        totalAmount = total,
                        items = dataGorp,
                        nowDateYear = Calendar.getInstance().get(Calendar.YEAR).toString(),
                        nowDateMonth = (Calendar.getInstance().get(Calendar.MONTH) + 1).toString(),
                        nowDateDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString(),
                    )
                }
            }

        }
    }

    fun onEvent(event: HomeEvent){
        if (event is HomeEvent.OnDatePick){
            viewModelScope.launch {
                val normalDate = event.normalDate
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR , normalDate.year)
                calendar.set(Calendar.MONTH , normalDate.month - 1)

                val (startTime , endTime )= MoneyManagerDateUtils.getStartAndEndTime(calendar)
                homeRepository.getExpenseByStartTimeAndEndTime(
                    startTime,endTime
                ).collectLatest {
                    val data = it
                    val dataGroup = data.sortedByDescending { it.timestamp }.groupBy {
                        MoneyManagerDateUtils.getDayAndDayText(it.timestamp)?:""
                    }.toList()
                    val incomeItems = data.filter { it.isIncome }
                    val expenseItems = data.filterNot { it.isIncome }
                    val income = incomeItems.sumOf { it.cost }
                    val expense = expenseItems.sumOf { it.cost }
                    val total = -expense + income
                    _state.update { state ->
                        state.copy(
                            nowDateYear =normalDate.year.toString(),
                            nowDateMonth = (normalDate.month).toString(),
                            income = income,
                            expense = expense,
                            totalAmount = total,
                            items = dataGroup
                        )
                    }
                }
            }
        }
    }
}