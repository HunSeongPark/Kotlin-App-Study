package com.hunseong.channel

import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// Ticker channel : 주어진 시간(delay)마다 Unit을 channel에 send하는 channel
// 특정 시간 단위로 produce를 해야하는 pipeline, operations에 활용


fun main() = runBlocking {
    val ticker = ticker(delayMillis = 1000, initialDelayMillis = 0) // Ticker Channel

    launch {
        for(i in ticker) {
            println("working! [time : ${System.currentTimeMillis()}]")
        }
    }

    delay(5000L)
    ticker.cancel()
}

/* Log (initialDealyMillis = 0이므로 시작하자마자 Unit send. 이후 1000ms마다 Unit을 send)
working! [time : 1637503841257]
working! [time : 1637503842245]
working! [time : 1637503843244]
working! [time : 1637503844240]
working! [time : 1637503845245]
working! [time : 1637503846245]
 */