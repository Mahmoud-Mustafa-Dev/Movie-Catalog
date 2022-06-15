package com.mahmoud.moviecatalog.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

open class BaseViewModel(private val coroutineDispatcher: CoroutineDispatcher): ViewModel() {

    private val initialized = AtomicBoolean(false)

    fun onResume() {
        if(initialized.compareAndSet(false, true)){
            setup()
        }
        resume()
    }

    protected fun <T> LiveData<T>.postValue(value: T) =
        (this as? MutableLiveData<T>)?.postValue(value) ?: run {}

    protected fun launchCoroutine(block: suspend () -> Unit) {
        val coroutineContext = (coroutineDispatcher)
        viewModelScope.launch(coroutineContext) { block.invoke() }
    }

    open fun setup() {}
    open fun resume() {}
}
