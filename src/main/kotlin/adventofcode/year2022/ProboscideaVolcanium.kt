package adventofcode.year2022

import adventofcode.Solver
import adventofcode.common.BreadthFirstSearch

object ProboscideaVolcanium: Solver(year = 2022, day = 16) {

    private val valvePattern = Regex("Valve ([A-Z]+) has (.+)")
    override fun part1(input: List<String>): String {
        return TunnelMap.parse(input).max("AA", time = 30).toString()
    }

    override fun part2(input: List<String>): String {
        val tunnels = TunnelMap.parse(input)
        val valuableNodes = tunnels.tunnels.filter { it.value.flowRate > 0 }.map { it.key }
        return valuableNodes.toList().iterationsOf().maxOf { you ->
            tunnels.max("AA", valuableNodes.filterNot(you::contains).toSet(), time = 26)
             + tunnels.max("AA", you.toSet(), time = 26)
        }.toString()
    }

    fun <T> List<T>.iterationsOf(fromIncluding: Int = 0, count: Int = -1): Sequence<Set<T>> = sequence {
        if (size == fromIncluding) yield(emptySet())
        else when (count) {
            0 -> yield(emptySet())
            size - fromIncluding -> yield(this@iterationsOf.dropLast(count).toSet())
            else -> {
                yieldAll(iterationsOf(fromIncluding + 1, count - 1).map { it + get(fromIncluding) })
                yieldAll(iterationsOf(fromIncluding + 1, count))
            }
        }
    }

    fun graphTunnels(input: List<String>) = input.associate {
        valvePattern.matchEntire(it)?.let { result ->
            val (_, name, valve) = result.groupValues
            name to Valve.fromString(valve)
        } ?: throw Exception("Couldn't parse to Valve: $it")
    }.toMutableMap()

    class TunnelMap private constructor(val tunnels: Map<String, Valve>) {
        private val distances: MutableMap<String, Int> = mutableMapOf()

        fun max(from: String, explored: Set<String> = setOf(), time: Int): Int {
            val toExplore = tunnels
                .filter { (key, tunnel) -> !(tunnel.flowRate == 0 || explored.contains(key)) }
                .apply { if (isEmpty()) return 0 }

            return toExplore.maxOf { (it, valve) ->
                val remainingTime = time - ((from distanceTo it) + 1)
                if (remainingTime <= 0) return@maxOf 0
                remainingTime * valve.flowRate + max(it, explored + it, remainingTime)
            }
        }
        private infix fun String.distanceTo(other: String) = distances[listOf(this, other).sorted().joinToString()] ?: throw Exception("Connection not made: $this to $other")
        private fun setDistance(from: String, to: String, distance: Int) {
            if ((tunnels[to]?.flowRate ?: 0)*(tunnels[from]?.flowRate ?: 0) == 0 && (from != "AA" && to != "AA")) return
            distances[listOf(from, to).sorted().joinToString()] = distance
        }

        companion object {
            fun parse(input: List<String>): TunnelMap {
                val result = TunnelMap(graphTunnels(input))
                for ((from, valve) in result.tunnels) {
                    if (valve.flowRate == 0 && from != "AA") continue
                    val paths = BreadthFirstSearch.explore(
                        discover = { result.tunnels[it]?.tunnels ?: emptySet() },
                        start = from,
                        goalTerminationCheck = { false }
                    )
                    for (to in result.tunnels.keys) {
                        result.setDistance(to, from, BreadthFirstSearch.shortestDistanceTo(paths, from) { it == to })
                    }
                }
                return result
            }
        }
    }

    data class Valve(val flowRate: Int, val tunnels: Set<String>) {
        companion object {
            private val expected = Regex("flow rate=(\\d+); tunnels? leads? to valves? ([A-Z]+(?:, [A-Z]+)*)")
            fun fromString(string: String): Valve {
                return expected.matchEntire(string)?.let {
                    val (_, rate, tunnels) = it.groupValues
                    Valve(rate.toInt(), tunnels.split(", ").toSet())
                } ?: throw Exception("Couldn't parse into valve: $string")
            }
        }
    }
}