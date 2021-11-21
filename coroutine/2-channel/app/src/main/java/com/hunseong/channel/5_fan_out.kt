package com.hunseong.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Fan-out : 하나의 Channel에 여러 coroutine이 값을 receive. 송신자와 수신자가 1:n
// 하나의 일을 여러 coroutine이 나눠서 할 수 있음


fun CoroutineScope.produceNumberr() = produce { // infinite produce number 1..
    var x = 1
    while (true) {
        send(x++)
        delay(100)
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch { // launch and receive

    // Fan-out 시 for loop를 통해 data를 소비하는 것이 안전함
    // consumeEach 사용 시, coroutine 중 하나가 소비에 실패하면 channel이 닫히게 되고 모든 코루틴의 수행이 종료됨
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}

fun main() = runBlocking {
    val producer = produceNumberr()
    repeat(5) { launchProcessor(it, producer) } // 1 producer and 5 receiver
    delay(950)
    producer.cancel() // cancel producer
}

/* Log (순서가 보장되며 각 coroutine이 나눠서 receive)
Processor #0 received 1
Processor #0 received 2
Processor #1 received 3
Processor #2 received 4
Processor #3 received 5
Processor #4 received 6
Processor #0 received 7
Processor #1 received 8
Processor #2 received 9
Processor #3 received 10
 */