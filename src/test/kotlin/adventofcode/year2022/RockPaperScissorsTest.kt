package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class RockPaperScissorsTest {

    val input = """
        A Y
        B X
        C Z
    """.trimIndent().lines()

    @Test
    fun part1() {
        assertEquals("15", RockPaperScissors.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("12", RockPaperScissors.part2(input))
    }
}