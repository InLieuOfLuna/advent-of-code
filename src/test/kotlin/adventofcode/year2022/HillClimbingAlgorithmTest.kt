package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

object HillClimbingAlgorithmTest: SolverTest(
    """
        Sabqponm
        abcryxxl
        accszExk
        acctuvwj
        abdefghi
    """
) {
    @Test
    fun part1() {
        assertEquals("31", HillClimbingAlgorithm.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("29", HillClimbingAlgorithm.part2(input))
    }
}