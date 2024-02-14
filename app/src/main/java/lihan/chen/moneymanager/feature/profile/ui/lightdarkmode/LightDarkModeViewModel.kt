package lihan.chen.moneymanager.feature.profile.ui.lightdarkmode

import androidx.core.text.util.LocalePreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lihan.chen.moneymanager.feature.profile.domain.preference.LocalThemeModePreferences
import javax.inject.Inject

@HiltViewModel
class LightDarkModeViewModel @Inject constructor(
    private val localThemeModePreferences: LocalThemeModePreferences
) : ViewModel() {

    private val _state = MutableStateFlow(LightDarkModeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            localThemeModePreferences.readMode().collectLatest {mode ->
                _state.update {
                    it.copy(
                        mode = if (mode == 0) Mode.Dark else Mode.Light
                    )
                }
            }
        }
    }

    fun onClick(){
        _state.update { state ->
            state.copy(
                mode = if (state.mode == Mode.Dark) Mode.Light else Mode.Dark
            )
        }
        viewModelScope.launch {
            localThemeModePreferences.saveMode(
                state.value.mode?.mode?:0
            )
        }
    }

}