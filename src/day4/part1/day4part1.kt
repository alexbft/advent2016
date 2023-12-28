package day4.part1

import bootstrap.readLines

fun solve(lines: List<String>): Int {
    return lines.sumOf { line ->
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
        if (mostCommon == checksum) sectorId.toInt() else 0
    }
}

fun main() {
    println(solve(readLines("day4.txt")))
}
