package lihan.chen.moneymanager.feature.add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.domain.model.Category
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem
import lihan.chen.moneymanager.feature.core.domain.util.ResUtils
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@Composable
fun ExpenseInfo(
    modifier: Modifier = Modifier,
    category : Category?,
    description : String,
    cost : Long,
    onValueChange : (String) -> Unit,
){
    val spacer = LocalSpacing.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircleIcon(
            modifier = Modifier.size(48.dp),
            backgroundColor = CategoryItem.getColorByCategory(category?.typeId?:0),
            imageResId = ResUtils.getDrawableResourceIdByName(name = category?.resIdString?:""),
            isClicked = true,
        )
        Spacer(modifier = Modifier.width(spacer.normal))
        BasicTextField(
            modifier = Modifier.weight(1f),
            value = description,
            onValueChange = onValueChange,
            decorationBox = {
                if (description.isEmpty()){
                    Texts.BodyMedium(
                        text = stringResource(id = R.string.description)
                    )
                }
                it()
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        Spacer(modifier = Modifier.width(spacer.normal))
        Texts.TitleMedium(
            text = cost.toString(),
            maxLines = 1
        )
    }
}

@PreviewLightDark
@Composable
fun ExpenseInfoPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(
                MaterialTheme.colorScheme.background
            )
        ) {
            ExpenseInfo(
                modifier = Modifier.fillMaxWidth(),
                category = CategoryItem.getItemsForAddNew()[0],
                description = "This is description",
                cost = 1900,
                onValueChange = {}
            )

        }
    }
}