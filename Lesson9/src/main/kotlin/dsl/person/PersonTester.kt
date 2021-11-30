package dsl.person

data class Person(var name : String, var age: Int)
{
    val nicknames = mutableListOf<String>()
    operator fun String.unaryPlus() {
        nicknames.add(this)
    }

    override fun toString(): String {
        return "Person $name $age $nicknames"
    }
}

fun person(block: Person.() -> Unit) : Person = Person("", 0).apply (block)

fun main()
{

    // person - функция, принимающая на вход функцию расширения для Person
    // и возвращает новый экземпляр Person
    val max = person {
        name = "Max Kotkov"
        age = 28
        +"Сказочник" // String.unaryPlus()
        +"Человечек"
    }

    println(
        max
    )

}
