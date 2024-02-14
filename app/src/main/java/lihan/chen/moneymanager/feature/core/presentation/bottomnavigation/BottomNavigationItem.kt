package lihan.chen.moneymanager.feature.core.presentation.bottomnavigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title : String,
    val selectedIcon : ImageVector,
    val unSelectedIcon : ImageVector,
    val titleResId : Int
)
