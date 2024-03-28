package lihan.chen.moneymanager.feature.chart.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.chart.domain.model.Chart
import lihan.chen.moneymanager.feature.chart.presentation.format
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import lihan.chen.moneymanager.feature.core.domain.model.FOOD
import lihan.chen.moneymanager.feature.core.domain.model.SHOPPING
import lihan.chen.moneymanager.feature.core.domain.model.SPORTS
import lihan.chen.moneymanager.feature.core.domain.model.TRAFFIC
import lihan.chen.moneymanager.feature.core.domain.model.WEAR
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.core.util.toMoneyString
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChartLayout(
    modifier: Modifier = Modifier,
    items: List<Chart> = emptyList(),
    isIncomeShow: Boolean = false,
    onChartClick: () -> Unit = {},
    detailExpense : List<Pair<Int,List<Expense>>> = emptyList(),
) {
    val spacer = LocalSpacing.current
    val costItems = remember(isIncomeShow) {
        if (isIncomeShow) {
            items.map {
                Pair(it.typeId, it.income)
            }
        } else {
            items.map {
                Pair(it.typeId, it.expense)
            }
        }
    }
    val sum = remember(isIncomeShow) {
        items.sumOf {
            if (isIncomeShow) {
                it.income
            } else {
                it.expense
            }
        }
    }
    var isStart by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        isStart = true
    }

    var sweepAngle = 0f
    var startAngle = 0f
    val animation by animateFloatAsState(
        targetValue = if (isStart) 1f else 0f, label = "",
        animationSpec = tween(1000)
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.CenterHorizontally),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color.Transparent
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacer.normal),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                Box(modifier = Modifier
                    .size(150.dp)
                    .drawBehind {
                        if (sum != 0L) {
                            costItems.forEachIndexed { index, item ->
                                sweepAngle = 360f * item.second / sum
                                if (index == 0) {
                                    startAngle = -90f
                                } else {
                                    startAngle += 360f * costItems[index - 1].second / sum
                                }
                                drawArc(
                                    color = CategoryItem.getColorByCategory(item.first),
                                    startAngle = startAngle * animation,
                                    sweepAngle = sweepAngle * animation,
                                    useCenter = false,
                                    style = Stroke(
                                        width = 40.dp.toPx()
                                    )
                                )
                            }

                        }
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                onChartClick()
                            }
                        )
                    },
                    contentAlignment = Alignment.Center
                ) {
                    Texts.TitleMedium(
                        text = if (isIncomeShow)
                            stringResource(id = R.string.income) else
                                stringResource(id = R.string.expense),
                        modifier = Modifier.padding(spacer.normal),
                    )
                }
                Spacer(modifier = Modifier.width(50.dp))
                if (sum != 0L){
                    Column {
                        costItems.sortedByDescending { it.second }.forEach { item ->
                            if (item.second != 0L){
                                val width: Float = (item.second / sum.toFloat())
                                Texts.BodySmall(
                                    text = stringResource(id = CategoryItem.getCategoryTypeNameByTypeId(item.first))
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(12.dp)
                                            .clip(CircleShape)
                                            .background(
                                                color = CategoryItem.getColorByCategory(item.first),
                                                shape = CircleShape
                                            )
                                    )
                                    Spacer(modifier = Modifier.width(spacer.normal))
                                    Texts.BodyMedium(
                                        text = "${(width * 100).toDouble().format(2)}%"
                                    )
                                    Spacer(modifier = Modifier.width(spacer.normal))
                                }
                                Spacer(modifier = Modifier.height(spacer.normal))
                            }

                        }

                    }

                }

            }

        }
        if (sum != 0L){
            Spacer(modifier = Modifier.height(spacer.normal))
            DetailExpense(
                modifier = Modifier
                    .fillMaxWidth(),
                expensesTypeList = detailExpense
            )

        }
    }


}

