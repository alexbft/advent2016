package day23.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day23part1KtTest {

    @Test
    fun solve() {
        val input = """
            cpy 2 a
            tgl a
            tgl a
            tgl a
            cpy 1 a
            dec a
            dec a            
        """.trimIndent().lines()
        assertEquals(3, solve(input))
    }
}
