package com.simple.simpletestapp

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.simple.simpletestapp.utils.Event
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
fun <T> LiveData<T>.getLiveDataValue(
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    time: Long = 5,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@getLiveDataValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    try {
        afterObserve.invoke()
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("Live data not updated on time")
        }
    } finally {
        this.removeObserver(observer)
    }
    @Suppress("Unchecked_cast")
    return data as T
}

fun <T> LiveData<Event<T>>.getLiveContent(): T? {
    return this.getLiveDataValue().getContent()
}
