package lihan.chen.moneymanager.feature.analysis.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import lihan.chen.moneymanager.feature.core.domain.util.ResUtils
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.core.util.toMoneyString
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme
import java.util.Calendar

@Composable
fun AnalysisScreen(
    state : AnalysisState,
    onEvent : (AnalysisEvent) -> Unit = {}
) {
    val spacer = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                onEvent(AnalysisEvent.OnBeforeYear)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Texts.TitleSmall(
                modifier = Modifier.weight(1f),
                text = state.nowDateYear,
                textAlign = TextAlign.Center
            )

            IconButton(onClick = {
                onEvent(AnalysisEvent.OnNextYear)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        LazyColumn{
            itemsIndexed(
                state.items
            ){ index,(month , sum) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacer.extraLarge, vertical = spacer.normal),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Texts.BodyMedium(text = stringResource(
                        id = ResUtils.getStringResourceIdByName(name = "month_${index+1}") ,
                        month.toString()
                    ))
                    Spacer(modifier = Modifier.width(spacer.extraLarge))
                    Texts.BodyLarge(text = sum.toLong().toMoneyString())
                }
            }
        }
    }
}



@PreviewLightDark
@Composable
fun AnalysisScreenPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
        ) {
            AnalysisScreen(
                state = AnalysisState(
                    items = listOf(
                        Pair( 1 , 13000f),
                        Pair( 2 , 1000f),
                        Pair( 3 , 20000f),
                        Pair( 4 , 8000f),
                        Pair( 5 , 34500f),
                        Pair( 6 , 76600f),
                        Pair( 7 , 40000f),
                        Pair( 8 , 30000f),
                        Pair( 9 , 1000f),
                        Pair( 10 , 5000f),
                        Pair( 11, 600f),
                        Pair( 12, 330f),
                    ),
                    nowDateYear = Calendar.getInstance().get(Calendar.YEAR).toString()
                )
            )
        }

    }

}