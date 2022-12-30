import java.util.Scanner

fun main() {

}

private fun readLines() = sequence {
    with(Scanner(System.`in`)) {
        while (hasNext()) yield(next())
    }
}.toList()