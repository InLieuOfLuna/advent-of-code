package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class TreetopTreeHouseTest : SolverTest(
    """
        30373
        25512
        65332
        33549
        35390
    """
) {

    @Test
    fun part1() {
        assertEquals("21", TreetopTreeHouse.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("8", TreetopTreeHouse.part2(input))
    }
}