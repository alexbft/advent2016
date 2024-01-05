package day18.part1

import bootstrap.readLines

fun solve(firstRow: String, rowsCount: Int): Int {
    var row = firstRow.map { it == '^' }
    var total = 0
    for (rowIndex in 2..rowsCount + 1) {
        total += row.count { !it }
        row = row.indices.map { x ->
            val left = if (x == 0) false else row[x - 1]
            val right = if (x == row.size - 1) false else row[x + 1]
            left != right
        }
    }
    return total
}

fun main() {
    val input = readLines("day18.txt").first()
    println(solve(input, 40))
}
