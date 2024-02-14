package lihan.chen.moneymanager.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import lihan.chen.moneymanager.R

val nunitoSansFontFamily = FontFamily(
    Font(R.font.nunitosans_10pt_regular , FontWeight.Normal),
    Font(R.font.nunitosans_10pt_medium , FontWeight.Medium),
    Font(R.font.nunitosans_10pt_bold , FontWeight.Bold),
    Font(R.font.nunitosans_10pt_extrabold , FontWeight.ExtraBold),
)

val titleTextStyle = TextStyle(
    fontFamily = nunitoSansFontFamily,
    fontWeight = FontWeight.Bold,
)
val bodyTextStyle = TextStyle(
    fontFamily = nunitoSansFontFamily,
    fontWeight = FontWeight.Normal
)


// Set of Material typography styles to start with
val Typography = Typography(
    displaySmall = titleTextStyle.copy(fontSize = 48.sp),
    titleLarge = titleTextStyle.copy(fontSize = 36.sp),
    titleMedium = titleTextStyle.copy(fontSize = 24.sp),
    titleSmall = titleTextStyle.copy(fontSize = 14.sp),

    bodyLarge = bodyTextStyle.copy(fontSize = 24.sp),
    bodyMedium = bodyTextStyle.copy(fontSize = 16.sp),
    bodySmall = bodyTextStyle.copy(fontSize = 12.sp)

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)