package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CalorieCountingTest {
    val input = """
        1000
        2000
        3000

        4000

        5000
        6000

        7000
        8000
        9000

        10000
    """.trimIndent().lines()

    @Test
    fun part1() {
        assertEquals("24000", CalorieCounting.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("45000", CalorieCounting.part2(input) )
    }
}