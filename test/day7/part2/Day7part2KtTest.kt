package day7.part2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day7part2KtTest {
    @Test
    fun supportsSsl1() {
        assertEquals(true, supportsSsl("aba[bab]xyz"))
    }

    @Test
    fun supportsSsl2() {
        assertEquals(false, supportsSsl("xyx[xyx]xyx"))
    }

    @Test
    fun supportsSsl3() {
        assertEquals(true, supportsSsl("aaa[kek]eke"))
    }

    @Test
    fun supportsSsl4() {
        assertEquals(true, supportsSsl("zazbz[bzb]cdb"))
    }

    @Test
    fun solve() {
        val input = """
            aba[bab]xyz
            xyx[xyx]xyx
            aaa[kek]eke
            zazbz[bzb]cdb
        """.trimIndent().lines()
        assertEquals(3, solve(input))
    }
}
