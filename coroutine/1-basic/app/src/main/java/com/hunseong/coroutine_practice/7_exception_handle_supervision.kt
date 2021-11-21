package com.hunseong.coroutine_practice

import kotlinx.coroutines.*
import java.io.IOException

fun main(): Unit = runBlocking {

    // Coroutine Exception Handler
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Handler caught exception : $exception")
    }


    //cancel이 부모 -> 자식으로만 전파됨. 자식 -> 부모로 전파 X
    //  = CoroutineScope(coroutineContext + SupervisorJob()).launch {}
    supervisorScope {
        val child = launch(handler) {

            throw IOException()
        }
    }
}