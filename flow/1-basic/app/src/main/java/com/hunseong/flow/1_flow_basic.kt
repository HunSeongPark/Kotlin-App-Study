package com.hunseong.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


// flow : 비동기로 여러 값을 계산이 끝날 때마다 즉시 반환하는 function을 만들 때 사용하는 coroutine builder
// flow는 non-blocking 형태로 thread를 block하지 않도록 구성할 수 있다.
// flow{} 구문의 return 값으로는 Flow가 반환됨
// flow도 builder 이므로 suspend 키워드 없이 생성 가능
// collect는 suspend


fun main() = runBlocking {
    println("main start")
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }
    foo().collect { value -> println(value) } // collect value
    println("main end")
}

fun foo(): Flow<Int> = flow {
    for (i in 1..3) {
        kotlinx.coroutines.delay(100) // non-block delay
        emit(i) // emit value
    }
}

/* Log (main thread가 blocking 되지 않고 asynchronous하게 실행됨)
main start
I'm not blocked 1
1
I'm not blocked 2
2
I'm not blocked 3
3
main end
 */