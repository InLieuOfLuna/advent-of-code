package adventofcode.year2022

import adventofcode.Solver

object RockPaperScissors: Solver(year = 2022, day = 2) {
    override fun part1(input: List<String>): String {
        return input
            .map { it.split(" ").map(Hand::decode) }
            .sumOf { (you, opponent) -> you playAgainst opponent }
            .toString()
    }


    override fun part2(input: List<String>): String {
        TODO("Not yet implemented")
    }
    private sealed class Hand(
        val score: Int,
        val weakAgainst: () -> Hand,
        val strongAgainst: () -> Hand
    ) {
        infix fun playAgainst(other: Hand) = score + when (other) {
            strongAgainst() -> 6
            this -> 3
            weakAgainst() -> 0
            else -> throw Exception("Impossible: $this, ${weakAgainst()}, ${strongAgainst()}")
        }
        companion object {
            fun decode(string: String) = when (string) {
                "A", "X" -> Rock
                "B", "Y" -> Paper
                "C", "Z" -> Scissors
                else -> throw Exception("Impossible to decode")
            }
        }
    }
    private object Rock: Hand(1, { Paper }, { Scissors })
    private object Paper: Hand(2, { Scissors }, { Rock })
    private object Scissors: Hand(3, { Rock }, { Paper })
}