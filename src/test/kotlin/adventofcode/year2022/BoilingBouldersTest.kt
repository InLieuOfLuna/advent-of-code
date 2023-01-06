package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BoilingBouldersTest {

    val input = """
        2,2,2
        1,2,2
        3,2,2
        2,1,2
        2,3,2
        2,2,1
        2,2,3
        2,2,4
        2,2,6
        1,2,5
        3,2,5
        2,1,5
        2,3,5
    """.trimIndent().lines()

    @Test
    fun part1() {
        assertEquals("64", BoilingBoulders.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("58", BoilingBoulders.part2(input))
    }
}