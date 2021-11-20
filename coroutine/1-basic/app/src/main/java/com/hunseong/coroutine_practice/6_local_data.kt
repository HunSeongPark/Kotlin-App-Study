package com.hunseong.coroutine_practice

import kotlinx.coroutines.*


fun main() = runBlocking {
    val localData = ThreadLocal<String?>()

    println("before launch - ${localData.get()}") // null

    val job1 = launch {
        localData.asContextElement(value = "launch")
        println("job1 launch - ${localData.get()}") // null

        withContext(localData.asContextElement(value = "withContext")) {
            println("withContext - ${localData.get()}") // withContext

            launch {
                println("launch in withContext - ${localData.get()}") // withContext
            }
        }
        println("after WithContext - ${localData.get()}") // null

        localData.set("set after WithContext")
        println("set after withContext - ${localData.get()}") // after WithContext
    }

    job1.join()

    val job2 = launch(Dispatchers.Default + localData.asContextElement(value = "job2")) {
        println("job2 launch - ${localData.get()}") // job2
    }

    job2.join()
    val job3 = launch {
        println("job3 launch - ${localData.get()}") // after WithContext
    }

    job3.join()

    val job4 = launch(Dispatchers.Default) {
        localData.set("Default")
        println("job4 launch - ${localData.get()}") // Default
    }

    job4.join()

    val job5 = launch(Dispatchers.Default) {
        println("job5 launch - ${localData.get()}") // Default
    }

    job5.join()

    println("after launch - ${localData.get()}")
}