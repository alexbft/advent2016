package day3.part1

import bootstrap.readLines

fun solve(lines: List<String>): Int {
    return lines.count { line ->
        val xs = line.trim().split("\\s+".toRegex()).map { it.toInt() }.sorted()
        xs[0] + xs[1] > xs[2]
    }
}

fun main() {
    println(solve(readLines("day3.txt")))
}
