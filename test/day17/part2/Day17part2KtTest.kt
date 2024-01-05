package day17.part2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day17part2KtTest {
    @Test
    fun test1() {
        assertEquals(370, solve("ihgpwlah"))
    }

    @Test
    fun test2() {
        assertEquals(492, solve("kglvqrro"))
    }

    @Test
    fun test3() {
        assertEquals(830, solve("ulqzkmiv"))
    }
}
