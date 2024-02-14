package lihan.chen.moneymanager.feature.profile.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme
import java.util.Calendar

@Composable
fun ProfileDataPicker(
    onOk : (Long , Long) -> Unit = {_ , _ ->},
    onCancel : () -> Unit = {},
    onDismiss : () -> Unit = {}
) {

    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    val context = LocalContext.current
    var startTimeString by remember {
        mutableStateOf("")
    }
    var endTimeString by remember {
        mutableStateOf("")
    }
    var startTime by remember {
        mutableLongStateOf(0L)
    }
    var endTime by remember {
        mutableLongStateOf(0L)
    }
    var isShowStartDatePicker by remember {
        mutableStateOf(false)
    }
    var isShowEndDatePicker by remember {
        mutableStateOf(false)
    }
    val spacer = LocalSpacing.current

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                if (startTime != 0L && endTime != 0L){
                    onOk(
                        startTime , endTime
                    )
                    onDismiss()
                }else{
                    onCancel()
                }
            }) {
                Text(text = stringResource(id = R.string.dialog_ok_button))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(text = stringResource(id = R.string.dialog_cancel_button))
            }
        },
        title = {},
        text = {
            Column {
                TextButton(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    border = BorderStroke(
                        width =  1.dp ,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    shape = RoundedCornerShape(8.dp),
                    onClick = { isShowStartDatePicker = true}) {
                    Texts.BodyMedium(
                        text = startTimeString.ifEmpty {
                            stringResource(id = R.string.alaramaddbottomsheettimepicker)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(spacer.normal))
                TextButton(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    border = BorderStroke(
                        width =  1.dp ,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    shape = RoundedCornerShape(8.dp),
                    onClick = { isShowEndDatePicker = true}) {
                    Texts.BodyMedium(
                        text = endTimeString.ifEmpty {
                            stringResource(id = R.string.alaramaddbottomsheettimepicker)
                        }
                    )
                }

            }
        }
    )

    if (isShowStartDatePicker){
        DatePickerDialog(
            context,
            object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
                    val calendarStart = Calendar.getInstance().apply {
                        set(Calendar.YEAR , year)
                        set(Calendar.MONTH , month)
                        set(Calendar.DAY_OF_MONTH , day)
                        set(Calendar.HOUR_OF_DAY,0)
                        set(Calendar.MINUTE,0)
                        set(Calendar.SECOND,0)
                    }
                    startTimeString = String.format("%d-%d-%d" , year , month + 1 ,day)
                    startTime = calendarStart.timeInMillis
            }},
            year,month,day
        ).apply {
            setOnDismissListener {
                isShowStartDatePicker = false
            }
        }.show()
    }

    if (isShowEndDatePicker){
        DatePickerDialog(
            context,
            object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
                    val calendarEnd = Calendar.getInstance().apply {
                        set(Calendar.YEAR , year)
                        set(Calendar.MONTH , month)
                        set(Calendar.DAY_OF_MONTH , day)
                        set(Calendar.HOUR_OF_DAY,23)
                        set(Calendar.MINUTE,59)
                        set(Calendar.SECOND,59)
                    }
                    endTimeString = String.format("%d-%d-%d" , year , month + 1 ,day)
                    endTime = calendarEnd.timeInMillis
                }},
            year,month,day
        ).apply {
            setOnDismissListener {
                isShowEndDatePicker = false
            }
        }.show()
    }
}

@PreviewLightDark
@Composable
fun ProfileDataPickerPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        ) {
            ProfileDataPicker()

        }
    }

}