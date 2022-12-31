package adventofcode.year2022

import adventofcode.Solver

object TuningTrouble: Solver(year = 2022, day = 6) {
    override fun part1(input: List<String>): String {
        return findFirstMarker(input.joinToString(""), 4).toString()
    }

    override fun part2(input: List<String>): String {
        return findFirstMarker(input.joinToString(""), 14).toString()
    }

    fun findFirstMarker(input: String, size: Int): Int {
        return input
            .windowed(size)
            .withIndex()
            .first { (_, value) -> value.toSet().size == size }
            .index + size
    }
}