package day14.part2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day14part2KtTest {

    @Test
    fun solve() {
        assertEquals(22551, solve("abc"))
    }

    @Test
    fun generate() {
        assertEquals("a107ff634856bb300138cac6568c0f24", HashGenerator().generate("abc0"))
    }
}
