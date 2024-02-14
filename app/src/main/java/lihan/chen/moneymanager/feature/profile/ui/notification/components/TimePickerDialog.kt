package lihan.chen.moneymanager.feature.profile.ui.notification.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import lihan.chen.moneymanager.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onDismissRequest : () -> Unit = {},
    onConfirmClick : (String) -> Unit= {}
) {
    val timePickerState = rememberTimePickerState(
        is24Hour = false
    )

    AlertDialog(
        onDismissRequest = onDismissRequest,
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.timepickerdialog_cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val formattedHour = String.format("%02d", timePickerState.hour)
                val formattedMinute = String.format("%02d", timePickerState.minute)
                onConfirmClick(
                    "${formattedHour}:${formattedMinute}"
                )
            }) {
                Text(text = stringResource(id = R.string.timepickerdialog_ok))
            }
        },
        title = {
            TimePicker(state = timePickerState)
        }
    )
}

@Preview
@Composable
fun TimePickerDialogPreview() {
    TimePickerDialog()

}