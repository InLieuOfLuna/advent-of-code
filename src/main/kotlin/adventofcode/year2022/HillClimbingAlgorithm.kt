package adventofcode.year2022

import adventofcode.BreadthFirstSearch
import adventofcode.Solver

object HillClimbingAlgorithm: Solver(year = 2022, day = 12) {
    override fun part1(input: List<String>): String {
        with(TopographicMap(input)) {
            return BreadthFirstSearch.shortestDistanceTo(
                { it.getAdjacentPositions() },
                start = start,
                goalTerminationCheck = end::equals
            ).toString()
        }
    }

    override fun part2(input: List<String>): String {
        with(TopographicMap(input)) {
            return BreadthFirstSearch.shortestDistanceTo(
                { it.getAdjacentPositions(true) },
                start = end,
                goalTerminationCheck = { it.char == 'a' || it.char == 'S' }
            ).toString()
        }
    }

    private class TopographicMap(private val input: List<String>) {
        val start by lazy { getAllCells().first { it.char == 'S' } }
        val end by lazy { getAllCells().first { it.char == 'E' } }
        private fun getAllCells() = sequence {
            input.forEachIndexed { y, line ->
                line.forEachIndexed { x, _ -> yield(Coordinate(x, y)) }
            }
        }

        infix fun Coordinate.canTravelTo(to: Coordinate): Boolean {
            if (z < to.z - 1) return false
            if (x == to.x) return (y-1..y+1).contains(to.y)
            if (y == to.y) return (x-1..x+1).contains(to.x)
            return false
        }

        fun Coordinate.getAdjacentPositions(inverted: Boolean = false): Set<Coordinate> {
            return setOf(
                Coordinate(x - 1, y),
                Coordinate(x + 1, y),
                Coordinate(x, y - 1),
                Coordinate(x, y + 1)
            ).filter {
                it.x >= 0 && it.y >= 0 && it.y < input.size && it.x < input[it.y].length
                        && (if(inverted) (it.canTravelTo(this)) else (canTravelTo(it)))
            }.toSet()
        }

        val Coordinate.char get() = input[y][x]
        val Coordinate.z get() = when(char) {
            'E' -> 25
            'S' -> 0
            else -> char - 'a'
        }
    }
    private data class Coordinate(val x: Int, val y: Int)
}