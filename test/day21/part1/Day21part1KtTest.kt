package day21.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day21part1KtTest {

    @Test
    fun solve() {
        val input = """
            swap position 4 with position 0
            swap letter d with letter b
            reverse positions 0 through 4
            rotate left 1 step
            move position 1 to position 4
            move position 3 to position 0
            rotate based on position of letter b
            rotate based on position of letter d
        """.trimIndent().lines()
        val start = "abcde"
        assertEquals("decab", solve(input, start))
    }
}