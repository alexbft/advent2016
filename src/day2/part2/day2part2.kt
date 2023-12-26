package day2.part2

import bootstrap.readLines

fun solve(lines: List<String>): String {
    val keypad = """
        ..1..
        .234.
        56789
        .ABC.
        ..D..
    """.trimIndent().lines()
    var x = 0
    var y = 2
    val digits = lines.map { line ->
        for (c in line) {
            var nx = x
            var ny = y
            when (c) {
                'L' -> nx -= 1
                'R' -> nx += 1
                'U' -> ny -= 1
                'D' -> ny += 1
            }
            if (nx in 0..4 && ny in 0..4 && keypad[ny][nx] != '.') {
                x = nx
                y = ny
            }
        }
        keypad[y][x]
    }
    return digits.joinToString("")
}

fun main() {
    println(solve(readLines("day2.txt")))
}
