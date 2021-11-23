package com.hunseong.flow_basic2

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

// flow의 completion : 정상적으로 emit 완료, 중간에 exception 발생한 경우 모두 완료로 처리됨
// onCompletion을 통한 명시적 종료 후 작업 선언
// nullable한 Throwable을 파라미터로 넘겨주므로 nullable 여부를 통해 collect의 정상 완료 여부를 판단 할 수 있음
// onCompletion의 경우, error를 handling 하지 않고 그대로 아래로 전달. 이후 catch를 통해 handling 권장
// catch operator와 동일하게 downstream에 대한 error는 확인하지 않음.

fun foo8(): Flow<Int> = flow {
    for (i in 1..3) {
        emit(1)
        throw RuntimeException()
    }
}

fun main() = runBlocking {
    foo8()
        .onCompletion { e -> if (e != null) println("Flow completed exceptionally $e") }
        .catch { e -> println("Caught exception $e") }
        .collect { value -> println(value) }
    println("Done!")
}

/* Log
1
Flow completed exceptionally java.lang.RuntimeException
Caught exception java.lang.RuntimeException
Done!
 */