package day4.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day4part1KtTest {

    @Test
    fun solve() {
        val input = """
            aaaaa-bbb-z-y-x-123[abxyz]
            a-b-c-d-e-f-g-h-987[abcde]
            not-a-real-room-404[oarel]
            totally-real-room-200[decoy]
        """.trimIndent().lines()
        assertEquals(1514, solve(input))
    }
}
