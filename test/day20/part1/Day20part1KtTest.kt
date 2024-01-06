package day20.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day20part1KtTest {

    @Test
    fun solve() {
        val input = """
            5-8
            0-2
            4-7
        """.trimIndent().lines()
        assertEquals(3u, solve(input))
    }
}
