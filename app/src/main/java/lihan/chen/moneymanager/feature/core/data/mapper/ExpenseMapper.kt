package lihan.chen.moneymanager.feature.core.data.mapper

import lihan.chen.moneymanager.feature.core.data.model.ExpenseEntity
import lihan.chen.moneymanager.feature.core.domain.model.Expense


fun Expense.toExpenseEntity() : ExpenseEntity {
    return ExpenseEntity(
        id = id,
        category = category?.toCategoryEntity()!!,
        description = description,
        isIncome = isIncome,
        cost = cost,
        timestamp = timestamp
    )
}

fun ExpenseEntity.toExpense() : Expense {
    return Expense(
        id = id,
        category = category.toCategory(),
        description = description,
        isIncome = isIncome,
        cost = cost,
        timestamp = timestamp
    )
}