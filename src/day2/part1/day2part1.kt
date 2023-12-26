package day2.part1

import bootstrap.readLines

fun solve(lines: List<String>): String {
    val x0 = 0
    val x1 = 2
    val y0 = 0
    val y1 = 2
    var x = 1
    var y = 1
    val digits = lines.map { line ->
        for (c in line) {
            when (c) {
                'L' -> x -= 1
                'R' -> x += 1
                'U' -> y -= 1
                'D' -> y += 1
            }
            x = maxOf(x0, minOf(x1, x))
            y = maxOf(y0, minOf(y1, y))
        }
        y * 3 + x + 1
    }
    return digits.joinToString("")
}

fun main() {
    println(solve(readLines("day2.txt")))
}
