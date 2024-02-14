package lihan.chen.moneymanager.feature.add.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@Composable
fun CalendarButton(
    modifier : Modifier = Modifier,
    month : Int = 1,
    day : Int = 6,
    onClick: () -> Unit = {},
    textColor : Color = MaterialTheme.colorScheme.primary
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(144.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(16.dp)
            )
        ,
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Texts.TitleMedium(
                text = "$month/$day",
                textAlign = TextAlign.Center,
                color = textColor,
            )
        }
    }

}

@PreviewLightDark
@Composable
fun CalendarButtonPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            CalendarButton()
        }
    }
}