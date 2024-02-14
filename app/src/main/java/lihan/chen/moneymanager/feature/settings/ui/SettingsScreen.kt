package lihan.chen.moneymanager.feature.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.util.LocalSpacing

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    val spacer = LocalSpacing.current
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(spacer.normal),
            text = stringResource(id = R.string.profile),
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn(
            modifier = Modifier.weight(1f)
        ){

        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()

}