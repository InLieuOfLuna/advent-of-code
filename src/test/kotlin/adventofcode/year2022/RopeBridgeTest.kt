package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class RopeBridgeTest : SolverTest(
    """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
    """
) {

    @Test
    fun part1() {
        assertEquals("13", RopeBridge.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("1", RopeBridge.part2(input))
    }
}