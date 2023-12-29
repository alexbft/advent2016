package day6.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day6part1KtTest {

    @Test
    fun solve() {
        val input = """
            eedadn
            drvtee
            eandsr
            raavrd
            atevrs
            tsrnev
            sdttsa
            rasrtv
            nssdts
            ntnada
            svetve
            tesnvt
            vntsnd
            vrdear
            dvrsen
            enarar
        """.trimIndent().lines()
        assertEquals("easter", solve(input))
    }
}
