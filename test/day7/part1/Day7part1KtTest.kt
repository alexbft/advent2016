package day7.part1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day7part1KtTest {

    @Test
    fun supportsTls1() {
        assertEquals(true, supportsTls("abba[mnop]qrst"))
    }

    @Test
    fun supportsTls2() {
        assertEquals(false, supportsTls("abcd[bddb]xyyx"))
    }

    @Test
    fun supportsTls3() {
        assertEquals(false, supportsTls("aaaa[qwer]tyui"))
    }

    @Test
    fun supportsTls4() {
        assertEquals(true, supportsTls("ioxxoj[asdfgh]zxcvbn"))
    }
    @Test
    fun solve() {
        val input = """
            abba[mnop]qrst
            abcd[bddb]xyyx
            aaaa[qwer]tyui
            ioxxoj[asdfgh]zxcvbn
        """.trimIndent().lines()
        assertEquals(2, solve(input))
    }
}
