package com.hunseong.flow_basic2

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

// catch : flow 내에서 발생한 exception을 catch 할 수 있음
// emitter 내에서 발생한 exception은 catch로 잡음
// collect 내에서 발생한 exception은 catch 위에 onEach로 body를 옮겨 작성하여 잡음

fun foo6(): Flow<Int> = flow {
    for (i in 1..3) {
        println("emit $i")
        emit(i)
    }
}

fun main() = runBlocking {
    foo6()
        .onEach { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
        .catch { e -> println("이곳에서 $e 에 대한 처리 수행") }
        .collect() //collect start
}

/* Log
emit 1
1
emit 2
이곳에서 java.lang.IllegalStateException: Collected 2 에 대한 처리 수행
 */