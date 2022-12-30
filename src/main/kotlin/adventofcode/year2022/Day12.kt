package adventofcode.year2022

import adventofcode.Solver
import java.util.LinkedList
import java.util.Queue

object Day12: Solver(year = 2022, day = 12) {
    override fun part1(input: List<String>): String {
        val map = TopographicMap(input)

        val steps = breadthFirstSearch(
            { with(map) { it.getAdjacentPositions() } },
            start = map.start,
            goalCheck = map.end::equals
        ).size - 1

        return steps.toString()
    }

    override fun part2(input: List<String>): String {
        val map = TopographicMap(input)
        val steps = breadthFirstSearch(
            { with(map) { it.getAdjacentPositions(true) } },
            start = map.end,
            goalCheck = { with(map) { it.char == 'a' || it.char == 'S' } }
        ).size - 1
        return steps.toString()
    }

    private fun <T> breadthFirstSearch(discover: (T) -> Set<T>, start: T, goalCheck: (T) -> Boolean): List<T> {
        val searchQueue: Queue<T> = LinkedList<T>().apply { add(start) }
        val breadcrumbs = mutableMapOf<T, T>()

        loop@while (searchQueue.isNotEmpty()) {
            val position = searchQueue.poll()
            val newSpots = discover(position)
                .filter { !breadcrumbs.containsKey(it) }
            for (spot in newSpots) {
                breadcrumbs[spot] = position
                if (goalCheck(spot)) break@loop
            }
            searchQueue.addAll(newSpots)
        }
        return breadcrumbs.toRoute(breadcrumbs.keys.first(goalCheck)) { it == start }
    }

    private fun <T> Map<T, T>.toRoute(start: T, endCheck: (T) -> Boolean): List<T> {
        var current = start
        val result = mutableListOf(current)
        while (!endCheck(current)) {
            current = get(current)!!
            result += current
        }
        return result
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
            if (x == to.x) {
                return (y-1..y+1).contains(to.y)
            }
            if (y == to.y) {
                return (x-1..x+1).contains(to.x)
            }
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