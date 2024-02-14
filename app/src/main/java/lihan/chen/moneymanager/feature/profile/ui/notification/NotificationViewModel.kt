package lihan.chen.moneymanager.feature.profile.ui.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lihan.chen.moneymanager.feature.core.domain.model.Constants.ALARM_IDS
import lihan.chen.moneymanager.feature.profile.domain.alarm.AlarmAction
import lihan.chen.moneymanager.feature.profile.domain.model.alarm.AlarmItem
import lihan.chen.moneymanager.feature.profile.domain.preference.LocalAlarmPreferences
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val localAlarmPreferences: LocalAlarmPreferences,
    private val alarmAction: AlarmAction
) : ViewModel() {

    private val _state = MutableStateFlow(NotificationState())
    val state = _state.asStateFlow()
    init {
        viewModelScope.launch {
            localAlarmPreferences.readAlarmList().collectLatest {
                _state.update { state ->
                    state.copy(
                        alarmItemList = it
                    )
                }
            }
        }
    }
    fun onEvent(event: NotificationEvent){
        when(event){
            is NotificationEvent.AddNewAlarm -> {
                var currentList = state.value.alarmItemList.toMutableList()
                val eventAlarm = state.value.currentAlarm
                if (eventAlarm?.id == null){
                    val ids = currentList.map { it.id }
                    val emptyIds = ALARM_IDS.filter {
                        it !in ids
                    }
                    val emptyId = emptyIds.take(1)[0]
                    val addAlarm = eventAlarm?.copy(
                        id = emptyId,
                        enable = true
                    )
                    addAlarm?.let {
                        currentList.add(it)
                        alarmAction.schedule(it)
                    }
                }else {
                    currentList = currentList.map {
                        if (it.id == eventAlarm.id){
                            eventAlarm
                        }else{
                            it
                        }
                    }.toMutableList()
                    if (eventAlarm.enable){
                        alarmAction.reSchedule(eventAlarm)
                    }

                }
                _state.update {
                    it.copy(
                        alarmItemList = currentList,
                        currentAlarm = AlarmItem()
                    )
                }
            }
            is NotificationEvent.DeleteAlarm -> {
                val alarm = state.value.currentAlarm
                val currentList = state.value.alarmItemList.toMutableList()
                val findAlarm = currentList.find { it.id == alarm.id }
                findAlarm?.let {
                    alarmAction.cancel(it)
                    currentList.removeIf { it.id == alarm.id }
                    _state.update {
                        it.copy(
                            alarmItemList = currentList
                        )
                    }
                }
                viewModelScope.launch {
                    localAlarmPreferences.saveAlarmList(currentList)
                }
            }
            is NotificationEvent.SwitchChange -> {
                val currentList = state.value.alarmItemList.map {
                    if (it.id == event.alarm.id){
                        it.copy(enable = event.enable)
                    }else{
                        it
                    }
                }
                if (event.enable){
                   alarmAction.schedule(event.alarm)
                }else{
                    alarmAction.cancel(event.alarm)
                }
                _state.update {
                    it.copy(
                        alarmItemList = currentList,
                        currentAlarm = AlarmItem()
                    )
                }
                viewModelScope.launch {
                    localAlarmPreferences.saveAlarmList(currentList)
                }

            }
            is NotificationEvent.OnItemClick ->{
                _state.update {
                    it.copy(
                        currentAlarm = event.alarmItem
                    )
                }
            }
            is NotificationEvent.SheetNameChange->{
                _state.update {
                    it.copy(
                        currentAlarm = it.currentAlarm.copy(
                            title = event.name
                        )
                    )
                }
            }
            is NotificationEvent.SheetTimeChange->{
                _state.update {
                    it.copy(
                        currentAlarm = it.currentAlarm.copy(
                            timeString = event.time
                        )
                    )
                }
            }
        }
    }


}