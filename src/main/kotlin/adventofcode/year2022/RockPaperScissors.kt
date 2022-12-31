package adventofcode.year2022

import adventofcode.Solver

object RockPaperScissors: Solver(year = 2022, day = 2) {
    override fun part1(input: List<String>): String {
        return input
            .map { it.split(" ").map(Hand::decode) }
            .sumOf { (opponent, you) -> you playAgainst opponent }
            .toString()
    }

    override fun part2(input: List<String>): String {
        return input
            .map { it.split(" ") }
            .map { (opponent, goal) ->
                Hand.decode(opponent) to Result.decode(goal)
            }
            .sumOf { (opponent, goal) ->
                playForResult(opponent, goal) playAgainst opponent
            }.toString()
    }

    private fun playForResult(other: Hand, result: Result) = when (result) {
        Result.Win -> other.weakAgainst()
        Result.Draw -> other
        Result.Loss -> other.strongAgainst()
    }

    private sealed class Hand(
        val score: Int,
        val weakAgainst: () -> Hand,
        val strongAgainst: () -> Hand
    ) {
        infix fun playAgainst(other: Hand) = score + when (other) {
            strongAgainst() -> Result.Win.points
            this -> Result.Draw.points
            weakAgainst() -> Result.Loss.points
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

    enum class Result(val points: Int) {
        Win(6),
        Draw(3),
        Loss(0);
        companion object {
            fun decode(string: String) = when (string) {
                "X" -> Loss
                "Y" -> Draw
                "Z" -> Win
                else -> throw Exception("Impossible: $string")
            }
        }
    }
}