package lihan.chen.moneymanager.feature.core.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import lihan.chen.moneymanager.feature.core.data.model.ExpenseEntity

@Dao
interface ExpenseDao {

    @Insert(entity = ExpenseEntity::class)
    suspend fun insertExpense(expenseEntity: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity): Int


    @Query("SELECT * FROM ExpenseEntity WHERE timestamp BETWEEN :startTimeOfMonth AND :endTimeOfMonth")
    fun getExpenseByStartTimeAndEndTime(
         startTimeOfMonth : Long , endTimeOfMonth : Long
    ) : Flow<List<ExpenseEntity>>

    @Update(entity = ExpenseEntity::class)
    suspend fun updateExpense(expenseEntity: ExpenseEntity)


    @Query("SELECT * FROM ExpenseEntity WHERE id = :expenseId")
    suspend fun getExpenseById(
        expenseId : Int
    ) : ExpenseEntity?


    @Query("SELECT MAX(id) as id, MAX(category) as category, description, MAX(isIncome) as isIncome, MAX(cost) as cost, MAX(timestamp) as timestamp FROM ExpenseEntity GROUP BY description ORDER BY id DESC LIMIT 8")
    fun getExpenseLastEightItems() : Flow<List<ExpenseEntity>>

}