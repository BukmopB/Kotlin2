package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Consumer

fun callApi() : String {
    Thread.sleep(2_000)
    return "hello"
}


fun main() {

    Observable.fromCallable { callApi() }.subscribe(
        ::println
    )


    val names = Observable.just("Borya", "Max", "Svetlana", "Michael")
    // только имена содержащие M
    // перевести в верхний регистр

    names.filter{it.contains('M')}.map { it.toUpperCase() }.subscribe(
        ::println
    )

    val stringConsumer = Consumer<String> { println(it)}
    val errorConsumer  = Consumer<Throwable> { println(it)}
    val endAction      = Action { println("ended")}

    val subscription =
        names
            .filter { it.length > 4 }
            .subscribe(
            stringConsumer,
            errorConsumer,
            endAction
        )

    val numbers = listOf(1, 2, 10, 30, 55, 0, 100)
    Observable.fromIterable(numbers)
        .map { 1000/it }
        .subscribe(
            ::println,
            ::println
        )




}