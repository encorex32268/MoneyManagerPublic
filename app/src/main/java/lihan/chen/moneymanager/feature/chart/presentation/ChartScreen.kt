package lihan.chen.moneymanager.feature.chart.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.BuildConfig
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.chart.domain.model.Chart
import lihan.chen.moneymanager.feature.chart.presentation.components.ChartLayout
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import lihan.chen.moneymanager.feature.core.domain.model.FOOD
import lihan.chen.moneymanager.feature.core.domain.model.SHOPPING
import lihan.chen.moneymanager.feature.core.domain.model.SPORTS
import lihan.chen.moneymanager.feature.core.domain.model.TRAFFIC
import lihan.chen.moneymanager.feature.core.domain.model.WEAR
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.home.presentation.components.DateLayout
import lihan.chen.moneymanager.feature.home.presentation.components.NormalDate
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@Composable
fun ChartScreen(
    modifier: Modifier = Modifier,
    state: ChartState,
    onDatePick: (NormalDate) -> Unit = {},
    onChartClick: () -> Unit = {}
) {
    val spacer = LocalSpacing.current
    Column(
        modifier = modifier
    ) {
        DateLayout(
            modifier = Modifier.fillMaxWidth(),
            currentDate = "${state.nowDateYear}/${state.nowDateMonth}",
            onYearPick = onDatePick
        )
        if (state.items.isEmpty()){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(100.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.other_unknown),
                    contentDescription = null
                )
                Texts.TitleMedium(
                    text = stringResource(id = R.string.no_data)
                )
            }
        }else{
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = spacer.normal)
            ){
                if (state.items.isEmpty()) {
                    item {

                    }
                }else{
                    item {
                        Spacer(modifier = Modifier.height(spacer.extraLarge))
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ChartLayout(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = spacer.large),
                                items = state.items,
                                isIncomeShow = state.isIncomeShow,
                                onChartClick = onChartClick,
                                detailExpense = state.expensesTypeList
                            )

                        }
                    }
                }
            }

        }
    }


}



fun Double.format(digits: Int) = "%.${digits}f".format(this)


@PreviewLightDark
@Composable
fun ChartScreenPreview() {
    MoneyManagerTheme {
        ChartScreen(
            state = ChartState(
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
                ),
                nowDateYear = "2024",
                nowDateMonth = "01",
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
        )

    }

}