import adventofcode.year2022.RopeBridge
import adventofcode.year2022.*
import java.util.Scanner

val year2022 = listOf(
    CalorieCounting,
    RockPaperScissors,
    RucksackReorganization,
    CampCleanup,
    SupplyStacks,
    TuningTrouble,
    NoSpaceLeftOnDevice,
    TreetopTreeHouse,
    RopeBridge,
    HillClimbingAlgorithm
)

fun main() {
    val year = year2022
    print("Day [1-${year.size}]: ")
    val day = year[readln().toInt() - 1]
    print("Part 1 or 2: ")
    val part = readln()
    println("Paste input below:")
    val input = readLines()
    if (part == "1") println(day.part1(input))
    if (part == "2") println(day.part2(input))
}

private fun readLines() = buildList {
    with(Scanner(System.`in`)) {
        while (hasNext()) add(nextLine())
    }
}