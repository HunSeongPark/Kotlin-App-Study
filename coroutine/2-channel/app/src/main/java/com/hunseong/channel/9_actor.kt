package com.hunseong.channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlin.system.measureTimeMillis

// actor : state를 통해 Thread-Handler 패턴과 유사하게 동기화 제어가 가능
// actor scope 내에서 channel의 send/receive를 처리할 수 있음

suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        val jobs = List(n) { // action 100 * 1000 times
            launch {
                repeat(k) { action() }
            }
        }
        jobs.forEach { it.join() } // run sequentially
    }
    println("Completed ${n * k} actions in $time ms")
}

sealed class CounterMsg { // actor state sealed class
    object IncCounter : CounterMsg()
    data class GetCounter(val response : CompletableDeferred<Int>) : CounterMsg()
}

fun CoroutineScope.counterActor() = actor<CounterMsg> {
    var counter = 0
    for (msg in channel) { // receive message from channel
        when (msg) {
            is CounterMsg.IncCounter -> counter++
            is CounterMsg.GetCounter -> msg.response.complete(counter) // send result
        }
    }
}


fun main(): Unit = runBlocking {
    val counter = counterActor()
    GlobalScope.massiveRun {
        counter.send(CounterMsg.IncCounter)
    }
    val response = CompletableDeferred<Int>()
    counter.send(CounterMsg.GetCounter(response))
    println("Count : ${response.await()}") // get result
    counter.close()
}