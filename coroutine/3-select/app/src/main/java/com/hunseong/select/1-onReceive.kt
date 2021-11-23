package com.hunseong.select

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

// select.onReceive() : 여러 channel 중 먼저 receive 가능한 channel의 값을 뽑아냄

fun CoroutineScope.fizz() = produce {
    while (true) {
        delay(300)
        send("fizz")
    }
}

fun CoroutineScope.buzz() = produce {
    while (true) {
        delay(500)
        send("BUZZ!!")
    }
}

suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
    select<Unit> {
        fizz.onReceive {
            println("fizz -> $it")
        }
        buzz.onReceive {
            println("buzz -> $it")
        }
    }
}

fun main() = runBlocking {
    val fizz = fizz()
    val buzz = buzz()
    repeat(7) { selectFizzBuzz(fizz, buzz) }
    coroutineContext.cancelChildren()
}

/* Log
fizz -> fizz
buzz -> BUZZ!!
fizz -> fizz
fizz -> fizz
buzz -> BUZZ!!
fizz -> fizz
buzz -> BUZZ!!
 */