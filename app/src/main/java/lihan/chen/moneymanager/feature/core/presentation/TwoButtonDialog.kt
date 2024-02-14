package lihan.chen.moneymanager.feature.core.presentation

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.window.Dialog
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@Composable
fun TwoButtonDialog(
    title : String,
    content : String,
    titleTextStyle : TextStyle = MaterialTheme.typography.titleMedium,
    contentTextStyle: TextStyle= MaterialTheme.typography.bodyMedium,
    onDismissRequest : () -> Unit = {},
    onConfirmButtonClick : () -> Unit = {},
) {

    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
           TextButton(onClick = {
               onConfirmButtonClick()
               onDismissRequest()
           }) {
               Text(text = stringResource(id = R.string.dialog_ok_button))
           }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissRequest()
            }) {
                Text(text = stringResource(id = R.string.dialog_cancel_button))
            }
        },
        title = {
            Text(text = title , style = titleTextStyle)
        },
        text = {
            Text(text = content , style = contentTextStyle)
        }
    )

}

@PreviewLightDark
@Composable
fun TwoButtonDialogPreview() {
    MoneyManagerTheme {
        TwoButtonDialog(
            title = "Test Title",
            content = "Test Content"
        )

    }

}