@ExperimentalMaterial3Api
@Composable
private fun DetailExpense(
    modifier: Modifier = Modifier,
    expensesTypeList : List<Pair<Int,List<Expense>>>
) {
    val spacer = LocalSpacing.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacer.large)
    ) {
        expensesTypeList.forEachIndexed { index,  pair->
            val sum = pair.second.sumOf { it.cost }
            var expand by remember {
                mutableStateOf(false)
            }
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { expand = !expand},
                colors = CardDefaults.outlinedCardColors(
                    containerColor = Color.Transparent
                ),
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = spacer.large)
                        .padding(
                            start = spacer.normal
                        )
                ){
                    Texts.TitleMedium(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id =CategoryItem.getCategoryTypeNameByTypeId(pair.first)),
                    )
                    Texts.TitleMedium(
                        modifier = Modifier
                            .padding(horizontal = 25.dp),
                        text = sum.toMoneyString(),
                    )

                }
            }

            AnimatedVisibility(visible =expand) {
                Column {
                    val detailList = remember {
                        pair.second.sortedBy { it.cost }.groupBy { it.description }
                    }
                    detailList.onEachIndexed { index, entry ->
                        Box(modifier = Modifier
                            .drawBehind {
                                val path = Path().apply {
                                    moveTo(10.dp.toPx(),0f)
                                    lineTo(10.dp.toPx(), size.height / 2 )
                                    lineTo(20.dp.toPx(), size.height / 2)
                                    moveTo(10.dp.toPx(),size.height / 2)
                                }
                                drawPath(
                                    path = path,
                                    color = Color.LightGray,
                                    style = Stroke(
                                        1.dp.toPx()
                                    )
                                )

                            }){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Texts.BodyMedium(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 25.dp),
                                    text = entry.key,
                                )
                                Texts.BodyMedium(
                                    modifier = Modifier.padding(horizontal = 25.dp),
                                    text = entry.value.sumOf { it.cost }.toMoneyString(),
                                )

                            }
                        }
                    }


                }

            }

        }
    }

}

@ExperimentalMaterial3Api
@PreviewLightDark
@Preview(showSystemUi = true)
@Composable
fun DetailExpensePreview() {
    MoneyManagerTheme {
        Box(modifier = Modifier.background(
            MaterialTheme.colorScheme.background
        )) {
            DetailExpense(
                expensesTypeList = listOf(
                    Pair(
                        FOOD, listOf(
                            Expense(
                                category = CategoryItem.getItemsForAddNew()[1],
                                description = "Test1", isIncome = false, cost = 100
                            ),
                            Expense(
                                category = CategoryItem.getItemsForAddNew()[2],
                                description = "Test2", isIncome = false, cost = 200
                            ),
                            Expense(
                                category = CategoryItem.getItemsForAddNew()[3],
                                description = "Test3", isIncome = false, cost = 300
                            ),
                            Expense(
                                category = CategoryItem.getItemsForAddNew()[4],
                                description = "Test4", isIncome = false, cost = 400
                            ),
                            Expense(
                                category = CategoryItem.getItemsForAddNew()[5],
                                description = "Test5", isIncome = false, cost = 500
                            ),
                        )),

                    )
            )

        }

    }

}

@PreviewLightDark
@Preview(showSystemUi = true)
@Composable
fun ChartLayoutPreview() {
    MoneyManagerTheme {
        Box(modifier = Modifier.background(
            MaterialTheme.colorScheme.background
        )){
            ChartLayout(
                items = listOf(
                    Chart(
                        typeId = FOOD,
                        income = 300,
                        expense = 300
                    ),
                    Chart(
                        typeId = SPORTS,
                        income = 300,
                        expense = 400
                    ),
                    Chart(
                        typeId = TRAFFIC,
                        income = 300,
                        expense = 600
                    ),
                    Chart(
                        typeId = WEAR,
                        income = 300,
                        expense = 700
                    ),
                    Chart(
                        typeId = SHOPPING,
                        income = 300,
                        expense = 1000
                    ),
                )
            )

        }
    }
}