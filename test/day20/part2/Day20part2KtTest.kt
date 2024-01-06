package day20.part2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day20part2KtTest {

    @Test
    fun solve() {
        val input = """
            5-8
            0-2
            4-7
        """.trimIndent().lines()
        // The range size is UInt.MAX_VALUE + 1, and 8 ips are blocked
        val expected = UInt.MAX_VALUE - 8u + 1u
        assertEquals(expected, solve(input))
    }
}
