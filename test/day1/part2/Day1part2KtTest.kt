package day1.part2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day1part2KtTest {

    @Test
    fun solve() {
        assertEquals(4, solve("R8, R4, R4, R8".lines()))
    }
}
