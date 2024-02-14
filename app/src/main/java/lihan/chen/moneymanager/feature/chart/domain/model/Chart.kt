package lihan.chen.moneymanager.feature.chart.domain.model

import androidx.compose.ui.graphics.Color
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem

data class Chart(
    val typeId : Int,
    val color : Color = CategoryItem.getColorByCategory(typeId),
    val income : Long = 0L,
    val expense : Long = 0L
)