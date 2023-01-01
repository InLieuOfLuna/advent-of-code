package adventofcode.year2022

import adventofcode.Solver

object CalorieCounting: Solver(year = 2022, day = 1) {
    override fun part1(input: List<String>): String {
        return assessBags(input)
            .max()
            .toString()
    }

    override fun part2(input: List<String>): String {
        return assessBags(input)
            .sortedDescending()
            .subList(0, 3)
            .sum()
            .toString()
    }
    private fun assessBags(input: List<String>) = assessBags(input.joinToString("\n"))
    private fun assessBags(input: String): List<Int> {
        return input.trim()
            .split("\n\n")
            .map {
                it.lines()
                    .map(Integer::parseInt)
                    .sum()
            }
    }
}