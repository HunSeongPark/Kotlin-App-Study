package com.hunseong.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

// flow들은 multiple flow로 동작하도록 하는 operator를 사용하지 않는 한 순차적으로 동작함
// 기본적으로 terminal operator를 호출하는 coroutine에서 바로 수행. 새로운 coroutine 생성하지 않음

fun main() = runBlocking {
    (1..5).asFlow()
        .filter {
            println("Filter $it")
            it % 2 == 0
        }
        .map {
            println("Map $it")
            "string $it"
        }
        .collect {
            println("Collect $it")
        }
}

/* Log
Filter 1
Filter 2
Map 2
Collect string 2
Filter 3
Filter 4
Map 4
Collect string 4
Filter 5
 */