package adventofcode.year2022

import adventofcode.Solver
import adventofcode.common.BreadthFirstSearch
import kotlin.math.abs

object BoilingBoulders: Solver(year = 2022, day = 18) {
    override fun part1(input: List<String>): String {
        return getRocks(
            input.map(Point::rockFromString).toSet()
        ).sumOf { it.getInnerArea() }.toString()
    }

    override fun part2(input: List<String>): String {
        val segments = input.map(Point::rockFromString).toSet()
        return getRocks(segments).sumOf { it.getOuterArea() }.toString()
    }

    private fun getRocks(points: Set<Point>) = sequence {
        val remaining = points.toMutableSet()
        while (remaining.isNotEmpty()) {
            val start = remaining.first()
            val explored = BreadthFirstSearch.explore(
                discover = { point ->
                    remaining.filter(point::cornerTouching).toSet()
                },
                start = start
            )
            val rock = explored.keys + start
            remaining.removeAll(rock)
            yield(Rock(rock))
        }
    }


    private class Rock(val rockSegments: Set<Point>, val bounds: Bounds = Bounds(
        min = Point(rockSegments.minOf { it.x }, rockSegments.minOf { it.y }, rockSegments.minOf { it.z }),
        max = Point(rockSegments.maxOf { it.x }, rockSegments.maxOf { it.y }, rockSegments.maxOf { it.z })
    )) {
        fun getInnerArea(): Int {
            return rockSegments.sumOf { test ->
                test.surrounding().filter { !rockSegments.contains(it) }.size
            }
        }
        fun getOuterArea(): Int {
            val start =
                rockSegments
                .first(bounds::atEdge).surrounding()
                .first(bounds::doesNotContain) // Gets air touching the rock (a known starting point)
            val explored = BreadthFirstSearch.allAccessible(
                discover = { from ->
                    from.surrounding().filter { test ->
                        !rockSegments.contains(test) && rockSegments.any(test::cornerTouching)
                    }.toSet()
                },
                start = start
            )
            return explored.sumOf { rockSegments.filter(it::sidesTouching).size }
        }
    }
    data class Bounds(val min: Point, val max: Point) {
        fun atEdge(point: Point): Boolean {
            return point.x == min.x || point.x == max.x
                    || point.y == min.y || point.y == max.y
                    || point.z == min.z || point.z == max.z
        }
        fun contains(point: Point): Boolean {
            return point.x in min.x..max.x
                    && point.y in min.y..max.y
                    && point.z in min.z..max.z
        }
        fun doesNotContain(point: Point) = !contains(point)
    }
    data class Point(val x: Int, val y: Int, val z: Int) {
        private fun adjacentTo(other: Point, axisOfAcceptableDifference: Int): Boolean {
            val sideChecks = arrayOf(abs(x - other.x), abs(y - other.y), abs(z - other.z))
            return (sideChecks.sum() <= axisOfAcceptableDifference) && sideChecks.none { it > 1 }
        }

        fun sidesTouching(other: Point) = adjacentTo(other, 1)
        fun edgeTouching(other: Point) = adjacentTo(other, 2)
        fun cornerTouching(other: Point) = adjacentTo(other, 3)

        fun surrounding() = setOf(
            copy(x = x - 1),
            copy(x = x + 1),
            copy(y = y - 1),
            copy(y = y + 1),
            copy(z = z - 1),
            copy(z = z + 1)
        )
        companion object {
            fun rockFromString(input: String): Point {
                val (x, y, z) = input.split(",").map(Integer::parseInt)
                return Point(x, y, z)
            }
        }
    }
}