package com.hunseong.coroutine_practice

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

fun main() = runBlocking {

    val job = GlobalScope.launch {
        delay(1000L)
        println("Coroutine.")
    }
    println("Hello,")
    job.join()
}