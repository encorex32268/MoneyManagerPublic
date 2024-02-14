package lihan.chen.moneymanager.feature.add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import lihan.chen.moneymanager.R
import lihan.chen.moneymanager.feature.core.presentation.components.Texts
import lihan.chen.moneymanager.feature.core.util.LocalSpacing
import lihan.chen.moneymanager.feature.profile.ui.lightdarkmode.Mode
import lihan.chen.moneymanager.ui.theme.MoneyManagerTheme
import lihan.chen.moneymanager.ui.theme.dark_CorrectColorContainer
import lihan.chen.moneymanager.ui.theme.dark_ErrorColorContainer
import lihan.chen.moneymanager.ui.theme.light_CorrectColorContainer
import lihan.chen.moneymanager.ui.theme.light_ErrorColorContainer
import lihan.chen.moneymanager.ui.theme.light_onCorrectColor
import lihan.chen.moneymanager.ui.theme.light_onErrorColor
import lihan.chen.moneymanager.ui.theme.md_theme_dark_outline

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostTypeSelect(
    modifier: Modifier = Modifier,
    isIncome : Boolean = false,
    onTypeChange : (Boolean) -> Unit = {},
    shape : RoundedCornerShape = RoundedCornerShape(8.dp),
    themeMode : Mode
){
    val spacer = LocalSpacing.current
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable { onTypeChange(false) }
                .background(
                    if (!isIncome){
                        if (themeMode == Mode.Dark) dark_CorrectColorContainer else light_CorrectColorContainer
                    }else{
                        if (themeMode == Mode.Dark) md_theme_dark_outline else light_onCorrectColor

                    },
                    shape=shape
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape=shape
                )
            ,
            contentAlignment = Alignment.Center){
            Texts.BodyMedium(
                modifier = Modifier.padding(spacer.large),
                text = stringResource(id = R.string.expense)
            )
        }
        Spacer(modifier = Modifier.width(spacer.large))
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onTypeChange(true)
                }
                .background(
                    if (isIncome){
                        if (themeMode == Mode.Dark) dark_ErrorColorContainer else light_ErrorColorContainer
                    }else{
                        if (themeMode == Mode.Dark) md_theme_dark_outline else light_onErrorColor
                    }
                    ,
                    shape=shape
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape=shape
                )
            ,
            contentAlignment = Alignment.Center
        ){
            Texts.BodyMedium(
                modifier = Modifier.padding(spacer.large),
                text =  stringResource(id = R.string.income),
                style = MaterialTheme.typography.bodyMedium,
            )
        }

    }
}

@PreviewLightDark
@Composable
fun CostTypeSelectPreview() {
    MoneyManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.background
                )
        ) {
            CostTypeSelect(
                themeMode = Mode.Light
            )
            CostTypeSelect(
                isIncome = true,
                themeMode = Mode.Dark
            )
        }
    }
}
