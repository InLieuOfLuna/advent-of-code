package adventofcode.year2022

import adventofcode.Solver

object CampCleanup: Solver(year = 2022, day = 4) {
    override fun part1(input: List<String>): String {
        return parse(input).sumOf { (first, second) ->
            if (first overlapsFully second || second overlapsFully first) 1 as Int else 0
        }.toString()
    }

    override fun part2(input: List<String>): String {
        return parse(input).sumOf { (first, second) ->
            if (first overlapsPartially  second || second overlapsPartially  first) 1 as Int else 0
        }.toString()
    }

    private fun parse(input: List<String>) = input.map { it.split(",").map(::toIntRange) }
    private fun toIntRange(string: String): IntRange {
        val (min, max) = string.split("-")
        return min.toInt()..max.toInt()
    }
    private infix fun IntRange.overlapsPartially(other: IntRange): Boolean {
        return contains(other.first) || contains(other.last)
    }
    private infix fun IntRange.overlapsFully(other: IntRange): Boolean {
        return contains(other.first) && contains(other.last)
    }
}