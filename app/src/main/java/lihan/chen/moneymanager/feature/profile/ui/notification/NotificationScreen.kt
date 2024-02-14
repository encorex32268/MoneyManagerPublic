package lihan.chen.moneymanager.feature.profile.ui.notification

import android.Manifest
import android.app.AlarmManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import kotlinx.coroutines.launch
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.presentation.TwoButtonDialog
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem
import lihan.chen.moneymanager.feature.profile.ui.notification.components.AlarmBottomSheet
import lihan.chen.moneymanager.feature.profile.ui.notification.components.AlarmItemLayout
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NotificationScreen(
    onEvent : (NotificationEvent) -> Unit ={},
    state : NotificationState,
    onGoToSetting : () -> Unit = {}
) {
    val spacer = LocalSpacing.current
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    val bottomSheetState = rememberStandardBottomSheetState(
        skipHiddenState = false,
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    val scope = rememberCoroutineScope()
    val isShowDeleteDialog = rememberSaveable {
        mutableStateOf(false)
    }
    var isHideBottomSheet by remember {
        mutableStateOf(true)
    }
    var hasPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
            mutableStateOf(true)
        }
    }
    val permission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult ={
            hasPermission = it
        }
    )
    LaunchedEffect( Unit){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        }
    }

    LaunchedEffect(isHideBottomSheet , Unit) {
        scope.launch {
            if (isHideBottomSheet){
                bottomSheetScaffoldState.bottomSheetState.hide()
                keyboard?.hide()
            }else{
                bottomSheetScaffoldState.bottomSheetState.expand()
            }
        }
    }
    BottomSheetScaffold(
        modifier = Modifier
            .padding(horizontal = spacer.normal)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        scope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
                                isHideBottomSheet = true
                            }
                        }
                    }
                )
            },
        sheetSwipeEnabled = false,
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            AlarmBottomSheet(
                modifier = Modifier.fillMaxSize(),
                onCloseClick = {
                    isHideBottomSheet = true
                },
                onSaveClick = {
                    onEvent(NotificationEvent.AddNewAlarm)
                    isHideBottomSheet = true
                },
                onEvent = onEvent,
                currentAlarmItem = state.currentAlarm

            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item{
                Texts.TitleLarge(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacer.normal),
                    text = stringResource(id = R.string.profile_settings_notification),
                )
            }
            items(state.alarmItemList){ alarmItem ->
                AlarmItemLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    ,
                    alarmItem = alarmItem,
                    onAlarmDelete = {
                        isShowDeleteDialog.value = true
                        onEvent(NotificationEvent.OnItemClick(alarmItem))
                    },
                    onCheckedChange = {boolean ->
                        onEvent(NotificationEvent.SwitchChange(alarmItem, boolean))
                    },
                    onItemClick = {
                        onEvent(NotificationEvent.OnItemClick(alarmItem))
                        isHideBottomSheet = false
                    }
                )
            }
            if (state.alarmItemList.size<3){
                item {
                    FloatingActionButton(
                        onClick = {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                val alarmManager = context.getSystemService(AlarmManager::class.java)
                                if (!alarmManager.canScheduleExactAlarms()){
                                    onGoToSetting()
                                    return@FloatingActionButton
                                }
                            }
                            onEvent(NotificationEvent.OnItemClick(AlarmItem()))
                            isHideBottomSheet = false
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription =null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

        }
    }
    if (isShowDeleteDialog.value){
        TwoButtonDialog(
            title = stringResource(id = R.string.dialog_delete_title),
            content = stringResource(id = R.string.dialog_delete_content),
            onConfirmButtonClick = {
                onEvent(NotificationEvent.DeleteAlarm)
            },
            onDismissRequest = {
                isShowDeleteDialog.value = false
            }
        )
    }
}


@PreviewLightDark
@Composable
fun NotificationScreenPreview() {
    MoneyManagerTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(
                MaterialTheme.colorScheme.background
            ),
            contentAlignment = Alignment.Center
        ){
            NotificationScreen(
                state = NotificationState(
                    alarmItemList = listOf(
                        AlarmItem(
                            timeString = "08:00",
                            title = "BreakFirst"
                        ),
                        AlarmItem(
                            timeString = "12:00",
                            title = "Launch",
                            enable = true
                        )
                    ),
                    currentAlarm = AlarmItem(
                        timeString = "18:00",
                        title = "Dinner"
                    )
                )
            )
        }

    }

}