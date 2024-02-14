package lihan.chen.moneymanager.feature.home.presentation

import lihan.chen.moneymanager.feature.core.domain.model.Expense
import java.util.Calendar

data class HomeState(
    val income : Long = 0,
    val expense : Long = -0,
    val totalAmount : Long = income + -expense,
    val items :  List<Pair<String, List<Expense>>> = emptyList(),
    val nowDateYear : String = "",
    val nowDateMonth : String = "",
    val nowDateDayOfMonth: String = ""
)
