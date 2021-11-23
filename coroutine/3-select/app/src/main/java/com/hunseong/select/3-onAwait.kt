package com.hunseong.select

import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select
import kotlin.random.Random

// select.onAwait() : Deferred value를 갖는 여러 코루틴 중 가장 먼저 반환되는 하나를 선택할 수 있음

fun CoroutineScope.asyncString(time: Int) = async {
    delay(time.toLong())
    "Waited for $time ms"
}

fun CoroutineScope.asyncStringList(): List<Deferred<String>> {
    val random = Random(3)
    return List(12) { asyncString(random.nextInt(1000)) } //make random delay async
}

fun main() = runBlocking {
    val list = asyncStringList()
    val result = select<String> {
        list.withIndex().forEach { (index, deferred) ->
            deferred.onAwait { answer -> // await once most faster async
                "Deferred $index produced answer '$answer'"
            }
        }
    }
    println(result)
    val countActive = list.count { it.isActive } // count still active coroutines
    println("$countActive coroutines are still active")
    coroutineContext.cancelChildren()
}

/* Log
Deferred 6 produced answer 'Waited for 43 ms'
11 coroutines are still active
 */