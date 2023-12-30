package day9.part1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day9part1KtTest {
    @ParameterizedTest
    @CsvSource(
        "ADVENT,6",
        "A(1x5)BC,7",
        "(3x3)XYZ,9",
        "A(2x2)BCD(2x2)EFG,11",
        "(6x1)(1x3)A,6",
        "X(8x2)(3x3)ABCY,18",
    )
    fun solve(input: String, expected: Int) {
        assertEquals(expected, solve(input))
    }
}
