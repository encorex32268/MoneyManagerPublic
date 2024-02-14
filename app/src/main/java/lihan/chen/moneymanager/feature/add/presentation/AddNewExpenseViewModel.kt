package lihan.chen.moneymanager.feature.add.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import lihan.chen.moneymanager.feature.add.domain.AddNewExpenseRepository
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import lihan.chen.moneymanager.feature.core.domain.model.RECENTLY
import lihan.chen.moneymanager.feature.core.domain.util.UiEvent
import lihan.chen.moneymanager.feature.core.util.MoneyManagerDateUtils
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddNewExpenseViewModel @Inject constructor(
    private val repository: AddNewExpenseRepository,
    private val saveSavedStateHandle: SavedStateHandle
): ViewModel(){

    private val stateKey = "AddNewExpenseState"
    private val descriptionKey = "descriptionState"

    val state = saveSavedStateHandle.getStateFlow(stateKey , AddNewExpenseState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val description = saveSavedStateHandle.getStateFlow(descriptionKey , "")

    fun onEvent(event: AddNewExpenseEvent){
        when(event){
            AddNewExpenseEvent.DeleteText -> {
                var costText = state.value.cost.toString()
                if (costText.isNotBlank() && costText.isNotEmpty()){
                    costText = costText.dropLast(1)
                    if (costText.isNotEmpty()){
                        saveSavedStateHandle[stateKey] = saveSavedStateHandle.get<AddNewExpenseState>(stateKey)?.copy(
                            cost = costText.toLong()
                        )
                    }else{
                        saveSavedStateHandle[stateKey] = saveSavedStateHandle.get<AddNewExpenseState>(stateKey)?.copy(
                            cost = 0
                        )
                    }
                }
            }
            is AddNewExpenseEvent.OnTextChange -> {
                var costText = state.value.cost.toString()
                if (costText.length < 10){
                    costText += event.text
                    saveSavedStateHandle[stateKey] = saveSavedStateHandle.get<AddNewExpenseState>(stateKey)?.copy(
                        cost = costText.toLong()
                    )
                }
            }
            AddNewExpenseEvent.SaveExpense -> {
                viewModelScope.launch {
                    val temp = Calendar.getInstance()
                    temp.set(Calendar.YEAR , state.value.year.toInt())
                    temp.set(Calendar.MONTH , state.value.month.toInt() -1 )
                    temp.set(Calendar.DAY_OF_MONTH , state.value.dayOfMonth.toInt())


                    if (state.value.expenseId == null){
                        repository.insertExpense(
                            Expense(
                                category = state.value.category,
                                description = state.value.description.ifEmpty {
                                    saveSavedStateHandle.get<String>(descriptionKey)?:""
                                },
                                isIncome = state.value.isIncome,
                                cost = state.value.cost,
                                timestamp = temp.timeInMillis
                            )
                        )
                    }else{
                        state.value.expenseId?.let {
                            repository.updateExpense(
                                Expense(
                                    description = state.value.description.ifEmpty {
                                        saveSavedStateHandle.get<String>(descriptionKey)?:""
                                    },
                                    isIncome = state.value.isIncome,
                                    category = state.value.category,
                                    cost = state.value.cost,
                                    id = it,
                                    timestamp = temp.timeInMillis
                                )
                            )
                        }
                    }
                    _uiEvent.send(UiEvent.Success)
                }
            }
            is AddNewExpenseEvent.OnItemSelected ->{
                saveSavedStateHandle[stateKey] = saveSavedStateHandle.get<AddNewExpenseState>(stateKey)?.copy(
                    categories = state.value.categories.map { category ->
                        if (event.category.id == category.category.id){
                            category.copy(isClick = true)
                        }else{
                            category.copy(isClick = false)
                        }
                    },
                    recentlyCategories = state.value.recentlyCategories.map {
                        it.copy(isClick = false)
                    },
                    category = event.category,
                    description = ""
                )
                saveSavedStateHandle[descriptionKey] = event.description
            }
            is AddNewExpenseEvent.OnRecentlyItemSelected ->{
                saveSavedStateHandle[stateKey] = saveSavedStateHandle.get<AddNewExpenseState>(stateKey)?.copy(
                    categories = state.value.categories.map {
                        it.copy(isClick = false)
                    },
                    recentlyCategories = state.value.recentlyCategories.map { category ->
                        if (event.category.id == category.category.id){
                            category.copy(isClick = true)
                        }else{
                            category.copy(isClick = false)
                        }
                    },
                    category = event.category,
                    description = event.description
                )
                saveSavedStateHandle[descriptionKey] = event.category.name
            }
            is AddNewExpenseEvent.OnTypeChange ->{
                saveSavedStateHandle[stateKey] = saveSavedStateHandle.get<AddNewExpenseState>(stateKey)?.copy(
                    isIncome = event.isClicked
                )
            }
            is AddNewExpenseEvent.OnDescriptionChange ->{
                saveSavedStateHandle[stateKey] = saveSavedStateHandle.get<AddNewExpenseState>(stateKey)?.copy(
                    description = event.text
                )
            }
            is AddNewExpenseEvent.SelectedDate ->{
                val time = event.dateTime
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = time
                saveSavedStateHandle[stateKey] = saveSavedStateHandle.get<AddNewExpenseState>(stateKey)?.copy(
                    year = calendar.get(Calendar.YEAR).toString(),
                    month = (calendar.get(Calendar.MONTH) + 1).toString(),
                    dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH).toString(),
                )
            }
            else -> {}
        }
    }

//    fun resetState(){
//        _state.update {
//            AddNewExpenseState()
//        }
//    }

    fun setDate(
        year: String?,
        month: String?,
        dayOfMonth : String?,
        id : Int?
    ) {
        val calendar = MoneyManagerDateUtils.getTodayCalendar()
        if (id ==  -1){
            viewModelScope.launch {
                repository.getExpenseLastEightItems().collectLatest {
                    val recentlyCategories = it.mapIndexed { index, expense ->
                        expense.category?.copy(
                            id = RECENTLY + (expense.id?:index),
                            name = expense.description
                        )
                    }
                    saveSavedStateHandle[stateKey] = saveSavedStateHandle.get<AddNewExpenseState>(stateKey)?.copy(
                        year = year ?: calendar.get(Calendar.YEAR).toString(),
                        month = month ?: (calendar.get(Calendar.MONTH) + 1).toString(),
                        dayOfMonth = dayOfMonth ?: (calendar.get(Calendar.DAY_OF_MONTH)).toString(),
                        categories = CategoryItem.getItemsForAddNew().map { CategoryState(category = it) },
                        recentlyCategories = recentlyCategories.filterNotNull().map {
                            CategoryState(category = it)
                        }
                    )

                }
            }
        }else{
            id?.let { expenseId ->
                viewModelScope.launch {
                    val expense = repository.getExpenseById(expenseId)
                    expense?.let {expense ->
                        repository.getExpenseLastEightItems().collectLatest {
                            val recentlyCategories = it.mapIndexed { index, expense ->
                                expense.category?.copy(
                                    id = RECENTLY + (expense.id?:index),
                                    name = expense.description
                                )
                            }
                            val newRecentlyCategories = recentlyCategories.filterNotNull().map {
                                if(expense.description == it.name){
                                    CategoryState(isClick = true , it)
                                }else{
                                    CategoryState(isClick = false,category = it)
                                }
                            }
                            val categories = if(newRecentlyCategories.any{ it.isClick }) {
                                CategoryItem.getItemsForAddNew().map {
                                    CategoryState(isClick = false, category = it)
                                    }
                                }else{
                                    CategoryItem.getItemsForAddNew().map {
                                        if (expense.category?.id == it.id) {
                                            CategoryState(isClick = true, it)
                                        } else {
                                            CategoryState(isClick = false, category = it)
                                        }
                                    }
                                }
                            saveSavedStateHandle[stateKey] = saveSavedStateHandle.get<AddNewExpenseState>(stateKey)?.copy(
                                year = year?:calendar.get(Calendar.YEAR).toString(),
                                month = month?:(calendar.get(Calendar.MONTH) + 1).toString(),
                                dayOfMonth = dayOfMonth?:(calendar.get(Calendar.DAY_OF_MONTH)).toString(),
                                categories = categories,
                                recentlyCategories = newRecentlyCategories,
                                description = expense.description,
                                category = expense.category,
                                isIncome = expense.isIncome,
                                cost = expense.cost,
                                expenseId = expense.id
                            )
                        }
                    }
                }
            }
        }
    }
}