package lihan.chen.moneymanager.feature.profile.ui.notification.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem
import lihan.chen.moneymanager.feature.profile.ui.notification.NotificationEvent
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@Composable
fun AlarmBottomSheet(
    modifier: Modifier = Modifier,
    onCloseClick : () -> Unit = {},
    onEvent : (NotificationEvent) -> Unit = {},
    currentAlarmItem: AlarmItem,
    onSaveClick : () -> Unit = {}
) {
    val spacer = LocalSpacing.current
    val defaultTimeText = stringResource(id = R.string.alaramaddbottomsheettimepicker)

    var isShowTimePicker by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                if (currentAlarmItem.timeString.isNotEmpty() && currentAlarmItem.timeString.isNotEmpty()){
                    onSaveClick()
                }else{
                    onCloseClick()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        OutlinedTextField(
            value = currentAlarmItem.title,
            onValueChange = {
                onEvent(NotificationEvent.SheetNameChange(it))
            },
            textStyle = MaterialTheme.typography.titleMedium.copy(
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(spacer.large))
        Box(modifier = Modifier
            .clickable { isShowTimePicker = true },
            contentAlignment = Alignment.CenterStart
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentAlarmItem.timeString.isNotEmpty()){
                    Icon(
                        painter = painterResource(R.drawable.baseline_access_time_24_outline),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(spacer.normal))
                }
                Texts.TitleMedium(
                    text = currentAlarmItem.timeString.ifEmpty { defaultTimeText },
                )
            }
        }
    }
    if (isShowTimePicker){
        TimePickerDialog(
            onDismissRequest = {isShowTimePicker = false},
            onConfirmClick = {
                onEvent(NotificationEvent.SheetTimeChange(it))
                isShowTimePicker = false
            }
        )
    }

}

@PreviewLightDark
@Composable
fun AlarmBottomSheetPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(
                MaterialTheme.colorScheme.background
            )
        ) {
            AlarmBottomSheet(
                currentAlarmItem = AlarmItem(
                    timeString = "10:20",
                    title = "Title"
                )
            )

        }

    }

}