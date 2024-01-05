package day15.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day15part1KtTest {

    @Test
    fun solve() {
        val input = """
            Disc #1 has 5 positions; at time=0, it is at position 4.
            Disc #2 has 2 positions; at time=0, it is at position 1.
        """.trimIndent().lines()
        assertEquals(5L, solve(input))
    }
}
