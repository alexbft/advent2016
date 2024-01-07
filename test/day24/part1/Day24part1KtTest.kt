package day24.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day24part1KtTest {

    @Test
    fun solve() {
        val input = """
            ###########
            #0.1.....2#
            #.#######.#
            #4.......3#
            ###########
        """.trimIndent().lines()
        assertEquals(14, solve(input))
    }
}
