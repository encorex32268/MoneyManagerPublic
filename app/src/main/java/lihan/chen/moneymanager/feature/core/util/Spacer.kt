package lihan.chen.moneymanager.feature.core.util

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacer(
    val default : Dp = 0.dp,
    val extraSmall : Dp = 2.dp,
    val small : Dp = 4.dp,
    val normal : Dp = 8.dp,
    val middle : Dp = 12.dp,
    val large : Dp = 16.dp,
    val extraLarge : Dp = 20.dp,
)

val LocalSpacing = compositionLocalOf { Spacer() }

