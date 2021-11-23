package com.hunseong.flow_basic2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

// launchIn : flow를 통해 특정 이벤트 발생 시 분리된 coroutine을 실행하여 동작하게 함으로써
// 이후 코드가 바로 실행될 수 있도록 함
// 인자로 launch 할 CoroutineScope가 들어감.
// launchIn은 Job을 return하므로 필요에 따라 scope와 별개로 flow를 취소 (cancel), flow 완료까지 대기 (join) 가능.

fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun main() = runBlocking {
    events()
        .onEach { event -> println("Listen Event : $event") }
        .launchIn(this)
    println("Done!")
}

/* Log
Done!
Listen Event : 1
Listen Event : 2
Listen Event : 3
 */