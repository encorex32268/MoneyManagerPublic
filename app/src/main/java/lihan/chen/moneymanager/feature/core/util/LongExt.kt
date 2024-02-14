package lihan.chen.moneymanager.feature.core.util

import java.text.NumberFormat
import java.util.Locale

fun Long.toMoneyString() : String {
   return NumberFormat.getCurrencyInstance(
      Locale.getDefault()
   ).format(this)
}