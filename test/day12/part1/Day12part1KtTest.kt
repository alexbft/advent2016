package day12.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day12part1KtTest {

    @Test
    fun solve() {
        val input = """
            cpy 41 a
            inc a
            inc a
            dec a
            jnz a 2
            dec a
        """.trimIndent().lines()
        assertEquals(42, solve(input))
    }
}
