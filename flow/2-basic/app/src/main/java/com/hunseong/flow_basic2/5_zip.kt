package com.hunseong.flow_basic2

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

// zip : 두 개의 flow를 병합
// 두 flow의 원소 개수 중 적은 개수에 맞춰 병합됨
// 앞쪽 원소부터 차례로 병합됨

fun main() = runBlocking {
    val nums = (1..3).asFlow()
    val strs = flowOf("one", "two", "three", "four")
    nums.zip(strs) { a, b -> "$a -> $b" } // zip a single string
        .collect { println(it) }
}

/* Log
1 -> one
2 -> two
3 -> three
 */