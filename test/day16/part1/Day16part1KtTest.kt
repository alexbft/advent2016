package day16.part1

import day16.fromBitString
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day16part1KtTest {

    @Test
    fun generate() {
        val input = fromBitString("10000")
        val expected = fromBitString("10000011110010000111")
        assertEquals(expected, generate(input, 20))
    }

    @Test
    fun checksum() {
        val input = fromBitString("10000011110010000111")
        assertEquals("01100", checksum(input))
    }

    @Test
    fun solve() {
        assertEquals("01100", solve("10000", 20))
    }
}
