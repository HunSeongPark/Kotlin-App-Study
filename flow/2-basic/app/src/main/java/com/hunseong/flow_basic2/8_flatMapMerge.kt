package com.hunseong.flow_basic2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

// flatMapMerge : 동시에 emit 가능한 값을 emit 시키고 하나의 flow로 병합하여 collect

fun requestFlow2(i: Int): Flow<String> = flow {
    emit("$i : First")
    kotlinx.coroutines.delay(500)
    emit("$i : Second")
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapMerge { requestFlow2(it) }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

/* Log (외부, 내부 flow가 동시에 수행되며 하나의 flow로 합쳐짐, 순서 보장 x)
1 : First at 171 ms from start
2 : First at 265 ms from start
3 : First at 367 ms from start
1 : Second at 672 ms from start
2 : Second at 766 ms from start
3 : Second at 874 ms from start
 */