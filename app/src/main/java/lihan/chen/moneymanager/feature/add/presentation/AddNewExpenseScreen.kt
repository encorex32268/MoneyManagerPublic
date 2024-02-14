package lihan.chen.moneymanager.feature.add.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.add.components.CalculateLayout
import lihan.chen.moneymanager.feature.add.components.CircleIcon
import lihan.chen.moneymanager.feature.add.components.CostTypeSelect
import lihan.chen.moneymanager.feature.add.presentation.components.CategoryItem
import lihan.chen.moneymanager.feature.core.domain.model.Category
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem.getColorByCategory
import lihan.chen.moneymanager.feature.core.domain.util.ResUtils
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.profile.ui.lightdarkmode.Mode
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddNewExpenseScreen(
    state : AddNewExpenseState,
    onEvent: (AddNewExpenseEvent) -> Unit = {},
    theme : Mode
) {
    val bottomSheetState = rememberStandardBottomSheetState(
        skipHiddenState = false,
    )
    val bottomSheetScaffoldState  = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    val spacer = LocalSpacing.current
    val scope = rememberCoroutineScope()
    var currentCategory by remember {
        mutableStateOf<Category?>(null)
    }

    LaunchedEffect(key1 = state.category , Unit){
        currentCategory = state.category
        scope.launch {
            if (currentCategory == null){
                bottomSheetScaffoldState.bottomSheetState.hide()
            }else{
                bottomSheetScaffoldState.bottomSheetState.expand()
            }
        }
    }

    BottomSheetScaffold(
        modifier = Modifier
            .padding(horizontal = spacer.normal)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        scope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
                                bottomSheetScaffoldState.bottomSheetState.hide()
                            }
                        }
                    }
                )
            },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            currentCategory?.let {
                CalculateLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacer.normal)
                        .padding(bottom = spacer.large)
                    ,
                    onItemClick = {
                        onEvent(AddNewExpenseEvent.OnTextChange(it))
                    },
                    onDelete = {
                        onEvent(AddNewExpenseEvent.DeleteText)
                    },
                    onOkClick = {
                        onEvent(AddNewExpenseEvent.SaveExpense)
                    },
                    onValueChange = {
                        onEvent(AddNewExpenseEvent.OnDescriptionChange(it))
                    },
                    onDateSelected = {
                        onEvent(AddNewExpenseEvent.SelectedDate(it))
                    } ,
                    month = state.month,
                    day = state.dayOfMonth,
                    state = state
                )

            }
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            CostTypeSelect(
                modifier = Modifier.padding(spacer.normal),
                isIncome =  state.isIncome,
                onTypeChange = {
                    onEvent(
                        AddNewExpenseEvent.OnTypeChange(it)
                    )
                },
                themeMode = theme
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                if (state.recentlyCategories.isNotEmpty()){
                    item {
                        Column {
                            Text(
                                text = stringResource(id = R.string.recently),
                                style = MaterialTheme.typography.titleSmall
                            )
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                maxItemsInEachRow = 4,
                            ) {
                                state.recentlyCategories.forEach {categoryState ->
                                    CategoryItem(
                                        modifier = Modifier.weight(1f),
                                        isClicked = categoryState.isClick,
                                        category = categoryState.category,
                                        onItemClick = {
                                            onEvent(
                                                AddNewExpenseEvent.OnRecentlyItemSelected(
                                                    category = categoryState.category,
                                                    description = categoryState.category.name.ifEmpty { "" }
                                                )
                                            )
                                            scope.launch {
                                                bottomSheetScaffoldState.bottomSheetState.expand()
                                            }
                                        }
                                    )

                                }
                                repeat(4 - state.recentlyCategories.size % 4) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                            Spacer(modifier = Modifier.height(spacer.normal))
                        }
                    }
                }

                state.categories.groupBy { it.category.typeId }.toList().sortedBy {
                    it.first
                }.forEach { (typeId, category) ->
                    item {
                        Column {
                            Texts.TitleSmall(
                                text = stringResource(id = CategoryItem.getCategoryTypeNameByTypeId((typeId))),
                            )
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                maxItemsInEachRow = 4,
                            ) {
                                category.forEach {categoryState ->
                                    val categoryNameRes = categoryState.category.nameResId?.let {
                                        stringResource(id =it )
                                    }?:""
                                    CategoryItem(
                                        modifier = Modifier.weight(1f),
                                        isClicked = categoryState.isClick,
                                        category = categoryState.category,
                                        onItemClick = {
                                            onEvent(
                                                AddNewExpenseEvent.OnItemSelected(
                                                    category = categoryState.category,
                                                    description = categoryNameRes
                                                )
                                            )
                                            scope.launch {
                                                bottomSheetScaffoldState.bottomSheetState.expand()
                                            }
                                        }
                                    )

                                }
                                repeat(4 - category.size % 4) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                            Spacer(modifier = Modifier.height(spacer.normal))
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
fun AddNewExpenseScreenPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AddNewExpenseScreen(
                state = AddNewExpenseState(

                ),
                onEvent = {},
                theme = Mode.Dark
            )
        }
    }

}
