package com.hunseong.hilt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.hilt.data.Log
import com.hunseong.hilt.data.LogRepository
import com.hunseong.hilt.di.LogDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogViewModel @Inject constructor(@LogDB private val logRepository: LogRepository) : ViewModel() {

    private val _logLiveData = MutableLiveData<List<Log>>()
    val logLiveData : LiveData<List<Log>>
        get() = _logLiveData

    fun add(msg: String) {
        viewModelScope.launch {
            logRepository.add(msg)
            getLogs()
        }
    }

    fun getLogs() {
        viewModelScope.launch {
            val list = logRepository.getLogs()
            _logLiveData.value = list
        }
    }
}