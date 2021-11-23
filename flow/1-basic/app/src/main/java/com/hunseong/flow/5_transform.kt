package com.hunseong.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

// transform 연산자를 통해 값의 변환을 수행할 수 있음
// 임의의 값을 여러 번 반복해서 emit 하도록 할 수도 있음

suspend fun performRequest2(request: Int) : String {
    delay(1000)
    return "response $request"
}

fun main() = runBlocking {
    (1..3).asFlow()
        .transform { request ->
            emit("Making request $request")
            emit(performRequest2(request))
        }
        .collect { response -> println(response) }
}

/* Log
Making request 1
response 1
Making request 2
response 2
Making request 3
response 3
 */