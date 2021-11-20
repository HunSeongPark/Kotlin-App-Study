package com.hunseong.coroutine_practice

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Activity : CoroutineScope {

    private lateinit var job: Job

    fun create() {
        job = Job()
    }

    fun destroy() {
        job.cancel()
    }

    // 해당 클래스의 기본 Coroutine Context + 생명주기에 맞춰 job 요소 추가
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    fun doWorking() {
        repeat(10) { i ->
            launch {
                try {
                    delay((i * 2) * 100L)
                    println("Work $i is Complete")
                } catch (e: CancellationException) {
                    println("Work $i is Cancelled")
                }
            }
        }
    }
}

fun main() = runBlocking {
    val mainActivity = Activity()

    mainActivity.create() // onCreate
    mainActivity.doWorking() // work
    delay(500)
    println("activity destroy")
    mainActivity.destroy() //onDestroy
    delay(1000)
}