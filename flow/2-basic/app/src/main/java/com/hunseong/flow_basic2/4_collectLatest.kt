package com.hunseong.flow_basic2

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// collectLatest : conflate와 달리 collector가 받은 emit 값을 처리 전 새로운 emit 값이 들어오면 기존 동작 취소


fun foo4(): Flow<Int> = flow {
    for (i in 1..9) {
        kotlinx.coroutines.delay(100)
        emit(i)
    }
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        foo4()
            .collectLatest { value ->
                try {
                    println("Collect $value")
                    delay(300)
                    println("Done $value")
                } catch (e: CancellationException) {
                    println("Cancelled $value")
                }
            }
    }
    println("Collected in $time ms")
}

/* Log (Delay에 의해 마지막 emit을 제외하고 Done 수행 전 다음 emit으로 인해 작업 취소됨)
Collect 1
Cancelled 1
Collect 2
Cancelled 2
Collect 3
Cancelled 3
Collect 4
Cancelled 4
Collect 5
Cancelled 5
Collect 6
Cancelled 6
Collect 7
Cancelled 7
Collect 8
Cancelled 8
Collect 9
Done 9
Collected in 1284 ms
 */
