package com.hunseong.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
// channel은 Buffer를 가지고 있지 않으므로
// channel에서 send가 먼저 발생하면 channel은 receive가 올 때까지 suspend 상태
// channel에서 receive가 먼저 발생하면 channel은 send가 올 때까지 suspend 상태
// channel과 produce 모두 buffer size를 지정할 수 있는 capacity parameter를 optional로 가지고 있음
 */

fun main() = runBlocking {
    val channel = Channel<Int>(4) // buffered channel (4)
    val sender = launch {
        repeat(10) {
            println("Send $it")
            channel.send(it)
        }
    }

    delay(1000)
    sender.cancel()
}

/* Log (크기 4의 buffer를 가지고 있는 channel이므로 0,1,2,3까지는 suspend 되지 않고 buffer에 보관, 4부터는 buffer가 가득 찼으므로 send 후 suspend)
Send 0
Send 1
Send 2
Send 3
Send 4
 */

