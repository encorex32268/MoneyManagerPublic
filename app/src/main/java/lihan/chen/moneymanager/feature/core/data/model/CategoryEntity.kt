package lihan.chen.moneymanager.feature.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey
    val id : Int?=null,
    val name : Int,
    val resIdString : String?=null,
    val typeId : Int
)
