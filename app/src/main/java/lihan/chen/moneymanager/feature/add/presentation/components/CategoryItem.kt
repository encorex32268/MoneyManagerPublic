package lihan.chen.moneymanager.feature.add.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.feature.add.components.CircleIcon
import lihan.chen.moneymanager.feature.core.domain.model.Category
import lihan.chen.moneymanager.feature.core.domain.util.ResUtils
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    isClicked : Boolean,
    onItemClick : () -> Unit = {}
){
    val spacer = LocalSpacing.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircleIcon(
            modifier = Modifier
                .padding(horizontal = spacer.small)
                .padding(top = spacer.normal)
                .size(48.dp)
            ,
            backgroundColor = lihan.chen.moneymanager.feature.core.domain.model.CategoryItem.getColorByCategory(category.typeId),
            imageResId = ResUtils.getDrawableResourceIdByName(name = category.resIdString?:""),
            isClicked = isClicked,
            id = category.id,
            onItemClick = {
                onItemClick()
            },
        )
        val description = category.name.ifEmpty {
            category.nameResId?.let { stringResource(id = it) }
        }?:""
        Texts.BodySmall(text = description )

    }
}