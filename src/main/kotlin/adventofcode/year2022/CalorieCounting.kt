package adventofcode.year2022

import adventofcode.Solver

object CalorieCounting: Solver(year = 2022, day = 1) {
    override fun part1(input: List<String>): String {
        val highest = input
            .splitAt("")
            .maxOf { it.map(Integer::parseInt).sum() }
        return highest.toString()
    }

    override fun part2(input: List<String>): String {
        val sumOfThreeHighest = input
            .splitAt("")
            .map { it.map(Integer::parseInt).sum() }
            .sortedDescending()
            .subList(0, 3).sum()
        return sumOfThreeHighest.toString()
    }
    private fun <T> List<T>.splitAt(separator: T): List<List<T>> {
        val result = mutableListOf<List<T>>()
        var list = mutableListOf<T>()

        forEach { item ->
            if (item == separator) {
                result.add(list)
                list = mutableListOf()
            } else {
                list.add(item)
            }
        }
        if (list.size > 0) result.add(list)
        return result
    }
}