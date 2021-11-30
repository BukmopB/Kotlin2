package json

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

fun obj(block: JsonObjectBuilder.() -> Unit) = JsonObjectBuilder().apply (block).res
fun array(block: JsonArrayBuilder.() -> Unit) = JsonArrayBuilder().apply (block).res

class JsonArrayBuilder{
    val res = JsonArray()
    operator fun String.unaryPlus()
    {
        res.add(this)
    }
}

class JsonObjectBuilder
{
    val res = JsonObject()
    infix fun String.to(value : String)
    {
        res.addProperty(this, value)
    }
    infix fun String.to(value: JsonElement)
    {
        res.add(this, value)
    }
}


fun main()
{
    val json = obj {
        "course" to "Kotlin2"
        "provider" to "Specialist"
        "date" to obj {
            "from" to "9.11.2020"
            "to"   to "18.11.2020"
        }
        "names" to array {
            +"Ivan Petrov"
            +"Maria Pavlova"
        }
    }

    println(json)
}