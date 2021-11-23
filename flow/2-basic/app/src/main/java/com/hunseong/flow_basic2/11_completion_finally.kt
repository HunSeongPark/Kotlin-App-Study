package com.hunseong.flow_basic2

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

// flow의 completion : 정상적으로 emit 완료, 중간에 exception 발생한 경우 모두 완료로 처리됨
// try-catch의 finally block을 이용한 방법

fun foo7(): Flow<Int> = (1..3).asFlow()

fun main() = runBlocking {
    try {
        foo7().collect { value -> println(value) }
    } finally {
        println("Done!")
    }
}

/* Log
1
2
3
Done!
 */