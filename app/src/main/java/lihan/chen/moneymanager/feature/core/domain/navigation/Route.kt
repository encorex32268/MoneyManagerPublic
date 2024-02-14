package lihan.chen.moneymanager.feature.core.domain.navigation

import android.app.Notification
import android.provider.Settings


object Route {
    const val Home = "Home"
    const val AddNew = "AddNew"
    const val Edit = "Edit"
    const val Chart = "Chart"
    const val Profile = "Profile"
    const val Analysis = "Analysis"

    const val Notification = "Notification"
    const val Settings = "Settings"
    const val LightDarkMode = "LightDarkMode"

    private val mainPages = listOf<String>(
        Home,
        Chart,
        Analysis,
        Profile
    )
    fun checkIsMainPage(route : String) : Boolean{
        return route in mainPages
    }
}