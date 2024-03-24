package lihan.chen.moneymanager.feature.home.presentation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.BuildConfig
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.domain.model.Category
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import lihan.chen.moneymanager.feature.core.domain.model.FOOD
import lihan.chen.moneymanager.feature.core.domain.model.HEALTH
import lihan.chen.moneymanager.feature.core.domain.model.OTHER
import lihan.chen.moneymanager.feature.core.domain.model.SHOPPING
import lihan.chen.moneymanager.feature.core.domain.model.SPORTS
import lihan.chen.moneymanager.feature.core.domain.model.TRAFFIC
import lihan.chen.moneymanager.feature.core.domain.model.WEAR
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.core.util.MoneyManagerDateUtils
import lihan.chen.moneymanager.feature.home.presentation.components.AmountTextLayout
import lihan.chen.moneymanager.feature.home.presentation.components.DateLayout
import lihan.chen.moneymanager.feature.home.presentation.components.DatePicker
import lihan.chen.moneymanager.feature.home.presentation.components.ExpenseItem
import java.time.Instant


@Composable
fun HomeScreen(
    modifier : Modifier = Modifier,
    state: HomeState,
    onNavigateToAdd : () -> Unit = {},
    onEvent : (HomeEvent) -> Unit = {},
    onNavigateToEdit : (Expense) -> Unit = {},
) {
    val spacer = LocalSpacing.current
    
    Scaffold(
        modifier = modifier,
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
//                DateLayout(
//                    modifier = Modifier.fillMaxWidth(),
//                    currentDate = "${state.nowDateYear}/${state.nowDateMonth}",
//                    onYearPick = {
//                        onEvent(
//                            HomeEvent.OnDatePick(it)
//                        )
//                    }
//                )
                DatePicker(
                    year = state.nowDateYear.toIntOrNull()?:0,
                    month = state.nowDateMonth.toIntOrNull()?:0,
                    onArrowLeftClick = {
                        Log.d("TAG", "HomeScreen: onArrowLeftClick ${it}")
                       onEvent(HomeEvent.OnDatePick(it))
                    },
                    onArrowRightClick = {
                        Log.d("TAG", "HomeScreen: onArrowRightClick ${it}")
                        onEvent(HomeEvent.OnDatePick(it))
                    }
                )
                Spacer(modifier = Modifier.height(spacer.small))
                AmountTextLayout(
                    modifier = Modifier.fillMaxWidth(),
                    income = state.income,
                    expense = state.expense,
                    total = state.totalAmount
                )
                Spacer(modifier = Modifier.height(spacer.large))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacer.large),
                    verticalArrangement = Arrangement.spacedBy(spacer.normal)
                ){
                    items(state.items){(dayTimestampText , expenses) ->
                        ExpenseItem(
                            modifier = Modifier
                                .fillMaxWidth(),
                            items = expenses,
                            onItemClick = {
                                onNavigateToEdit(it)
                            },
                            month = state.nowDateMonth,
                            dayText = dayTimestampText
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(70.dp))
                    }
                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onNavigateToAdd()
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription =null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}





@PreviewLightDark
@Composable
fun HomeScreenPreview() {
    val items = listOf(
        Expense(
            id = 1,
            category =Category(id = 10011, nameResId = R.string.food_beer, resIdString = "food_beer", typeId = FOOD),
            description = "This is description",
            isIncome = true,
            cost = 1000,
            timestamp = Instant.now().toEpochMilli()
        ),
        Expense(
            id = 1,
            category = Category(id = 20011, nameResId = R.string.traffic_airplane, resIdString = "traffic_airplane", typeId = TRAFFIC),
            description = "This is description",
            isIncome = true,
            cost = 1000,
            timestamp = Instant.now().toEpochMilli()

        ),
        Expense(
            id = 1,
            category = Category(id = 30011, nameResId = R.string.wear_t_shirt, resIdString = "wear_t_shirt", typeId = WEAR),
            description = "This is description",
            isIncome = true,
            cost = 1000,
            timestamp = Instant.now().toEpochMilli()

        ),
        Expense(
            id = 1,
            category = Category(id = 40013, nameResId = R.string.sports_basketball, resIdString = "sports_basketball", typeId = SPORTS),
            description = "This is description",
            isIncome = true,
            cost = 1000,
            timestamp = Instant.now().toEpochMilli()

        ),
        Expense(
            id = 1,
            category = Category(id = 50014, nameResId = R.string.sports_sports, resIdString = "sports_sports", typeId = SPORTS),
            description = "This is description",
            isIncome = true,
            cost = 1000,
            timestamp = Instant.now().toEpochMilli()

        ),
        Expense(
            id = 1,
            category = Category(id = 60013, nameResId = R.string.other_book, resIdString = "shopping_ring", typeId = SHOPPING),
            description = "This is description",
            isIncome = true,
            cost = 1000,
            timestamp = Instant.now().toEpochMilli()

        )
        ,
        Expense(
            id = 1,
            category = Category(id = 70011, nameResId = R.string.health_tooth, resIdString = "health_tooth", typeId = HEALTH),
            description = "This is description",
            isIncome = true,
            cost = 1000,
            timestamp = Instant.now().toEpochMilli()

        )
        ,
        Expense(
            id = 1,
            category = Category(id = 80011, nameResId = R.string.other_bill, resIdString = "other_bill", typeId = OTHER),
            description = "This is description",
            isIncome = true,
            cost = 1000,
            timestamp = Instant.now().toEpochMilli()

        )
        ,
        Expense(
            id = 1,
            category = Category(id = 80019, nameResId = R.string.other_phone, resIdString = "other_phone", typeId = OTHER),
            description = "This is description",
            isIncome = true,
            cost = 1000,
            timestamp = Instant.now().toEpochMilli()

        )
    )
    val itemPairs = items.sortedBy { it.timestamp }.groupBy {
        MoneyManagerDateUtils.getDayAndDayText(it.timestamp)?:""
    }.toList()

    HomeScreen(
        state = HomeState(
            income = 4000,
            expense = -3000,
            items = itemPairs,
        ),
        onNavigateToAdd = {}
    )
}