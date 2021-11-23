package com.hunseong.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

// flow 자체에서는 cancel 함수를 지원하지 않음
// withTimeoutOrNull 또는 launch로 감싸서 cancel 작업 수행

fun foo3() : Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking {
    println("main start")

//    withTimeoutOrNull(250) {
//        foo3().collect { value -> println(value) }
//    }

    val fooLaunch = launch {
        foo3().collect { value -> println(value) }
    }

    delay(250)
    fooLaunch.cancel()

    println("main end")
}

/* Log
main start
Emitting 1
1
Emitting 2
2
main end
 */