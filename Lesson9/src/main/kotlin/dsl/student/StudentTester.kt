package dsl.student


data class Student(val name: String = "", val course: String ="", val grades: List<Double> = listOf())

// fun person (block: Person.()         -> Unit) : Person  = Person("", 0).apply (block)
   fun student(block: StudentBuilder.() -> Unit) : Student = StudentBuilder().apply(block).build() // Написать


class StudentBuilder {
//    var name   : String = ""
//    var course : String = ""
//    var grades : List<Double> = listOf()

    private val map = mutableMapOf<String, Any>() // название свойства -> значение свойства
    // map.get("course")
    var name   : String       by map
    var course : String       by map
    var grades : List<Double> by map

    fun build() : Student = Student(name, course, grades)
}


fun main()
{
    val student = student {
        name = "John Doe"
        course = "Kotlin#2"
        grades = listOf(4.4, 5.0)
    }

    val misha = Student().copy(name = "Misha")

    println(student)
    println(misha)
}
