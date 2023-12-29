package day5.part1

import java.math.BigInteger
import java.security.MessageDigest

fun md5(s: String): String {
    val md = MessageDigest.getInstance("MD5")
    val result = md.digest(s.toByteArray(Charsets.US_ASCII))
    return String.format("%032x", BigInteger(1, result))
}

fun solve(input: String): String {
    val maxN = 100_000_000
    return buildString {
        for (i in 0 until maxN) {
            val hash = md5("$input$i")
            if (hash.startsWith("00000")) {
                append(hash[5])
                if (length == 8) {
                    break
                }
            }
        }
    }
}

fun main() {
    println(solve("cxdnnyjw"))
}
