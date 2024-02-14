package lihan.chen.moneymanager.feature.chart.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lihan.chen.moneymanager.feature.chart.domain.ChartRepository
import lihan.chen.moneymanager.feature.chart.domain.model.Chart
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import lihan.chen.moneymanager.feature.core.util.MoneyManagerDateUtils
import lihan.chen.moneymanager.feature.home.presentation.components.NormalDate
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val chartRepository: ChartRepository
) : ViewModel(){

    private val _state = MutableStateFlow(ChartState())
    val state = _state.asStateFlow()

    private val _dbExpenseState = MutableStateFlow(listOf<Expense>())
    private val dbExpenseState = _dbExpenseState.asStateFlow()

    fun getDataFromDate(
        normalDate : NormalDate?=null
    ) {
        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            if (normalDate != null){
                calendar.set(Calendar.YEAR , normalDate.year)
                calendar.set(Calendar.MONTH , normalDate.month)
            }
            val (startTime, endTime) = MoneyManagerDateUtils.getStartAndEndTime(calendar)
            chartRepository.getExpenseByStartTimeAndEndTime(
                startTime, endTime
            ).collectLatest { it ->
                _dbExpenseState.update { data ->
                    it
                }
                val dataGroup = it.groupBy {
                    it.category?.typeId
                }
                val expensesTypeList =
                    it.filter { it.isIncome == state.value.isIncomeShow }.groupBy {
                        it.category?.typeId ?: 0
                    }.toList().sortedBy {
                        it.first
                    }
                val itemCharts = dataGroup.map {
                    val typeId = it.key
                    val items = it.value
                    typeId?.let {
                        Chart(
                            typeId = typeId,
                            income = items.filter { it.isIncome }.sumOf { it.cost },
                            expense = items.filterNot { it.isIncome }.sumOf { it.cost }
                        )
                    }
                }
                _state.update {
                    it.copy(
                        items = itemCharts.filterNotNull(),
                        expensesTypeList = expensesTypeList,
                        nowDateYear = if (normalDate != null){
                            normalDate.year.toString()
                        }else{
                            Calendar.getInstance().get(Calendar.YEAR).toString()
                        },
                        nowDateMonth = if(normalDate != null){
                            (normalDate.month+1).toString()
                        }else{
                            (Calendar.getInstance().get(Calendar.MONTH) + 1).toString()
                        }
                    )
                }

            }

        }
    }


    fun onChartClick(){
        val expensesTypeList  = dbExpenseState.value.filter { it.isIncome != state.value.isIncomeShow }.groupBy {
            it.category?.typeId?:0
        }.toList().sortedBy {
            it.first
        }
        _state.update {
            it.copy(
                isIncomeShow = !state.value.isIncomeShow,
                expensesTypeList = expensesTypeList
            )
        }
    }
}