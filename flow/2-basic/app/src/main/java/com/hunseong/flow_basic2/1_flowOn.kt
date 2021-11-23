package com.hunseong.flow_basic2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

// flow는 호출한 coroutine의 context에서 수행됨.
// flow의 context를 변경하기 위해서는 withContext가 아닌 flowOn을 사용

fun foo(): Flow<Int> = flow {
    for (i in 1..3) {
        Thread.sleep(100)
        log("Emitting $i")
        emit(i)
    }
}.flowOn(Dispatchers.Default) // Default Thread를 통해 Emit 수행

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")


fun main() = runBlocking {
    println("main start")
    foo().collect { log("Collected $it") }
    println("main end")
}

/* Log
main start
[DefaultDispatcher-worker-1] Emitting 1
[main] Collected 1
[DefaultDispatcher-worker-1] Emitting 2
[main] Collected 2
[DefaultDispatcher-worker-1] Emitting 3
[main] Collected 3
main end
 */