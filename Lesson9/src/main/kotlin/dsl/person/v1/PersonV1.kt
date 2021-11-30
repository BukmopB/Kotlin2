package dsl.person.v1

class Human(var name: String = "", var age: Int = 0, var addresses: MutableList<Address> = mutableListOf())
{
    override fun toString(): String {
        return "Human $name $age $addresses"
    }
}

data class Address (var street: String = "", var number: Int = 0, var city: String = "")

fun human        (block: Human.()   -> Unit) : Human = Human().apply(block)
fun Human.address(block: Address.() -> Unit) : Unit  // Написать - цель - добавить адреса в экземпляр Human
// Создать адрес, выполнить для него блок, добавить адрес в адреса в экземпляре human
{
//    val address = Address()
//    address.apply(block)
//    addresses.add(address)
    addresses.add(Address().apply(block))
}


fun main()
{

    val person = human {
        name = "John"
        age = 25
        address {
            street = "Main street"
            number = 42
            city = "Oklahoma"
        }
        address {
            street = "Tverskaya"
            number = 4
            city = "Moscow"
        }
    }


    println(
        person
    )


}