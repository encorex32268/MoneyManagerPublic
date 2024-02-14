package lihan.chen.moneymanager.feature.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import lihan.chen.moneymanager.feature.core.data.CategoryEntityTypeConverter

@Entity
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int?=null,
    val category : CategoryEntity,
    val description : String,
    val isIncome : Boolean,
    val cost : Long,
    val timestamp:Long
)
