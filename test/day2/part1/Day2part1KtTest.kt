package day2.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day2part1KtTest {

    @Test
    fun solve() {
        val input = """
            ULL
            RRDDD
            LURDL
            UUUUD
        """.trimIndent().lines()
        assertEquals("1985", solve(input))
    }
}
