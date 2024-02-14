package lihan.chen.moneymanager.feature.core.domain.model

import androidx.compose.ui.graphics.Color
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.domain.util.ResUtils

/**
const val FOOD = 10010
const val TRAFFIC = 20010
const val WEAR = 30010
const val SPORTS = 40010
const val SHOPPING = 50010
const val HEALTH = 60010
const val OTHER = 70010
 *
 * https://picsvg.com/
 * https://icons8.com/icon/set/question/family-ios
 */


const val RECENTLY = 80010
const val FOOD = 10010
const val TRAFFIC = 20010
const val WEAR = 30010
const val SPORTS = 40010
const val SHOPPING = 50010
const val HEALTH = 60010
const val OTHER = 70010

object CategoryItem {

    fun getItemsForAddNew() = listOf(
        Category(id = 10011, nameResId = R.string.food_food, resIdString = "food_food", typeId =FOOD ),
        Category(id = 10012, nameResId = R.string.food_hamburger, resIdString = "food_hamburger", typeId =FOOD ),
        Category(id = 10013, nameResId = R.string.food_noodles, resIdString = "food_noodles", typeId = FOOD),
        Category(id = 10014, nameResId = R.string.food_rice, resIdString = "food_rice", typeId = FOOD),
        Category(id = 10015, nameResId = R.string.food_fast, resIdString = "food_fast", typeId = FOOD),
        Category(id = 10016, nameResId = R.string.food_salad, resIdString = "food_salad", typeId = FOOD),
        Category(id = 10017, nameResId = R.string.food_drink, resIdString = "food_drink", typeId = FOOD),
        Category(id = 10018, nameResId = R.string.food_coffee, resIdString = "food_coffee", typeId = FOOD),
        Category(id = 10019, nameResId = R.string.food_cafe, resIdString = "food_cafe", typeId =FOOD ),
        Category(id = 10020, nameResId = R.string.food_hotcafe, resIdString = "food_hotcafe", typeId =FOOD ),
        Category(id = 10021, nameResId = R.string.food_beer, resIdString = "food_beer", typeId = FOOD),
        Category(id = 10022, nameResId = R.string.food_cake, resIdString = "food_cake", typeId = FOOD),
        Category(id = 10023, nameResId = R.string.food_birthday_cake, resIdString = "food_birthday_cake", typeId = FOOD),
        Category(id = 10024, nameResId = R.string.food_fruits, resIdString = "food_fruits", typeId = FOOD),
        Category(id = 10025, nameResId = R.string.food_bread, resIdString = "food_bread", typeId = FOOD),


        Category(id = 20011, nameResId = R.string.traffic_airplane, resIdString = "traffic_airplane", typeId = TRAFFIC),
        Category(id = 20012, nameResId = R.string.traffic_bicycle, resIdString = "traffic_bicycle", typeId = TRAFFIC),
        Category(id = 20013, nameResId = R.string.traffic_boat, resIdString = "traffic_speedboat", typeId = TRAFFIC),
        Category(id = 20014, nameResId = R.string.traffic_bus, resIdString = "traffic_bus", typeId = TRAFFIC),
        Category(id = 20015, nameResId = R.string.traffic_car, resIdString = "traffic_car", typeId = TRAFFIC),
        Category(id = 20016, nameResId = R.string.traffic_motobike, resIdString = "traffic_motobike", typeId = TRAFFIC),
        Category(id = 20017, nameResId = R.string.traffic_highspeedtrain, resIdString = "traffic_highspeedtrain", typeId = TRAFFIC),
        Category(id = 20018, nameResId = R.string.traffic_train, resIdString = "traffic_train", typeId = TRAFFIC),
        Category(id = 20019, nameResId = R.string.traffic_parking, resIdString = "traffic_parking", typeId = TRAFFIC),


        Category(id = 30011, nameResId = R.string.wear_t_shirt, resIdString = "wear_t_shirt", typeId = WEAR),
        Category(id = 30012, nameResId = R.string.wear_dress, resIdString = "wear_dress", typeId = WEAR),
        Category(id = 30013, nameResId = R.string.wear_babyshirt, resIdString = "wear_babyshirt", typeId = WEAR),
        Category(id = 30014, nameResId = R.string.wear_pants, resIdString = "wear_pants", typeId = WEAR),
        Category(id = 30015, nameResId = R.string.wear_skirt, resIdString = "wear_skirt", typeId = WEAR),
        Category(id = 30016, nameResId = R.string.wear_shorts, resIdString = "wear_shorts", typeId = WEAR),
        Category(id = 30017, nameResId = R.string.wear_shoes, resIdString = "wear_shoes", typeId = WEAR),
        Category(id = 30018, nameResId = R.string.wear_socks, resIdString = "wear_socks", typeId = WEAR),
        Category(id = 30019, nameResId = R.string.wear_cap, resIdString = "wear_cap", typeId = WEAR),
        Category(id = 30020, nameResId = R.string.wear_bag, resIdString = "wear_bag", typeId = WEAR),

        Category(id = 40011, nameResId = R.string.sports_sports, resIdString = "sports_sports", typeId = SPORTS),
        Category(id = 40012, nameResId = R.string.sports_basketball, resIdString = "sports_basketball", typeId = SPORTS),

        Category(id = 50011, nameResId = R.string.shopping_bag, resIdString = "shopping_bag", typeId = SHOPPING),
        Category(id = 50012, nameResId = R.string.shopping_gift, resIdString = "shopping_gift", typeId = SHOPPING),


        Category(id = 60011, nameResId = R.string.health_doctor, resIdString = "health_doctor", typeId = HEALTH),
        Category(id = 60012, nameResId = R.string.health_tooth, resIdString = "health_tooth", typeId = HEALTH),
        Category(id = 60013, nameResId = R.string.health_capsule, resIdString = "health_capsule", typeId = HEALTH),
        Category(id = 60014, nameResId = R.string.health_save, resIdString = "health_save", typeId = HEALTH),

        Category(id = 70011, nameResId = R.string.other_card, resIdString = "other_card", typeId = OTHER),
        Category(id = 70012, nameResId = R.string.other_bill, resIdString = "other_bill", typeId = OTHER),
        Category(id = 70013, nameResId = R.string.other_electric_fee, resIdString = "other_electric_fee", typeId = OTHER),
        Category(id = 70014, nameResId = R.string.other_water_fee, resIdString = "other_water_fee", typeId = OTHER),
        Category(id = 70015, nameResId = R.string.other_phone, resIdString = "other_phone", typeId = OTHER),
        Category(id = 70016, nameResId = R.string.other_smart_phone, resIdString = "other_smart_phone", typeId = OTHER),
        Category(id = 70017, nameResId = R.string.other_movie, resIdString = "other_movie", typeId = OTHER),
        Category(id = 70018, nameResId = R.string.other_stock_up, resIdString = "other_stock_up", typeId = OTHER),
        Category(id = 70019, nameResId = R.string.other_stock_down, resIdString = "other_stock_down", typeId = OTHER),
        Category(id = 70020, nameResId = R.string.other_salon_hair, resIdString = "other_salon", typeId = OTHER),
        Category(id = 70021, nameResId = R.string.other_massage, resIdString = "other_massage", typeId = OTHER),
        Category(id = 70022, nameResId = R.string.other_book, resIdString = "other_book", typeId = OTHER),
        Category(id = 70023, nameResId = R.string.other_music, resIdString = "other_music", typeId = OTHER),
        Category(id = 70024, nameResId = R.string.other_flower, resIdString = "other_flower", typeId = OTHER),
        Category(id = 70025, nameResId = R.string.other_ticket, resIdString = "other_ticket", typeId = OTHER),
        Category(id = 70026, nameResId = R.string.other_pets, resIdString = "other_pets", typeId = OTHER),
        Category(id = 70027, nameResId = R.string.other_tool, resIdString = "other_tool", typeId = OTHER),
        Category(id = 70028, nameResId = R.string.other_coins, resIdString = "other_coins", typeId = OTHER),
        Category(id = 70029, nameResId = R.string.other_money, resIdString = "other_money", typeId = OTHER),
        Category(id = 70030, nameResId = R.string.other_jobcar, resIdString = "other_jobcar", typeId = OTHER),
        Category(id = 70031, nameResId = R.string.other_jobmoto, resIdString = "other_jobmoto", typeId = OTHER),
        Category(id = 70032, nameResId = R.string.other_lottery, resIdString = "other_lottery", typeId = OTHER),
        Category(id = 70033, nameResId = R.string.other_lottery2, resIdString = "other_lottery2", typeId = OTHER),
        Category(id = 70034, nameResId = R.string.other_parttime, resIdString = "other_parttime", typeId = OTHER),
        Category(id = 70035, nameResId = R.string.other_work, resIdString = "other_work", typeId = OTHER),
    )


