package day19.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day19part1KtTest {

    @Test
    fun test1() {
        assertEquals(3, solve(5))
    }

    @Test
    fun test2() {
        assertEquals(3, solve(9))
    }

    @Test
    fun test3() {
        assertEquals(11, solve(13))
    }
}
