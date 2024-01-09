package day25

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day25KtTest {

    @Test
    fun program() {
        val actual = program(solve(), 20).joinToString("")
        assertEquals("01010101010101010101", actual)
    }
}
