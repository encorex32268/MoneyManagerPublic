package lihan.chen.moneymanager.feature.profile.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.BuildConfig
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.profile.domain.ProfileItem
import lihan.chen.moneymanager.feature.profile.ui.components.ProfileDataPicker
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onGoToNotification: () -> Unit = {},
    onOutputCSVClick : (Long , Long ) -> Unit = { _ , _  ->},
    onGoToLightDarkMode: () -> Unit = {},

) {
    val spacer = LocalSpacing.current
    var isShowDatePicker by remember {
        mutableStateOf(false)
    }
    var isStart  by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit){
        isStart = true
    }
    val profileList = listOf(
//        ProfileItem(
//            title = stringResource(id = R.string.profile_settings_fileout),
//            imageVector = ImageVector.vectorResource(id = R.drawable.base_drive_file_outline),
//            onItemClick = {
//                isShowDatePicker = true
//            }
//        ),
        ProfileItem(
            title = stringResource(id = R.string.profile_settings_notification),
            imageVector = Icons.Outlined.Notifications,
            onItemClick = onGoToNotification
        ),
        ProfileItem(
            title = stringResource(id = R.string.profile_settings_lightdark),
            imageVector = ImageVector.vectorResource(id = R.drawable.outline_light_mode_24),
            onItemClick = onGoToLightDarkMode
        ),
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            Box(
                modifier = Modifier
                    .size(250.dp),
                contentAlignment = Alignment.Center
            ){
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.profile_icon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
        items(profileList){ item: ProfileItem ->
            ProfileItemLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacer.large)
                ,
                imageVector = item.imageVector,
                title = item.title,
                onItemClick = item.onItemClick
            )
        }
    }
    if (isShowDatePicker){
        ProfileDataPicker(
            onOk =onOutputCSVClick,
            onCancel = {
                isShowDatePicker = false
            },
            onDismiss = {
                isShowDatePicker = false
            }
        )
    }

}

@Composable
private fun ProfileItemLayout(
    modifier : Modifier = Modifier,
    imageVector: ImageVector,
    title : String,
    onItemClick : () -> Unit = {}
){
    val spacer = LocalSpacing.current
    Row(
        modifier = modifier.clickable {
            onItemClick()
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(spacer.large))
        Texts.BodyLarge(
            modifier = Modifier
                .weight(1f)
                .padding(end = spacer.normal),
            text = title,
        )

        Spacer(modifier = Modifier.width(spacer.normal))
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
@PreviewLightDark
private fun ProfileItemPreview(){
    MoneyManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
        ) {

            ProfileScreen()
        }
    }
}

//@Preview
//@Composable
//fun ProfileScreenPreview() {
//    ProfileScreen()
//
//}