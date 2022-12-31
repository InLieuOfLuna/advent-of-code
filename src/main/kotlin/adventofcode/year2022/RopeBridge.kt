package adventofcode.year2022

import adventofcode.Solver
import java.lang.Exception

object RopeBridge : Solver(year = 2022, day = 9) {
    override fun part1(input: List<String>): String {
        val rope = Rope(2)
        input.forEach {
            val (direction, count) = it.split(" ")
            rope.step(direction, count.toInt())
        }
        return rope.visitedLocations.size.toString()
    }
    override fun part2(input: List<String>): String {
        val rope = Rope(10)
        input.forEach {
            val (direction, count) = it.split(" ")
            rope.step(direction, count.toInt())
        }
        return rope.visitedLocations.size.toString()
    }

    class Rope(size: Int) {
        val ropeParts = buildList { repeat(size) { add(0 to 0) } }.toMutableList()
        val visitedLocations = mutableSetOf(ropeParts.last())

        var head
            get() = ropeParts.first()
            set(value) { ropeParts[0] = value }
        val tail get() = ropeParts.last()
        fun step(direction: String, count: Int) = repeat(count) { step(direction) }
        private fun step(direction: String) {
            head = when (direction) {
                "U" -> head.copy(first = head.first + 1)
                "R" -> head.copy(second = head.second + 1)
                "D" -> head.copy(first = head.first - 1)
                "L" -> head.copy(second = head.second - 1)
                else -> throw Exception("Impossible direction")
            }
            ropeParts.indices.zipWithNext(::follow)
            visitedLocations.add(tail)
        }

        private fun follow(pos: Int, pos2: Int) {
            if(!(ropeParts[pos] isAdjacentTo ropeParts[pos2])) {
                if (ropeParts[pos2].first > ropeParts[pos].first) ropeParts[pos2] = ropeParts[pos2].copy(first = ropeParts[pos2].first - 1)
                if (ropeParts[pos2].first < ropeParts[pos].first) ropeParts[pos2] = ropeParts[pos2].copy(first = ropeParts[pos2].first + 1)
                if (ropeParts[pos2].second > ropeParts[pos].second) ropeParts[pos2] = ropeParts[pos2].copy(second = ropeParts[pos2].second - 1)
                if (ropeParts[pos2].second < ropeParts[pos].second) ropeParts[pos2] = ropeParts[pos2].copy(second = ropeParts[pos2].second + 1)
            }
        }


        infix fun Pair<Int, Int>.isAdjacentTo(other: Pair<Int, Int>): Boolean {
            return ((first - 1)..(first + 1)).contains(other.first)
                    && ((second - 1)..(second + 1)).contains(other.second)
        }
    }
}
