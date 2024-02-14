package lihan.chen.moneymanager.feature.core.data.mapper

import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.data.model.CategoryEntity
import lihan.chen.moneymanager.feature.core.domain.model.Category


fun Category.toCategoryEntity() : CategoryEntity {
    return CategoryEntity(
        id = id,
        name = nameResId?: R.drawable.other_unknown,
        resIdString = resIdString,
        typeId = typeId,
    )
}
fun CategoryEntity.toCategory() : Category {
    return Category(
        id = id,
        nameResId = name,
        resIdString = resIdString,
        typeId = typeId,

    )
}