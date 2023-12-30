package day9.part2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day9part2KtTest {
    @ParameterizedTest
    @CsvSource(
        "(3x3)XYZ,9",
        "X(8x2)(3x3)ABCY,20",
        "(27x12)(20x12)(13x14)(7x10)(1x12)A,241920",
        "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN,445",
    )
    fun solve(input: String, expected: Int) {
        assertEquals(expected.toLong(), solve(input))
    }
}
