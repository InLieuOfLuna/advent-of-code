package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

object RockPaperScissorsTest: SolverTest(
    """
        A Y
        B X
        C Z
    """
) {

    @Test
    fun part1() {
        assertEquals("15", RockPaperScissors.part1(input))
    }

    @Test
    fun part2() {
    }
}