package lihan.chen.moneymanager.feature.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import lihan.chen.moneymanager.feature.core.data.model.CategoryEntity

@Dao
interface CategoryDao {

    @Insert
    suspend fun insertCategoryEntity(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM CATEGORYENTITY")
    fun getCategories() : Flow<List<CategoryEntity>>

}