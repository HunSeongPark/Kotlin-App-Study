package com.hunseong.flow_basic2

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// conflate : collect 하는 중간값을 생략하여 collect의 속도를 높임
// collector가 값을 collect 하는 시점에서 emit 되어 있는 값 중 중간 값은 모두 버리고 최종적으로 마지막 값을 취함

fun foo3(): Flow<Int> = flow {
    for (i in 1..9) {
        kotlinx.coroutines.delay(100)
        emit(i)
        println("emit $i")
    }
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        foo3().conflate()
            .collect { value ->
                try {
                    delay(300)
                    println("Done $value")
                } catch (e: CancellationException) {
                    println("Cancelled $value")
                }
            }
    }
    println("Collected in $time ms")
}

/* Log
emit 1
emit 2
emit 3
Done 1
emit 4
emit 5
emit 6
Done 3
emit 7
emit 8
emit 9
Done 6
Done 9
 */
