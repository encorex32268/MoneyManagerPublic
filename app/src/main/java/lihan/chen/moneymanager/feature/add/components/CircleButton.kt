package lihan.chen.moneymanager.feature.add.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@Composable
fun CircleButton() {
    Box(
        modifier = Modifier
            .size(72.dp)
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(100.dp)
            )
            .clip(RoundedCornerShape(100.dp))
            .border(
                width =  1.dp ,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(100.dp))
        ,
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "9",
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),

        )
    }
}

@PreviewLightDark
@Composable
fun CircleButtonPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            CircleButton()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}