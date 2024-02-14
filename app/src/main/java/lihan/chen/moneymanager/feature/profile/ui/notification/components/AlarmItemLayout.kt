package lihan.chen.moneymanager.feature.profile.ui.notification.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmItemLayout(
    modifier : Modifier = Modifier,
    alarmItem: AlarmItem,
    onAlarmDelete : () -> Unit ={},
    onItemClick : () -> Unit = {},
    onCheckedChange : (Boolean) -> Unit = {}
) {
    val spacer = LocalSpacing.current
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        onClick = onItemClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacer.normal),
                verticalAlignment = Alignment.CenterVertically
            ) {
               Text(
                   modifier = Modifier
                       .padding(start = spacer.normal)
                       .weight(1f),
                   text = alarmItem.title,
                   style = MaterialTheme.typography.titleMedium
               )
               Spacer(modifier = Modifier.width(spacer.normal))
               Icon(
                   modifier = Modifier
                       .padding(end = spacer.normal)
                       .clickable { onAlarmDelete() },
                   imageVector = Icons.Outlined.Delete,
                   contentDescription = null
               )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacer.normal),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription =null
                    )
                    Text(
                        text = alarmItem.timeString,
                        style = MaterialTheme.typography.displaySmall
                    )
                }

                Switch(
                    checked = alarmItem.enable,
                    onCheckedChange = onCheckedChange
                )

            }

        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun AlarmItemLayoutPreview() {
    MoneyManagerTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            AlarmItemLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                ,
                alarmItem = AlarmItem(
                    timeString ="12:00",
                    title = "launch"
                )
            )
        }
    }
}