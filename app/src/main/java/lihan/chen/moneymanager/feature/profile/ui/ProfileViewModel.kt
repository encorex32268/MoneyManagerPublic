package lihan.chen.moneymanager.feature.profile.ui

import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opencsv.CSVWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lihan.chen.moneymanager.feature.core.domain.util.UiEvent
import lihan.chen.moneymanager.feature.profile.domain.repository.ProfileRepository
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.channels.FileLockInterruptionException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
): ViewModel(){

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun csvOutput(
        startTime : Long , endTime : Long
    ){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                profileRepository.getExpenseByStartTimeAndEndTime(startTime, endTime)
                    .collectLatest {
                        val expenseData = it
                        val csvFile = File(
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                            "Test.csv"
                        )
                        val data = listOf(
                            expenseData.toTypedArray()
                        )
                        val javaArray: MutableList<Array<String>> = mutableListOf()
                        data.forEach {
                            javaArray.add( it.map { it.toString() }.toTypedArray())
                        }
                        try {
                            val writer = CSVWriter(FileWriter(csvFile))
                            writer.writeAll(
                                javaArray
                            )
                            writer.flush()
                            writer.close()
                            _uiEvent.send(UiEvent.Success)
                        } catch (e: Exception) {
                            _uiEvent.send(UiEvent.Failed(e.localizedMessage))
                        } catch (e: FileAlreadyExistsException) {
                            _uiEvent.send(UiEvent.Failed(e.localizedMessage))
                        } catch (e: FileLockInterruptionException) {
                            _uiEvent.send(UiEvent.Failed(e.localizedMessage))
                        } catch (e: IOException) {
                            _uiEvent.send(UiEvent.Failed(e.localizedMessage))
                        }
                    }
            }
        }
    }
}