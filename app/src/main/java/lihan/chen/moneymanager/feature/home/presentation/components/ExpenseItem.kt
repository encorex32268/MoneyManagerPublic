package lihan.chen.moneymanager.feature.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.add.components.CircleIcon
import lihan.chen.moneymanager.feature.core.domain.model.Category
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import lihan.chen.moneymanager.feature.core.domain.model.FOOD
import lihan.chen.moneymanager.feature.core.domain.model.HEALTH
import lihan.chen.moneymanager.feature.core.domain.model.OTHER
import lihan.chen.moneymanager.feature.core.domain.model.SHOPPING
import lihan.chen.moneymanager.feature.core.domain.model.SPORTS
import lihan.chen.moneymanager.feature.core.domain.model.TRAFFIC
import lihan.chen.moneymanager.feature.core.domain.model.WEAR
import lihan.chen.moneymanager.feature.core.domain.util.ResUtils
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.core.util.toMoneyString
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme
import java.time.Instant

@Composable
fun ExpenseItem(
    modifier: Modifier = Modifier,
    dayText : String,
    items: List<Expense>,
    month : String,
    onItemClick: (Expense) -> Unit = {},
){
    val spacer = LocalSpacing.current
    val total = run {
        val incomeItems = items.filter { it.isIncome }
        val expenseItems = items.filterNot { it.isIncome }
        val income = incomeItems.sumOf { it.cost }
        val expense = expenseItems.sumOf { it.cost }
        -expense + income
    }
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 0.dp ,
            color = MaterialTheme.colorScheme.outlineVariant
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
                    .padding(spacer.normal)
            ) {
                Texts.BodyMedium(
                    modifier = Modifier.weight(1f),
                    text = "$month/$dayText"
                )
                Texts.BodyMedium(
                    text = stringResource(
                        id = R.string.total_expense_item,
                        total.toMoneyString()
                    )
                )
            }
            items.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacer.normal)
                        .clickable {
                            onItemClick(it)
                        }
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircleIcon(
                        modifier = Modifier.size(36.dp),
                        isClicked = true,
                        imageResId = ResUtils.getDrawableResourceIdByName(name = it.category?.resIdString?:""),
                        backgroundColor = CategoryItem.getColorByCategory(it.category?.typeId?:0)
                    )
                    Spacer(modifier = Modifier.width(spacer.normal))
                    Texts.BodySmall(
                        modifier = Modifier.weight(1f),
                        text = it.description,
                    )
                    Spacer(modifier = Modifier.width(spacer.normal))
                    Texts.BodySmall(
                        text = if (it.isIncome) it.cost.toMoneyString() else "-${it.cost.toMoneyString()}",
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@PreviewLightDark
@Composable
fun ExpenseItemPreview() {
    MoneyManagerTheme {
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
        val itemPairs = items.sortedBy { it.timestamp }.groupBy { it.timestamp }.toList()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.normal)
        ){
            items(itemPairs){(day , expenses) ->
                ExpenseItem(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    items = expenses,
                    onItemClick = {},
                    month = "12",
                    dayText = day.toString()
                )
            }
        }


    }
}