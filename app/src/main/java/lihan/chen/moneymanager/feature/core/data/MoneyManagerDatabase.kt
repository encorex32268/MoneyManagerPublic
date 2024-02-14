package lihan.chen.moneymanager.feature.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import lihan.chen.moneymanager.feature.core.data.model.CategoryEntity
import lihan.chen.moneymanager.feature.core.data.model.ExpenseEntity

@Database(
    entities = [ExpenseEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CategoryEntityTypeConverter::class)
abstract class MoneyManagerDatabase : RoomDatabase(){
    abstract val expenseDao : ExpenseDao
    abstract val categoryDao : CategoryDao
}