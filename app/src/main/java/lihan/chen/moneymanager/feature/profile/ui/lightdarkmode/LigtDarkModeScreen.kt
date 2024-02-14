package lihan.chen.moneymanager.feature.profile.ui.lightdarkmode

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.R


@Composable
fun LightDarkModeScreen(
    onClick : () -> Unit = {},
    state : LightDarkModeState
) {
    Box(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
        ,
        contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = when(state.mode){
                is Mode.Dark -> { R.drawable.baseline_mode_night_24 }
                is Mode.Light-> {R.drawable.outline_light_mode_24}
                else -> R.drawable.outline_light_mode_24
            }),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun LightDarkModeScreenPreview() {
    LightDarkModeScreen(
        onClick = {},
        state = LightDarkModeState()
    )

}