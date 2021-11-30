package generics

open class A
{
    override fun toString(): String {
        return "A"
    }
}

class B : A() {
    override fun toString(): String {
        return "B"
    }
}


fun work(list: MutableList<Any>)
{
    list.add(42)
}


fun main()
{

//    val listOfB = listOf<B>(B(), B(), B())
//
//    val listOfA = listOfB
//
//    println(listOfA[0])


//     Type mismatch
//    val mutableListOfA : MutableList<A> = mutableListOf<B>(B(), B())
//     Type mismatch
//     val mutableListOfB : MutableList<B> = mutableListOf<A>(A(), A())



    // out - по ссылке мы будем только считывать
    val mutableListOfNumber : MutableList<out Number> = mutableListOf<Int>(1,2,3,4)
    val number : Number = mutableListOfNumber[0]
    println(
        "number is $number"
    )

    // нельзя изменять контейнер, так это нарущение out контракта
    //    mutableListOfNumber.add(5)
    //    mutableListOfNumber.add(3.44)
    //    mutableListOfNumber[2] = 45



    val mutableListOfA = mutableListOf<A>(A(), A())
    val mutableListOfB = mutableListOf<B>(B(), B())

    val producerOfA = Producer<A>()
    println(
        producerOfA.get(mutableListOfA, 0)
    )
    println(
        producerOfA.get(mutableListOfB, 0)
    )

    // in означает, что по ссылке мы может только записывать
    // при этом все операции чтения запрещены
    val mutableListOfInt : MutableList<in Int> = mutableListOf<Number>(1, 2L, 3.14)
    mutableListOfInt.add(55)
    mutableListOfInt[0] = 111

    var any : Any? = mutableListOfInt[2]
    println(any)
    any  = mutableListOfInt[1]
    println(any)
    any = mutableListOfInt[0]
    println(any)

    // запрещено контрактом
    // val int: Int = mutableListOfInt[0]
    // mutableListOfInt.add(55.55)


}
class Producer<T>
{
    fun get(list: MutableList<out T>, number: Int) : T {
        return list[number]
    }
}
class Consumer<T>
{
    fun calculate(t: T) : Int = TODO()
}