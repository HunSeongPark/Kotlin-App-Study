package com.hunseong.coroutine_practice

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i...")
                delay(500L)
            }
        } finally {
            println("이곳에서 리소스 정리 등을 수행")
            withContext(NonCancellable) {
                delay(1000L)
                println("job cancel 이후 수행되어야 할 suspend fun 사용")
            }
        }
    }
    delay(1300L)
    println("main: cancel!")
    job.cancelAndJoin()
    println("main : program end.")
}