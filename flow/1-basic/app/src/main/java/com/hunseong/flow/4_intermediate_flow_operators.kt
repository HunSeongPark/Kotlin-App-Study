package com.hunseong.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


// flow도 collection과 같이 map, filter 등의 중간 연산자 지원
// cold stream으로 동작
// suspend function은 아님. 빠르게 동작하여 새로운 flow를 반환
// 블럭 안에서 suspend function을 사용할 수 있다는 점이 큰 특징

suspend fun performRequest(request: Int) : String {
    delay(1000)
    return "response $request"
}

fun main() = runBlocking {
    (1..3).asFlow()
        .map { request -> performRequest(request) } // 새로운 flow로 변환
        .collect { response -> println(response) } // 변환된 flow에 대한 collect
}

/* Log
response 1
response 2
response 3
 */