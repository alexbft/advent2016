package day6.part1

import bootstrap.readLines

fun solve(lines: List<String>): String {
    val cols = lines[0].indices.map { colIndex ->
        lines.map { it[colIndex] }
    }
    return cols.map { col -> col.groupingBy { it }.eachCount().maxBy { it.value }.key }.joinToString("")
}

fun main() {
    println(solve(readLines("day6.txt")))
}
