package lihan.chen.moneymanager.feature.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.core.util.Spacer
import lihan.chen.moneymanager.feature.core.util.toMoneyString
import lihan.chen.moneymanager.ui.theme.CorrectColor
import lihan.chen.moneymanager.ui.theme.ErrorColor
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@Composable
fun AmountTextLayout(
    modifier: Modifier = Modifier,
    income : Long,
    expense : Long,
    total : Long
) {
    val spacer = LocalSpacing.current
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.Transparent
        )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacer.normal)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AmountText(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = spacer.small),
                title = stringResource(id = R.string.total),
                style = MaterialTheme.typography.titleMedium.copy(
                    textAlign = TextAlign.Center
                ),
                textColor = MaterialTheme.colorScheme.onBackground,
                text = total.toMoneyString(),
                textSize = 20.sp
            )
            AmountText(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = spacer.small),
                title = stringResource(id = R.string.income),
                text = income.toMoneyString(),
                textColor = CorrectColor,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 14.sp
                )
            )
            AmountText(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = spacer.small),
                title = stringResource(id = R.string.expense),
                text = expense.toMoneyString(),
                textColor = ErrorColor,
                MaterialTheme.typography.titleSmall.copy(
                    fontSize = 14.sp
                )
            )
        }

    }
}

@Composable
private fun AmountText(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    textColor: Color,
    style: TextStyle,
    textSize: TextUnit = 14.sp
){
    var dynamicTextSize by remember {
        mutableStateOf(textSize)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Texts.BodySmall(text = title)
        Spacer(modifier = Modifier.width(LocalSpacing.current.extraLarge))
        Texts.TitleSmall(
            text = text,
            style = style.copy(
                fontSize = dynamicTextSize
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textColor,
            onTextLayout = {
                if (it.hasVisualOverflow && dynamicTextSize > 9.sp){
                    dynamicTextSize = (dynamicTextSize.value - 1.0F).sp
                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@PreviewLightDark
@Composable
fun AmountTextPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
        ) {
            AmountTextLayout(
                modifier = Modifier.fillMaxWidth(),
                income = 300,
                expense = -400,
                total = 9000000000
            )
            Spacer(modifier = Modifier.height(16.dp))

            AmountTextLayout(
                modifier = Modifier.fillMaxWidth(),
                income = 999999999,
                expense = -999999999,
                total = 2000
            )
            Spacer(modifier = Modifier.height(16.dp))

            AmountTextLayout(
                modifier = Modifier.fillMaxWidth(),
                income = 999999999,
                expense = 0,
                total = 2000
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}