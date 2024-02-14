package lihan.chen.moneymanager.feature.add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.feature.add.presentation.AddNewExpenseState
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CalculateLayout(
    modifier : Modifier = Modifier,
    onValueChange : (String) -> Unit = {},
    onOkClick : () -> Unit = {},
    onItemClick: (String) -> Unit = {},
    onDelete : () -> Unit = {},
    onDateSelected : (Long) -> Unit = {},
    month: String = "",
    day : String = "",
    state: AddNewExpenseState
){
    val spacer = LocalSpacing.current
    val datePickerState = rememberDatePickerState()
    val isShowDialog = rememberSaveable { mutableStateOf(false) }

    if (isShowDialog.value) {
        DatePickerDialog(
            onDismissRequest = { isShowDialog.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDateSelected(datePickerState.selectedDateMillis?:0L)
                        isShowDialog.value = false
                    }) {
                    Text("Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = { isShowDialog.value = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExpenseInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacer.normal),
            onValueChange = onValueChange,
            category = state.category,
            description = state.description,
            cost = state.cost
        )
        Spacer(modifier = Modifier.height(spacer.normal))
        CalculateKeyboard(
            onCalendarButtonClick = { isShowDialog.value = true },
            onDelete = onDelete,
            onOkClick = onOkClick,
            onItemClick = onItemClick,
            month = month,
            day = day

        )

    }

}




@Composable
private fun CalculateKeyboard(
    onOkClick : () -> Unit = {},
    onItemClick: (String) -> Unit = {},
    onDelete : () -> Unit = {},
    onCalendarButtonClick : () -> Unit = {},
    month: String = "",
    day : String = ""
){
    val spacer = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacer.normal,Alignment.CenterVertically)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(modifier = Modifier.weight(1f), text = "7", onClick = onItemClick)
            NumberButton(modifier = Modifier.weight(1f), text = "8", onClick = onItemClick)
            NumberButton(modifier = Modifier.weight(1f), text = "9", onClick = onItemClick)
            NumberButton(modifier = Modifier.weight(1f), text = "<", onClick = { onDelete()})
        }
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Column(
                modifier = Modifier.weight(3f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NumberButton(modifier = Modifier.weight(1f), text = "4", onClick = onItemClick)
                    NumberButton(modifier = Modifier.weight(1f), text = "5", onClick = onItemClick)
                    NumberButton(modifier = Modifier.weight(1f), text = "6", onClick = onItemClick)
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NumberButton(modifier = Modifier.weight(1f), text = "1", onClick = onItemClick)
                    NumberButton(modifier = Modifier.weight(1f), text = "2", onClick = onItemClick)
                    NumberButton(modifier = Modifier.weight(1f), text = "3", onClick = onItemClick)
                }
            }
            CalendarButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                ,
                onClick = onCalendarButtonClick,
                month = month.toInt(),
                day = day.toInt()
            )

        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(modifier = Modifier.weight(1f), text = "0", onClick = onItemClick)
            NumberButton(modifier = Modifier.weight(1f), text = "00", onClick = onItemClick)
            NumberButton(modifier = Modifier.weight(1f), text = "000", onClick = onItemClick)
            NumberButton(
                modifier = Modifier.weight(1f),
                text = "OK", onClick = {onOkClick()} ,
                textColor =  MaterialTheme.colorScheme.tertiary
            )
        }

    }
}






@Composable
@PreviewLightDark
fun CalculateLayoutPreview(){
    MoneyManagerTheme {
        var text by remember {
            mutableStateOf("")
        }
        CalculateLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                ),
            onItemClick = {},
            onValueChange = {},
            onOkClick = {},
            onDelete = {},
            month = "1",
            day = "18",
            state = AddNewExpenseState(
                category = CategoryItem.getItemsForAddNew()[0],
                description = "This is description",
                cost = 1900
            )
        )

    }
}