package com.hunseong.flow_basic2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

// flatMap : Flow<Flow<String>>과 같이 중첩된 flow의 상태를 flat하게 만드는 operator
// flatMapConcat : 내부 flow 완료 후에 외부 collect 수행

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i : First")
    kotlinx.coroutines.delay(500)
    emit("$i : Second")
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapConcat { requestFlow(it) }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

/* Log (내부 flow 완료 되어야만 외부 flow에서 값을 collect)
1 : First at 142 ms from start
1 : Second at 642 ms from start
2 : First at 747 ms from start
2 : Second at 1253 ms from start
3 : First at 1357 ms from start
3 : Second at 1857 ms from start
 */