package com.hunseong.channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.produce

// Fan-in : 하나의 Channel에 여러 coroutine이 값을 send. 송신자와 수신자가 n:1

suspend fun sendString(channel: SendChannel<String>, str: String, time: Long) { // cancel 전까지 무한으로 channel에 str send
    while (true) {
        delay(time)
        channel.send(str)
    }
}

fun main() = runBlocking {
    val channel = Channel<String>()
    launch { sendString(channel, "foo", 200L) }
    launch { sendString(channel, "BAR!", 500L) }
    repeat(6) {
        println(channel.receive()) // channel에서 send가 올 때까지 suspend 후 receive를 수행하므로 6번의 receive 완료 후 cancel됨
    }
    coroutineContext.cancelChildren()
}

/* Log
foo
foo
BAR!
foo
foo
BAR!
 */