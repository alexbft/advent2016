package day5.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day5part1KtTest {

    @Test
    fun md5() {
        assertEquals("000008f82c5b3924a1ecbebf60344e00", md5("abc5017308"))
    }

    @Test
    fun solve() {
        assertEquals("18f47a30", solve("abc"))
    }
}
