package thread

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import kotlin.random.Random

fun main()
{
    println(
        Runtime.getRuntime().availableProcessors()
    )

    println(
        "main thread id is ${Thread.currentThread().id}"
    )


//    val thread = MyThread()
//    thread.start()
//    //thread.start()

    val runnable  = MyRunnable()

//    Thread(runnable).start()
//    Thread(runnable).start()
//    Thread(runnable).start()

    val o = Any()



    val service : ExecutorService = Executors.newFixedThreadPool(2)
    service.submit(MyRunnable())
    service.submit(MyRunnable())
    service.submit(MyRunnable())
    service.submit(MyRunnable())
    service.submit(MyRunnable())
    service.submit(MyRunnable())


    val result : Future<String> = service.submit(MyCallable())

    if(result.isDone)
    {
        // воспользоваться результатом
        println(result.get())
    }


    println(result.get())

    service.shutdown() // ожидать выполения всех заданий из очереди
    // service.shutdownNow() // не ждать обработки всех заданий в очереди

}

class MyThread : Thread()
{
    override fun run() {
        Thread.sleep(200)
        println("thread id is: ${Thread.currentThread().id}")
    }
}

class MyRunnable : Runnable {
    override fun run() {
        Thread.sleep(200)
        println("thread id is: ${Thread.currentThread().id}")
    }
}

class MyCallable : Callable<String> {
    override fun call(): String {
        Thread.sleep(Random(500).nextLong(0, 100))
        return "thread is ${Thread.currentThread().id}"
    }

}


