package com.hunseong.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = Channel<Int>() //Channel 생성

    // channel send
    launch {
        for (x in 1..5) channel.send(x)
    }

    // channel receive
    repeat(5) { println(channel.receive()) }
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