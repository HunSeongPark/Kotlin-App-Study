package com.hunseong.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceNumber(): ReceiveChannel<Int> = produce(Dispatchers.Default) { // 다른 Thread (Default)에서 produce 수행
    for (x in 1..5) send(x)
}

fun main() = runBlocking {
    val numbers = produceNumber() // producer

    numbers.consumeEach { //consumer
        println(it)
    }
    println("Done!")
}

/* Log
1
2
3
4
5
Done!
 */