package lihan.chen.moneymanager.feature.add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@Composable
fun NumberButton(
    modifier: Modifier = Modifier,
    text : String,
    onClick: (String) -> Unit = {},
    textColor : Color = MaterialTheme.colorScheme.primary
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center){
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = CircleShape
                )
                .clickable { onClick(text) }
            ,
            contentAlignment = Alignment.Center,
        ){
            Texts.TitleMedium(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = textColor,
            )

        }

    }
}

@PreviewLightDark
@Composable
fun NumberButtonPreview() {
    MoneyManagerTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)

        ){
            NumberButton(
                text = "9"
            )

        }
    }
}