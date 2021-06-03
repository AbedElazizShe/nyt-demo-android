package com.sicpa.nytimedemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sicpa.nytimedemo.presentation.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val contextProvider: CoroutineContextProvider) :
    ViewModel() {

    private val job: Job = Job()
    abstract val coroutinesExceptionHandler: CoroutineExceptionHandler

    protected fun launchCoroutineIO(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(contextProvider.io + job + coroutinesExceptionHandler) {
            block()
        }
    }

    public override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
