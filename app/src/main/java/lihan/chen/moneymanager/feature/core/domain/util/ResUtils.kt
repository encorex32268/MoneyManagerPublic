package lihan.chen.moneymanager.feature.core.domain.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import lihan.chen.moneymanager.R

object ResUtils {

    @SuppressLint("DiscouragedApi")
    @Composable
    fun getDrawableResourceIdByName(name: String): Int {
        val context =  LocalContext.current
        val res =context.resources
        if (name.isEmpty()){
            return R.drawable.other_unknown
        }
        return res.getIdentifier(name, "drawable", context.packageName)
    }

    @SuppressLint("DiscouragedApi")
    @Composable
    fun getStringResourceIdByName(name: String): Int {
        val context =  LocalContext.current
        val res =context.resources
        if (name.isEmpty()){
            return R.string.month_1
        }
        return res.getIdentifier(name, "string", context.packageName)
    }
}