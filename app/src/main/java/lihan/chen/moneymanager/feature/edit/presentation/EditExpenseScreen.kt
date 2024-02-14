package lihan.chen.moneymanager.feature.edit.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.add.components.CircleIcon
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem
import lihan.chen.moneymanager.feature.core.domain.model.Expense
import lihan.chen.moneymanager.feature.core.domain.util.ResUtils
import lihan.chen.moneymanager.feature.core.presentation.TwoButtonDialog
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.core.util.MoneyManagerDateUtils
import lihan.chen.moneymanager.feature.core.util.toMoneyString
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@Composable
fun EditExpenseScreen(
   state: EditExpenseState,
   onDelete : () -> Unit = {},
   onEdit : () -> Unit = {}
) {
    val spacer = LocalSpacing.current
    val isShowDeleteDialog = rememberSaveable {
        mutableStateOf(false)
    }
   
    state.currentExpense?.let {
        if (isShowDeleteDialog.value){
            TwoButtonDialog(
                title = stringResource(id = R.string.dialog_delete_title),
                content = stringResource(id = R.string.dialog_delete_content),
                onConfirmButtonClick = onDelete,
                onDismissRequest = {
                    isShowDeleteDialog.value = false
                }
            )
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    isShowDeleteDialog.value = true
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null)
            }
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacer.normal),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacer.normal),
                    verticalArrangement = Arrangement.spacedBy(spacer.normal,Alignment.CenterVertically)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacer.normal),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircleIcon(
                            modifier = Modifier.size(48.dp),
                            isClicked = true,
                            imageResId = ResUtils.getDrawableResourceIdByName(name = it.category?.resIdString?:""),
                            backgroundColor = CategoryItem.getColorByCategory(it.category?.typeId?:0)
                        )
                        Spacer(modifier = Modifier.width(spacer.normal))
                        Text(
                            modifier = Modifier.weight(1f),
                            text = it.description,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacer.extraLarge),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Spacer(modifier = Modifier.width(spacer.extraLarge))
                        Text(
                            modifier = Modifier.weight(1f),
                            text = MoneyManagerDateUtils.getStringDateFromLong(it.timestamp),
                            style = MaterialTheme.typography.bodySmall.copy(
                                letterSpacing = 1.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacer.extraLarge),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(24.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_attach_money_24),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Spacer(modifier = Modifier.width(spacer.extraLarge))
                        Text(
                            modifier = Modifier.weight(1f),
                            text = if (it.isIncome) it.cost.toMoneyString() else "-${it.cost.toMoneyString()}",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    IconButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = onEdit
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Edit,
                            contentDescription = null)
                    }

                }
            }
        }

    }


}

@PreviewLightDark
@Composable
fun EditExpenseScreenPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            EditExpenseScreen(
                state = EditExpenseState(
                    currentExpense = Expense(
                        id = 32,
                        category = CategoryItem.getItemsForAddNew()[10],
                        description = "Test description" ,
                        isIncome = false,
                        cost = 1000,
                        timestamp = 1706780791161 //2024/2/1
                    )
                )
            )

        }

    }

}