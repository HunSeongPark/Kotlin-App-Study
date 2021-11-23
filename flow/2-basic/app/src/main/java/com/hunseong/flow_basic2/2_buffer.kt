package com.hunseong.flow_basic2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


fun foo1(): Flow<Int> = flow {
    for (i in 1..3) {
        kotlinx.coroutines.delay(100)
        emit(i)
    }
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        foo1().buffer() // buffer를 통해 값을 생산하는 쪽과 소비하는 쪽의 작업을 분리함으로써 processing time 단축
            .collect { value ->
                delay(300)
                println(value)
            }
    }

    println("Collected in $time ms")
}

/* Log (최초 emit(100ms) + 1,2,3 collect(300*3 ms)) = 1000ms
1
2
3
Collected in 1074 ms
 */