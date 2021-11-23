package com.hunseong.select

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select

// select.onSend() : 여러 channel 중 먼저 send 가능한 channel에게 값을 send
// available한 channel이 여러 개일 경우 select 문에서 위에 선언한 channel이 우선

fun CoroutineScope.produceNumbers(secondCh: SendChannel<Int>) = produce<Int> {
    for (num in 1..10) {
        delay(100)
        select<Unit> {
            onSend(num) {}
            secondCh.onSend(num) {}
        }
    }
}

fun main() = runBlocking {
    val secondCh = Channel<Int>()
    launch {
        secondCh.consumeEach { println("Side Channel has $it") } // consume side channel
    }
    produceNumbers(secondCh).consumeEach { //consume main channel
        println("Main Channel has $it")
        delay(100) // block main channel 100 ms
    }
    println("Done!")
    coroutineContext.cancelChildren()
}


/* Log
Main Channel has 1
Side Channel has 2
Main Channel has 3
Side Channel has 4
Main Channel has 5
Side Channel has 6
Main Channel has 7
Side Channel has 8
Main Channel has 9
Done!
 */