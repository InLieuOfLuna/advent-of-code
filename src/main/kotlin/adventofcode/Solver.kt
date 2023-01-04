package adventofcode

abstract class Solver(val year: Int, val day: Int) {
    abstract fun part1(input: List<String>): String
    abstract fun part2(input: List<String>): String
}