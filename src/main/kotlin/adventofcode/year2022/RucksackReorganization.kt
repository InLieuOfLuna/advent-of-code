package adventofcode.year2022

import adventofcode.Solver

object RucksackReorganization: Solver(year = 2022, day = 3) {
    override fun part1(input: List<String>): String {
        return input.map { it.splitInHalf().map(String::toSet) }
            .sumOf { (first, second) -> first.intersect(second).first().priority }
            .toString()
    }

    override fun part2(input: List<String>): String {
        return input.withIndex().groupBy(
            keySelector = { it.index / 3 },
            valueTransform = { it.value }
        ).values.sumOf {
            val (first, second, third) = it.map(String::toSet)
            first.intersect(second).intersect(third).first().priority
        }.toString()
    }

    private fun String.splitInHalf(): Array<String> {
        return arrayOf(substring(0, length / 2), substring(length / 2, length))
    }

    private val Char.priority get() = when (this) {
        in 'a'..'z' -> code - 'a'.code + 1
        in 'A'..'Z' -> code - 'A'.code + 27
        else -> TODO()
    }
}