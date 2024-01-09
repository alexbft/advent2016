package day25

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day25KtTest {

    @Test
    fun program() {
        val actual = program(192, 10).joinToString("")
        assertEquals("0101010101", actual)
    }
}
