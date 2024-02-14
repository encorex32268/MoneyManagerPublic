package lihan.chen.moneymanager.feature.add.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import lihan.chen.moneymanager.feature.core.domain.model.Category
import lihan.chen.moneymanager.feature.core.domain.model.CategoryItem
import java.util.Calendar

@Parcelize
data class CategoryState(
    val isClick : Boolean = false,
    val category: Category
) : Parcelable


@Immutable
@Parcelize
data class AddNewExpenseState(
    val categories: List<CategoryState> = CategoryItem.getItemsForAddNew().map {
        CategoryState(category = it)
    },
    val recentlyCategories : List<CategoryState> = emptyList(),

    val expenseId : Int?=null,
    val category : Category?=null,
    val description : String = "",
    val isIncome : Boolean =false,
    val cost: Long = 0,

    val year : String = Calendar.getInstance().get(Calendar.YEAR).toString(),
    val month : String = Calendar.getInstance().get(Calendar.MONTH).toString(),
    val dayOfMonth : String = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString(),
) : Parcelable
