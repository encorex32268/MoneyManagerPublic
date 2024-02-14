package lihan.chen.moneymanager.feature.analysis.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lihan.chen.moneymanager.feature.analysis.domain.AnalysisRepository
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val analysisRepository: AnalysisRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AnalysisState())
    val state = _state.asStateFlow()

    init {
        getData()
    }

    fun onEvent(event: AnalysisEvent){
        when(event){
            AnalysisEvent.OnBeforeYear ->{
                val currentYear = state.value.nowDateYear.toInt() - 1
                getData(currentYear)
            }
            AnalysisEvent.OnNextYear -> {
                val currentYear = state.value.nowDateYear.toInt() + 1
                getData(currentYear)
            }
        }
    }

    private fun getData(
         year : Int = Calendar.getInstance().get(Calendar.YEAR)
    ){
        val calendar = Calendar.getInstance()
        calendar.set(year, Calendar.JANUARY, 1, 0, 0, 0)
        val startTimeInMillis = calendar.timeInMillis
        calendar.set(year, Calendar.DECEMBER, 31, 23, 59, 59)
        val endTimeInMillis = calendar.timeInMillis
        viewModelScope.launch {
            analysisRepository.getExposeItemsByYearTime(
                startTimeOfYear = startTimeInMillis,
                endTimeOfYear = endTimeInMillis
            ).collectLatest {
                Log.d("TAG", "getMonthAndMonthSum: ${getMonthAndMonthSum(it)}")

                _state.update { state ->
                    state.copy(
                        items =  getMonthAndMonthSum(it),
                        nowDateYear = year.toString()
                    )
                }
            }
        }
    }

    private fun getMonthAndMonthSum(it: List<Expense>): List<Pair<Int, Float>> {
        val group = it.groupBy {
            getMonthByTime(it.timestamp)
        }
        return group.mapValues {
            it.value.map {
                if (it.isIncome){
                    it.cost
                }else{
                    -it.cost
                }
            }.sum().toFloat()
        }.toList()
    }


    private fun getMonthByTime(timeStamp : Long) : Int {
        val simpleDateFormat = SimpleDateFormat("MM")
        val month = simpleDateFormat.format(timeStamp)
        return month.toInt()
    }




}