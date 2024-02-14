package lihan.chen.moneymanager.feature

import java.util.Calendar

fun main() {
    val year = Calendar.getInstance()
    year.add(Calendar.YEAR,1)
    println(year.get(Calendar.YEAR))
}