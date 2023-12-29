package day6.part2

import bootstrap.readLines

fun solve(lines: List<String>): String {
    val cols = lines[0].indices.map { colIndex ->
        lines.map { it[colIndex] }
    }
    return cols.map { col -> col.groupingBy { it }.eachCount().minBy { it.value }.key }.joinToString("")
}

fun main() {
    println(solve(readLines("day6.txt")))
}
