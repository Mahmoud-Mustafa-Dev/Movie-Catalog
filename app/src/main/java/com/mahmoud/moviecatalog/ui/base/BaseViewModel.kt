package com.mahmoud.moviecatalog.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicBoolean

open class BaseViewModel: ViewModel() {

    private val initialized = AtomicBoolean(false)

    fun onResume() {
        if(initialized.compareAndSet(false, true)){
            setup()
        }
        resume()
    }

    protected fun <T> LiveData<T>.postValue(value: T) =
        (this as? MutableLiveData<T>)?.postValue(value) ?: run {}

    open fun setup() {}
    open fun resume() {}
}
