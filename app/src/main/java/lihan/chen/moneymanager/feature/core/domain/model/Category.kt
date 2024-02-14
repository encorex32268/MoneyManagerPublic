package lihan.chen.moneymanager.feature.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID
@Parcelize
data class Category(
    val id : Int?=null,
    val nameResId : Int?=null,
    val name : String = "",
    val resIdString : String?=null,
    val typeId : Int,
    val uuid : String = UUID.randomUUID().toString()
) : Parcelable
