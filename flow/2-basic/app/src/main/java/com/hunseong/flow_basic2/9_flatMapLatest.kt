package com.hunseong.flow_basic2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

// flatMapLatest : collectLatest와 유사. flow에서 emit 발생 시 이전에 동작, 대기중인 flow cancel

fun requestFlow3(i: Int): Flow<String> = flow {
    emit("$i : First")
    kotlinx.coroutines.delay(500)
    emit("$i : Second")
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapLatest { requestFlow3(it) }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

/* Log (delay에 의해 마지막 flow 제외 Second 작업 취소됨)
1 : First at 192 ms from start
2 : First at 301 ms from start
3 : First at 403 ms from start
3 : Second at 906 ms from start
 */