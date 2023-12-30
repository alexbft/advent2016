package day8.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day8part1KtTest {

    @Test
    fun solveFull() {
        val input = """
            rect 3x2
            rotate column x=1 by 1
            rotate row y=0 by 4
            rotate column x=1 by 1
        """.trimIndent().lines()
        val expected = """
            .#..#.#
            #.#....
            .#.....
        """.trimIndent().lines()
        val actual = solveFull(input, 7, 3)
        actual.checkEquals(expected)
    }

    @Test
    fun solve() {
        val input = """
            rect 3x2
            rotate column x=1 by 1
            rotate row y=0 by 4
            rotate column x=1 by 1
        """.trimIndent().lines()
        assertEquals(6, solveFull(input, 7, 3).litCells)
    }
}
