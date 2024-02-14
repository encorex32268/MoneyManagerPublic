package lihan.chen.moneymanager.feature.core.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import lihan.chen.moneymanager.feature.core.data.model.CategoryEntity

class CategoryEntityTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromCategoryEntity(
        categoryEntity: CategoryEntity
    ): String {
        return gson.toJson(categoryEntity)
    }

    @TypeConverter
    fun toCategoryEntity(json: String): CategoryEntity {
        return gson.fromJson(
            json,
            object : TypeToken<CategoryEntity>(){}.type
        )
    }

}