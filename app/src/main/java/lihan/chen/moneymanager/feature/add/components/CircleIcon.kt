package lihan.chen.moneymanager.feature.add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem
import lihan.chen.moneymanager.feature.core.domain.model.*
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem.getColorByCategory
import lihan.chen.moneymanager.feature.core.domain.util.ResUtils
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@Composable
fun CircleIcon(
    modifier : Modifier = Modifier,
    imageResId : Int,
    isClicked : Boolean = false,
    id : Int?=null,
    onItemClick : (Int) -> Unit = {},
    backgroundColor : Color,
    tintColor : Color = Color.Unspecified
) {
    val spacer = LocalSpacing.current
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (isClicked) backgroundColor else Color.White,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .clickable {
                    id?.let {
                        onItemClick(it)
                    }
                },
            contentAlignment = Alignment.Center
        ){
            Icon(
                modifier = Modifier.padding(spacer.normal),
                painter = painterResource(id = imageResId),
                contentDescription = null,
                tint =  tintColor,
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun CircleIconPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            val category = CategoryItem.getItemsForAddNew()[0]
            CircleIcon(
                modifier = Modifier
                    .padding(16.dp)
                    .size(48.dp),
                backgroundColor = getColorByCategory(category.typeId),
                imageResId = ResUtils.getDrawableResourceIdByName(name = category.resIdString?:""),
            )
            Spacer(modifier = Modifier.height(4.dp))
            CircleIcon(
                modifier = Modifier
                    .padding(16.dp)
                    .size(48.dp),
                backgroundColor = getColorByCategory(category.typeId),
                imageResId = ResUtils.getDrawableResourceIdByName(name = category.resIdString?:""),
                isClicked = true,
            )

        }
    }

}

