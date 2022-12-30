package adventofcode.year2022

abstract class SolverTest(val input: List<String>) {
    constructor(string: String) : this(string.trimIndent().lines())
}