package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class RucksackReorganizationTest {

    val input = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent().lines()

    @Test
    fun part1() {
        assertEquals("157", RucksackReorganization.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("70", RucksackReorganization.part2(input))
    }
}