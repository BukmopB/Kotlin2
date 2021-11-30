package dsl.person.hw

import dsl.person.v1.Human

data class Table (var tr: MutableList<Tr> = mutableListOf()) {
    override fun toString(): String {
        return "table {\n\t$tr\n}"
    }
}

data class Tr (var td: MutableList<Td> = mutableListOf()) {
    override fun toString(): String {
        return "tr {\n\t\t$td\n\t}"
    }
}

data class Td (var cell: String = "") {
    override fun toString(): String {
        return "td {\n\t\t\t$cell\n\t\t}"
    }
}

fun table (block: Table.() -> Unit) : Table = Table().apply(block)
fun Table.tr(block: Tr.() -> Unit) {
    tr.add(Tr().apply(block))
}
fun Tr.td(block: Td.() -> Unit) {
    td.add(Td().apply(block))
}

fun main() {
    val table = table {
        tr {
            td {
                cell = "cell1"
            }
            td {
                cell = "cell2"
            }
        }
        tr {
            td {
                cell = "cell3"
            }
            td {
                cell = "cell4"
            }
        }
    }

    println(table)
}