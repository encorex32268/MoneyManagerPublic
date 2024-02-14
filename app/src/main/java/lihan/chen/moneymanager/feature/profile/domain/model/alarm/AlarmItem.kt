package lihan.chen.moneymanager.feature.profile.domain.model.alarm

data class AlarmItem(
    val timeString : String = "",
    val title : String = "",
    val id : Int?=null,
    val enable : Boolean = false
)
