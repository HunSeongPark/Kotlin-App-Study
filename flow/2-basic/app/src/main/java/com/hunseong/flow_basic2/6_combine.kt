package com.hunseong.flow_basic2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

// combine : 두 flow에서 최신값을 기준으로 두 최신값을 병합하여 방출함

fun main() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
    val strs = flowOf("one", "two", "three").onEach { delay(400) } // strs every 400 ms
    val startTime = System.currentTimeMillis()
    nums.combine(strs) { a, b -> "$a -> $b"}
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

/* Log (각 flow에서 최신 값이 emit 될 때마다 collect 하여 결과 출력됨)
1 -> one at 444 ms from start
2 -> one at 649 ms from start
2 -> two at 847 ms from start
3 -> two at 951 ms from start
3 -> three at 1253 ms from start
 */