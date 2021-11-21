package com.hunseong.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = Channel<Int>() //Channel 생성

    // channel send
    launch {
        for (x in 1..5) channel.send(x)
        channel.close()
    }

    // channel receive
    for (y in channel) println(y) // channel.close() 호출 시 for loop에 close token send되어 for loop 종료
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