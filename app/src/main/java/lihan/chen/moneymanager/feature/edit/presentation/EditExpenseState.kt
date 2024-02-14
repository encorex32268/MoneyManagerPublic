package lihan.chen.moneymanager.feature.edit.presentation

import lihan.chen.moneymanager.feature.core.domain.model.Expense

data class EditExpenseState(
    val currentExpense : Expense?=null
)
