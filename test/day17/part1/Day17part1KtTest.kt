package day17.part1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day17part1KtTest {

    @Test
    fun test1() {
        assertEquals("DDRRRD", solve("ihgpwlah"))
    }

    @Test
    fun test2() {
        assertEquals("DDUDRLRRUDRD", solve("kglvqrro"))
    }

    @Test
    fun test3() {
        assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", solve("ulqzkmiv"))
    }
}
