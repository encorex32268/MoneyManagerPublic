package lihan.chen.moneymanager.feature.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    year: Int,
    month: Int,
    onArrowLeftClick: (NormalDate) -> Unit = {},
    onArrowRightClick: (NormalDate) -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                val result = NormalDate(year,month)
                when (month - 1) {
                    0 -> {
                        result.run {
                            this.year = year - 1
                            this.month = 12
                        }
                    }
                    else ->{
                        result.run {
                            this.month = month - 1
                        }
                    }
                }
                onArrowLeftClick(result)

            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "ArrowLeft",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Texts.TitleSmall(
            modifier = Modifier.weight(1f),
            text = "${year}/${String.format("%02d",month)}",
            textAlign = TextAlign.Center
        )
        IconButton(
            onClick = {
                val result = NormalDate(year,month)
                when (month + 1) {
                    13 -> {
                        result.run {
                            this.year = year + 1
                            this.month = 1
                        }
                    }
                    else ->{
                        result.run {
                            this.month = month + 1
                        }
                    }
                }
                onArrowRightClick(result)
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "ArrowRight",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

    }
}

@Preview(showSystemUi = true)
@PreviewLightDark
@Composable
fun DatePickerPreview() {
    MoneyManagerTheme {
        Box(modifier = Modifier.background(
            MaterialTheme.colorScheme.background
        )){
            DatePicker(
                year = 2024,
                month = 3
            )
        }
    }
}