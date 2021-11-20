package com.hunseong.coroutine_practice

import kotlinx.coroutines.*


fun main() = runBlocking<Unit> {
    launch {
        println("non-dispatcher(parent's thread) - ${Thread.currentThread().name}")
    }

    // 처음 thread는 호출자의 thread
    // suspend function 수행 이후는 적절한 thread
    launch(Dispatchers.Unconfined) {
        println("Dispatchers.Unconfined - ${Thread.currentThread().name}")
        delay(500)
        println("Dispatchers.Unconfined(after suspend fun) - ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) {
        println("Dispatchers.Default - ${Thread.currentThread().name}")
    }

    // 경량 스레드를 사용하는 코루틴의 장점이 사라짐.
    launch(newSingleThreadContext("myThread")) {
        println("newSingleThreadContext - ${Thread.currentThread().name}")
    }


}
