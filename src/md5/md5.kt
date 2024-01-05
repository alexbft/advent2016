package md5

import java.math.BigInteger
import java.security.MessageDigest

fun md5(s: String): String {
    val res = MessageDigest.getInstance("MD5").digest(s.toByteArray(Charsets.US_ASCII))
    return String.format("%032x", BigInteger(1, res))
}
