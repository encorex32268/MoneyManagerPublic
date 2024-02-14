package lihan.chen.moneymanager.feature.add.presentation

import lihan.chen.moneymanager.feature.core.domain.model.Category

sealed class AddNewExpenseEvent{
    data class OnTextChange(
        val text : String
    ) : AddNewExpenseEvent()

    data class OnDescriptionChange(
        val text : String
    ) : AddNewExpenseEvent()

    object DeleteText : AddNewExpenseEvent()
    object SaveExpense : AddNewExpenseEvent()
    data class OnItemSelected(
        val category: Category,
        val description : String
    ) : AddNewExpenseEvent()


    data class OnRecentlyItemSelected(
        val category: Category,
        val description : String
    ) : AddNewExpenseEvent()
    data class OnTypeChange(
        val isClicked : Boolean
    ) : AddNewExpenseEvent()

    data class SelectedDate(
        val dateTime : Long
    ) : AddNewExpenseEvent()

    object ResetExpense : AddNewExpenseEvent()
}
