package html

import java.lang.StringBuilder

open class Tag(val name : String, var value: String = "")
{
    val children = mutableListOf<Tag>()
    fun add(tag: Tag)
    {
        children.add(tag)
    }

    // <table><tr><td>cell1</td></tr></table>
    override fun toString(): String {
        val result = StringBuilder()
        result.append("<$name>")
        result.append(value)
        children.forEach { result.append(it)}
        result.append("</$name>")
        return result.toString()
    }

    operator fun String.unaryPlus() {
        value = this
    }

}

class TABLE : Tag("table") {
    fun tr(block: TR.() -> Unit) = add(TR().apply(block))
}



class TR : Tag("tr"){
    fun td(block: TD.() -> Unit) = add(TD().apply(block))
}

class TD : Tag("td")

//fun table(block : TABLE.() -> Unit ) : TABLE = TABLE().apply(block)
fun html(block : HTML.() -> Unit ) : HTML = HTML().apply(block)

class HTML : Tag("html")
{
    fun table(block: TABLE.() -> Unit) = add(TABLE().apply(block))
    fun p(block: P.() -> Unit) = add(P().apply(block))
}
class P : Tag("p")

fun main()
{
    val html = html {
        table {
            tr {
                "color" to "blue"
                td {
                    +"cell1"
                }
                td {
                    +"cell2"
                }
            }
            tr {
                td {
                    +"cell3"
                }
                td {
                    +"cell4"
                }
            }
        }
        p {
            +"hello world!"
        }
    }

    println(html)

}