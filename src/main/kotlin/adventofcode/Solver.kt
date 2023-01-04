package adventofcode

import java.util.*

abstract class Solver(val year: Int, val day: Int) {
    abstract fun part1(input: List<String>): String
    abstract fun part2(input: List<String>): String
}

object BreadthFirstSearch {

    fun <T> shortestDistanceTo(discover: (T) -> Set<T>, start: T, goalTerminationCheck: (T) -> Boolean): Int {
        return shortestDistanceTo(explore(discover, start, goalTerminationCheck), start, goalTerminationCheck)
    }
    fun <T> shortestDistanceTo(breadcrumbs: Map<T, T>, start: T, goalTerminationCheck: (T) -> Boolean): Int {
        return breadcrumbs.toRoute(breadcrumbs.keys.first(goalTerminationCheck)) { it == start }.size - 1
    }

    fun <T> canFind(discover: (T) -> Set<T>, start: T, goalTerminationCheck: (T) -> Boolean = { false }): Boolean {
        return allAccessible(discover, start, goalTerminationCheck).any(goalTerminationCheck)
    }
    fun <T> allAccessible(discover: (T) -> Set<T>, start: T, goalTerminationCheck: (T) -> Boolean = { false }) = explore(discover, start, goalTerminationCheck).keys + start

    fun <T> explore(discover: (T) -> Set<T>, start: T, goalTerminationCheck: (T) -> Boolean = { false }): Map<T, T> {
        val searchQueue: Queue<T> = LinkedList<T>().apply { add(start) }
        val breadcrumbs = mutableMapOf<T, T>()

        loop@while (searchQueue.isNotEmpty()) {
            val position = searchQueue.poll()
            val newSpots = discover(position)
                .filter { !breadcrumbs.containsKey(it) }
            for (spot in newSpots) {
                breadcrumbs[spot] = position
                if (goalTerminationCheck(spot)) break@loop
            }
            searchQueue.addAll(newSpots)
        }
        return breadcrumbs
    }

    fun <T> Map<T, T>.toRoute(start: T, endCheck: (T) -> Boolean): List<T> {
        var current = start
        val result = mutableListOf(current)
        while (!endCheck(current)) {
            current = get(current)!!
            result += current
        }
        return result
    }
}