package day4.part2

import bootstrap.readLines

private const val ALPHABET = "abcdefghijklmnopqrstuvwxyz"

fun solve(lines: List<String>): Int {
    val rooms = lines.mapNotNull { line ->
        val lineRe = """(.+)-(\d+)\[(.{5})]""".toRegex()
        val match = lineRe.matchEntire(line) ?: throw Exception("no match at line $line")
        val (name, sectorId, checksum) = match.destructured
        val mostCommon = name
            .filter { it != '-' }
            .groupingBy { it }
            .eachCount()
            .entries
            .sortedWith(
                compareByDescending<Map.Entry<Char, Int>> { it.value }
                    .thenBy { it.key })
            .take(5)
            .joinToString("") { "${it.key}" }
        if (mostCommon == checksum) {
            val sectorIdNum = sectorId.toInt()
            val decryptedName = name.map { c ->
                when (c) {
                    '-' -> ' '
                    else -> ALPHABET[(ALPHABET.indexOf(c) + sectorIdNum) % ALPHABET.length]
                }
            }.joinToString("")
            decryptedName to sectorIdNum
        } else null
    }.toMap()
    return rooms["northpole object storage"]!!
}

fun main() {
    println(solve(readLines("day4.txt")))
}
