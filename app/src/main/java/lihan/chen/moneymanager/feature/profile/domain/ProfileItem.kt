package lihan.chen.moneymanager.feature.profile.domain

import androidx.compose.ui.graphics.vector.ImageVector

data class ProfileItem(
    val title : String,
    val imageVector: ImageVector,
    val onItemClick : () -> Unit = {}
)
