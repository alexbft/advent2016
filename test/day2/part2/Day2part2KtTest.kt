package day2.part2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day2part2KtTest {

    @Test
    fun solve() {
        val input = """
            ULL
            RRDDD
            LURDL
            UUUUD
        """.trimIndent().lines()
        assertEquals("5DB3", solve(input))
    }
}