    fun getColorByCategory(id: Int): Color {
        return Color(
            when (id) {
                FOOD -> 0xFFFFFF93
                TRAFFIC -> 0xFF84C1FF
                WEAR -> 0xFFFFC78E
                SPORTS -> 0xFFA6FFFF
                SHOPPING -> 0xFFCA8EFF
                HEALTH -> 0xFFFF7575
                else -> 0xFF9D9D9D
            }
        )
    }

    fun getCategoryTypeNameByTypeId(typeId : Int) : Int{
        return when(typeId){
            RECENTLY -> { R.string.recently}
            FOOD ->{ R.string.food }
            TRAFFIC ->{ R.string.traffic }
            WEAR ->{ R.string.wear }
            SPORTS ->{ R.string.education }
            SHOPPING ->{ R.string.shopping }
            HEALTH ->{ R.string.health }
            else -> {  R.string.other}
        }
    }

    fun getCategoryTypeNameById(id : Int) : Int{
        return when(id){
            8 -> { R.string.recently}
            1 ->{ R.string.food }
            2 ->{ R.string.traffic }
            3 ->{ R.string.wear }
            4 ->{ R.string.education }
            5 ->{ R.string.shopping }
            6 ->{ R.string.health }
            else -> {  R.string.other}
        }
    }

}