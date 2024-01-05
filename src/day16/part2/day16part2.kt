package day16.part2

import day16.fromBitString
import day16.toBitString

fun generate(startData: List<Boolean>, length: Int): List<Boolean> {
    var cur = startData
    while (cur.size < length) {
        cur = cur + false + cur.reversed().map { !it }
        if (cur.size > length) {
            cur = cur.subList(0, length)
        }
    }
    return cur
}

fun checksum(data: List<Boolean>): String {
    var temp = data
    while (temp.size % 2 == 0) {
        temp = temp.chunked(2) { (a, b) -> a == b }
    }
    return toBitString(temp)
}

fun solve(startData: String, length: Int): String {
    return checksum(generate(fromBitString(startData), length))
}

fun main() {
    println(solve("11110010111001001", 35651584))
}
