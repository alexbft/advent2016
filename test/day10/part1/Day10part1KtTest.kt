package day10.part1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day10part1KtTest {
    @Test
    fun solve() {
        val input = """
            value 5 goes to bot 2
            bot 2 gives low to bot 1 and high to bot 0
            value 3 goes to bot 1
            bot 1 gives low to output 1 and high to bot 0
            bot 0 gives low to output 2 and high to output 0
            value 2 goes to bot 2
        """.trimIndent().lines()
        assertEquals(2, solve(input, 5 to 2))
    }
}
