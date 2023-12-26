package day1.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day1part1KtTest {

    @Test
    fun test1() {
        assertEquals(5, solve("R2, L3".lines()))
    }

    @Test
    fun test2() {
        assertEquals(2, solve("R2, R2, R2".lines()))
    }

    @Test
    fun test3() {
        assertEquals(12, solve("R5, L5, R5, R3".lines()))
    }

}
