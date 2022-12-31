package adventofcode.year2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class NoSpaceLeftOnDeviceTest : SolverTest(
    """
        ${'$'} cd /
        ${'$'} ls
        dir a
        14848514 b.txt
        8504156 c.dat
        dir d
        ${'$'} cd a
        ${'$'} ls
        dir e
        29116 f
        2557 g
        62596 h.lst
        ${'$'} cd e
        ${'$'} ls
        584 i
        ${'$'} cd ..
        ${'$'} cd ..
        ${'$'} cd d
        ${'$'} ls
        4060174 j
        8033020 d.log
        5626152 d.ext
        7214296 k
    """
) {

    @Test
    fun part1() {
        assertEquals("95437", NoSpaceLeftOnDevice.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("24933642", NoSpaceLeftOnDevice.part2(input))
    }
}