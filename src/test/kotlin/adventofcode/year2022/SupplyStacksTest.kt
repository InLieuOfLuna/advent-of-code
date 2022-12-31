package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

object SupplyStacksTest: SolverTest(
    """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
"""
) {

    @Test
    fun part1() {
        assertEquals("CMZ", SupplyStacks.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("MCD", SupplyStacks.part2(input))
    }
}