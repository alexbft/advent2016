package day3.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day3part1KtTest {

    @Test
    fun solve() {
        val input = """
            1 2 3
            3 2 1
            5 10 25
            2 3 4
        """.trimIndent().lines()
        assertEquals(1, solve(input))
    }
}
