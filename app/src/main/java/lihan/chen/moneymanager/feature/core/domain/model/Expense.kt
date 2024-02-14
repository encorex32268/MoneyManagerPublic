package lihan.chen.moneymanager.feature.core.domain.model

import java.text.SimpleDateFormat


data class Expense(
    val id : Int?=null,
    val category : Category?=null,
    val description : String,
    val isIncome : Boolean =false,
    val cost : Long = 0,
    val timestamp : Long = 0
){

    override fun toString(): String {
        return "$description , ${if (isIncome) cost else -cost} , ${getStringTime()}"
    }
    private fun getStringTime() : String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return simpleDateFormat.format(timestamp)
    }
}
