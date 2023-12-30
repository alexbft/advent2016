package day11.part1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day11part1KtTest {
    @Test
    fun solve() {
        val input = """
            The first floor contains a hydrogen-compatible microchip and a lithium-compatible microchip.
            The second floor contains a hydrogen generator.
            The third floor contains a lithium generator.
            The fourth floor contains nothing relevant.
        """.trimIndent().lines()
        assertEquals(11, solve(input))
    }
}
