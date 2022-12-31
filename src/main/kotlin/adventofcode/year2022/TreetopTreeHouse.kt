package adventofcode.year2022

import adventofcode.Solver

object TreetopTreeHouse: Solver(year = 2022, day = 8) {
    override fun part1(input: List<String>): String {
        val forest = Forest(input)
        return forest.all().count { (x, y) ->
            forest.isVisibleFromAnySide(x, y)
        }.toString()
    }

    override fun part2(input: List<String>): String {
        with(Forest(input)) {
            return all().maxOf { (x, y) -> totalViewingDistance(x, y) }
                .toString()
        }
    }
    class Forest(private val heightMap: List<String>) {
        fun all() = sequence {
            for (y in heightMap.indices) {
                for (x in heightMap[y].indices) yield(x to y)
            }
        }
        private fun height(x: Int, y: Int) = heightMap[y][x]
        fun totalViewingDistance(x: Int, y: Int): Int {
            val height = height(x, y)
            return surroundingTrees(x, y).map {
                viewingDistance(height, it)
            }.fold(1) { a, b -> a * b}
        }
        private fun viewingDistance(viewPointHeight: Char, heights: Iterable<Char>): Int {
            var count = 0
            for (treeHeight in heights) {
                count += 1
                if (viewPointHeight <= treeHeight) break
            }
            return count
        }
        fun isVisibleFromAnySide(x: Int, y: Int): Boolean {
            val testHeight = height(x, y)
            return surroundingTrees(x, y).map {
                it.all { height ->
                    testHeight > height
                }
            }.any { it }
        }
        fun surroundingTrees(x: Int, y: Int) = aboveTrees(x, y) + sideTrees(x, y)
        private fun aboveTrees(x: Int, y: Int) = heightMap.indices.split(y).map { it.map { y -> height(x, y) } }
        private fun sideTrees(x: Int, y: Int) = heightMap[y].indices.split(x).map { it.map { x -> height(x, y) } }
    }
    fun IntRange.split(position: Int) = buildList {
        add(position - 1 downTo  0)
        add((position+1)..endInclusive)
    }
}