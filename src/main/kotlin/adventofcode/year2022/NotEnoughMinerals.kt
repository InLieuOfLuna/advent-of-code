package adventofcode.year2022

import adventofcode.Solver
import kotlin.math.ceil

object NotEnoughMinerals: Solver(year = 2022, day = 19) {
    override fun part1(input: List<String>): String {
        TODO("Not yet implemented")
    }

    override fun part2(input: List<String>): String {
        TODO("Not yet implemented")
    }

    data class State(
        val resources: Map<Mineral, Int> = emptyMap(),
        val robots: Map<Mineral, Int> = mapOf(Mineral.Ore to 1),
        val time: Int
    ) {
        val endState by lazy { resourcesAfter(time) }
        fun potentialNextStates(blueprint: Blueprint) = sequence {
            for ((mineral, recipe) in blueprint) {
                if (!recipe.canCraftWith(endState)) continue
                val timeCost = timeToAfford(recipe)
                yield(
                    State(
                        resources = resourcesAfter(timeCost)
                            .mapValues { (mineral, current) -> current - (recipe[mineral] ?: 0) }, // remove cost of recipe
                        robots = robots.toMutableMap().apply { this[mineral] = (this[mineral] ?: 0) + 1 },
                        time = time - timeCost
                    )
                )
            }
        }
        fun resourcesAfter(time: Int) = resources.mapValues { (mineral, count) -> count + (robots[mineral] ?: 0) * time }
        fun timeToAfford(recipe: Recipe): Int {
            return recipe.maxOf { (mineral, count) ->
                (count - (resources[mineral] ?: 0)) ceilDiv robots[mineral]!!
            } + 1
        }
        private infix fun Int.ceilDiv(other: Int) = ceil(toDouble().div(other)).toInt()
    }

    class Blueprint(vararg values: Pair<Mineral, Recipe>): Map<Mineral, Recipe> by mapOf(*values) {

        fun maxOf(state: State, mineral: Mineral) = iterationsOf(state).maxOf {
            it.endState[mineral] ?: 0
        }

        fun iterationsOf(state: State): Sequence<State> = sequence {
            val next = state.potentialNextStates(this@Blueprint)
            if (next.any()) {
                next.forEach { yieldAll(iterationsOf(it)) }
            } else yield(state)
        }

    }
    class Recipe(vararg values: Pair<Mineral, Int>): Map<Mineral, Int> by mapOf(*values) {
        fun canCraftWith(resources: Map<Mineral, Int>): Boolean {
            return all { (mineral, required) -> (resources[mineral] ?: 0) >= required }
        }
    }
    enum class Mineral { Ore, Clay, Obsidian, Geode }
}