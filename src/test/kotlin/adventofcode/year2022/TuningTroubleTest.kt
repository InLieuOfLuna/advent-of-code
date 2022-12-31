package adventofcode.year2022

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TuningTroubleTest {
    @ParameterizedTest(name = "{0}: first marker after character {1}")
    @CsvSource(
        "bvwbjplbgvbhsrlpgdmjqwftvncz,      5",
        "nppdvjthqldpwncqszvftbrmjlhg,      6",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg, 10",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,  11"
    )
    fun part1(input: String, expect: Int) {
        assertEquals(expect, TuningTrouble.findFirstMarker(input, 4))
    }

    @ParameterizedTest(name = "{0}: first marker after character {1}")
    @CsvSource(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb,    19",
        "bvwbjplbgvbhsrlpgdmjqwftvncz,      23",
        "nppdvjthqldpwncqszvftbrmjlhg,      23",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg, 29",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,  26"
    )
    fun part2(input: String, expect: Int) {
        assertEquals(expect, TuningTrouble.findFirstMarker(input, 14))
    }
}