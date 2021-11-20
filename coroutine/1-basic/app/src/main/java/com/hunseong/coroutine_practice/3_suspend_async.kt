package com.hunseong.coroutine_practice

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

suspend fun networkFunOne() : Int {
    delay(1000L)
    return 10
}

suspend fun networkFunTwo() : Int {
    delay(1000L)
    return 20
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = async { networkFunOne() }
        val two = async { networkFunTwo() }
        println("$1 + $2 = ${one.await()+two.await()}")
    }
    println("measure time : $time ms")
}