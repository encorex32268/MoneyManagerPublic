package lihan.chen.moneymanager.feature.home.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme
import java.util.Calendar

@Composable
fun DateLayout(
    modifier: Modifier = Modifier,
    currentDate : String = "2023/12",
    onYearPick : (NormalDate) -> Unit = {}
) {
    val currentCalendar by remember {
        mutableStateOf(
            Calendar.getInstance()
        )
    }
    var currentYearText by remember {
        mutableStateOf(
            currentCalendar.get(Calendar.YEAR).toString()
        )
    }
    var isShowCalendar by remember {
        mutableStateOf(false)
    }
    val spacer = LocalSpacing.current
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(top = spacer.small)
                .padding(horizontal = spacer.normal)
                .clickable {
                    isShowCalendar = !isShowCalendar
                }
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(spacer.normal))
            Texts.TitleMedium(
                modifier = Modifier.weight(1f),
                text = currentDate
            )
        }
        AnimatedVisibility(visible = isShowCalendar) {
            OutlinedCard(
                modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                border = BorderStroke(
                    width = 0.dp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacer.normal)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacer.normal),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            currentCalendar.add(Calendar.YEAR , -1)
                            currentYearText = currentCalendar.get(Calendar.YEAR).toString()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }
                        Texts.TitleSmall(
                            modifier = Modifier.weight(1f),
                            text = currentYearText,
                            textAlign = TextAlign.Center
                        )
                        IconButton(onClick = {
                            currentCalendar.add(Calendar.YEAR , 1)
                            currentYearText = currentCalendar.get(Calendar.YEAR).toString()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }

                    }
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxWidth(),
                        columns = GridCells.Fixed(4)
                    ){
                        items(12){ month ->
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .padding(spacer.normal)
                                    .clickable {
                                        onYearPick(
                                            NormalDate(
                                                year = currentCalendar.get(Calendar.YEAR),
                                                month = month
                                            )
                                        )
                                        isShowCalendar = false
                                    },
                                contentAlignment = Alignment.Center
                            ){
                                val currentMonth = month + 1
                                val nowMonth = currentDate.split("/")[1].toIntOrNull()
                                Texts.BodyMedium(
                                    modifier = if (nowMonth == currentMonth )
                                        Modifier
                                            .fillMaxWidth()
                                            .clip(CircleShape)
                                            .border(
                                                width = 1.dp,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                shape = CircleShape
                                            )
                                    else Modifier,
                                    text = currentMonth.toString(),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                }

            }
        }

    }

}

@Preview(showSystemUi = true)
@PreviewLightDark
@Composable
fun DateLayoutPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(
                MaterialTheme.colorScheme.background
            )
        ) {
            DateLayout(
                modifier = Modifier.fillMaxWidth(),

                )

        }

    }
}
