package day3.part2

import bootstrap.readLines

fun solve(lines: List<String>): Int {
    return lines.chunked(3).sumOf { block ->
        val rows = block.map { line ->
            line.trim().split("\\s+".toRegex()).map { it.toInt() }
        }
        val cols = rows[0].indices.map { colIndex ->
            rows.map { row -> row[colIndex] }
        }
        cols.count { col ->
            val xs = col.sorted()
            xs[0] + xs[1] > xs[2]
        }
    }
}

fun main() {
    println(solve(readLines("day3.txt")))
}
