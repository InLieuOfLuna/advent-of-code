package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day12Test {

    val testInput = """
        Sabqponm
        abcryxxl
        accszExk
        acctuvwj
        abdefghi
    """.trimIndent().lines()

    @Test
    fun part1() {
        assertEquals("31", Day12.part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals("29", Day12.part2(testInput))
    }
}