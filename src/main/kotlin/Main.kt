import adventofcode.year2022.HillClimbingAlgorithm
import java.util.Scanner

fun main() {
    println(HillClimbingAlgorithm.part2(readLines()))
}

private fun readLines() = sequence {
    with(Scanner(System.`in`)) {
        while (hasNext()) yield(nextLine())
    }
}.toList()