package adventofcode.year2022

import adventofcode.Solver
import java.util.*

object SupplyStacks: Solver(year = 2022, day = 5) {
    override fun part1(input: List<String>): String {
        val (serializedStacks, instructions) = input.splitAt("")
        val crane = CrateMover9000().apply { loadCrates(serializedStacks) }
        for (line in instructions.filter(String::isNotEmpty)) {
            Instruction.fromString(line).operate(crane)
        }
        return crane.popAll()
    }

    override fun part2(input: List<String>): String {
        val (serializedStacks, instructions) = input.splitAt("")
        val crane = CrateMover9001().apply { loadCrates(serializedStacks) }
        for (line in instructions.filter(String::isNotEmpty)) {
            Instruction.fromString(line).operate(crane)
        }
        return crane.popAll()
    }

    fun Crane.loadCrates(serializedCraneStacks: List<String>) {
        val stacks = serializedCraneStacks.dropLast(1).reversed()
        for (line in stacks) {
            line.filterIndexed { index, _ -> index % 4 == 1 }
                .withIndex()
                .filter { (_, char) -> char in 'a'..'z' || char in 'A'..'Z' }
                .forEach { (index, value) ->
                    push(index + 1, value)
                }
        }
    }



    class Instruction(
        val times: Int,
        val from: Int,
        val to: Int
    ) {
        fun operate(crane: Crane) = crane.pushAll(to, crane.pop(from, times))
        companion object {
            private val instructionPattern = Regex("^move (\\d+) from (\\d) to (\\d)$")
            fun fromString(instruction: String): Instruction {
                val (_, times, from, to) = (instructionPattern
                    .matchEntire(instruction) ?: throw Exception("Didn't match pattern: $instruction"))
                    .groupValues
                return Instruction(times.toInt(), from.toInt(), to.toInt())
            }
        }
    }
    interface Crane {
        val keys: Set<Int>
        fun pop(index: Int, times: Int = 1): String
        fun push(index: Int, value: Char)
        fun pushAll(index: Int, values: String) {
            for(value in values) push(index, value)
        }
        fun popAll(): String = keys.map { pop(it) ?: ' ' }.joinToString("")
    }
    class CrateMover9000: MutableMap<Int, Stack<Char>> by mutableMapOf(), Crane {
        override fun push(index: Int, value: Char) {
            getOrPut(index, ::Stack).push(value)
        }
        override fun pop(index: Int, times: Int) = buildString {
            repeat(times) {
                append(this@CrateMover9000[index]?.pop()!!)
            }
        }
    }

    class CrateMover9001: MutableMap<Int, Stack<Char>> by mutableMapOf(), Crane {
        override fun push(index: Int, value: Char) {
            getOrPut(index, ::Stack).push(value)
        }
        override fun pop(index: Int, times: Int) = buildString {
            repeat(times) {
                append(this@CrateMover9001[index]?.pop()!!)
            }
        }.reversed()
    }
    fun <T> List<T>.splitAt(separator: T) = buildList {
        var list = mutableListOf<T>()

        this@splitAt.forEach { item ->
            if (item == separator) {
                add(list)
                list = mutableListOf()
            } else {
                list.add(item)
            }
        }
        if (list.size > 0) add(list)
    }
}