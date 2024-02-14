package lihan.chen.moneymanager.feature.profile.ui.lightdarkmode

import androidx.compose.foundation.isSystemInDarkTheme

data class LightDarkModeState(
    val mode : Mode?=null
)

sealed class Mode(val mode : Int) {
    object Light : Mode(1)
    object Dark : Mode(0)

}
