package com.stjepanbarisic.a3hnlistok.common.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

fun <T, R> LiveData<T>.map(mapperFunction: (T) -> R): LiveData<R> {
    return Transformations.map(this, mapperFunction)
}