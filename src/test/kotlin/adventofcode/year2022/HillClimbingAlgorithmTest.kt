package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class HillClimbingAlgorithmTest {

    val input = """
        Sabqponm
        abcryxxl
        accszExk
        acctuvwj
        abdefghi
    """.trimIndent().lines()

    @Test
    fun part1() {
        assertEquals("31", HillClimbingAlgorithm.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("29", HillClimbingAlgorithm.part2(input))
    }
}