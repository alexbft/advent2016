package day5.part2

import java.math.BigInteger
import java.security.MessageDigest

fun md5(s: String): String {
    val md = MessageDigest.getInstance("MD5")
    val result = md.digest(s.toByteArray(Charsets.US_ASCII))
    return String.format("%032x", BigInteger(1, result))
}

fun solve(input: String): String {
    val maxN = 100_000_000
    val posToChar = mutableMapOf<Int, Char>()
    for (i in 0 until maxN) {
        val hash = md5("$input$i")
        if (hash.startsWith("00000")) {
            val pos = "01234567".indexOf(hash[5])
            if (pos != -1 && pos !in posToChar) {
                posToChar[pos] = hash[6]
                println("found $pos: ${hash[6]}")
            }
            if (posToChar.size == 8) {
                break
            }
        }
    }
    return posToChar.entries.sortedBy { it.key }.joinToString("") { it.value.toString() }
}

fun main() {
    println(solve("cxdnnyjw"))
}
