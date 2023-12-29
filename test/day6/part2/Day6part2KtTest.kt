package day6.part2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day6part2KtTest {

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
        assertEquals("advent", solve(input))
    }
}
