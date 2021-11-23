package com.hunseong.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking


// flow는 sequence와 같은 cold stream
// collect가 호출되기 전까지는 수행되지 않으며, 호출 할 때마다 처음부터 값을 전부 방출함

fun foo2(): Flow<Int> = flow {
    println("Flow Started")
    for (i in 1..3) {
        kotlinx.coroutines.delay(100)
        emit(i)
    }
}

fun main() = runBlocking {
    println("Calling foo2..")
    val flow = foo2()
    println("Calling collect..")
    flow.collect { value -> println(value) }
    println("Calling collect again..")
    flow.collect { value -> println(value) }
}

/* Log
Calling foo2..
Calling collect..
Flow Started
1
2
3
Calling collect again..
Flow Started
1
2
3
 */