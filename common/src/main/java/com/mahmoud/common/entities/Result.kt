package com.mahmoud.common.entities

import android.app.Activity
import com.mahmoud.common.error.DefaultErrorHandler

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

fun <T> Result<T>.getOrDefault(default: T) = when (this) {
    is Result.Success -> this.value
    else -> default
}

fun <T> Result<T>.getOrNull() = when (this) {
    is Result.Success -> this.value
    else -> null
}


inline fun <T, R : T> Result<T>.getOrElse(block: (error: Throwable?) -> R) = when (this) {
    is Result.Success -> this.value
    is Result.Error -> block(this.error)
    else -> block(null)
}

fun <T> Result<T>.isSuccess() = when (this) {
    is Result.Success -> true
    else -> false
}

fun <T> Result<T>.isLoading() = when (this) {
    is Result.Loading -> true
    else -> false
}

fun <T> Result<T>.handleWith(
    activity: Activity,
    success: (value: T) -> Unit,
    error: ((error: Throwable) -> Unit)? = null,
    loading: (() -> Unit)? = null
) = when (this) {
    is Result.Success -> success(this.value)
    is Result.Error -> error?.invoke(this.error) ?: DefaultErrorHandler.handleError(activity, this.error)
    is Result.Loading -> loading?.invoke()
}

