package com.hunseong.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

// producer를 생성하는 함수를 확장함수로 만듦으로써 scope concurrency 보장, scope 내에서 관리 가능
fun CoroutineScope.produceNumbers(): ReceiveChannel<Int> = produce { // produce infinite stream
    var x = 1
    while (true) send(x++)
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce { //produce square(x) from numbers
        for (x in numbers) send(x * x)
    }

fun main() = runBlocking {
    val numbers = produceNumbers()
    val squares = square(numbers)
    for (i in 1..5) println(squares.receive())
    println("Done!")
    coroutineContext.cancelChildren() //cancel children (producers)
}

/* Log
1
4
9
16
25
Done!
 */