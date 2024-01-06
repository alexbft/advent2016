package day22.part1

import bootstrap.readLines

private data class Node(val used: Int, val size: Int) {
    val avail get() = size - used
}

fun solve(lines: List<String>): Int {
    val lineRe = """\S+\s+(\d+)T\s+(\d+)T.*""".toRegex()
    val nodes = lines.drop(2).map { line ->
        val match = lineRe.matchEntire(line) ?: throw Exception("No match at line $line")
        val (size, used) = match.destructured
        Node(used.toInt(), size.toInt())
    }
    var counter = 0
    for (n1 in nodes) {
        for (n2 in nodes) {
            if (n1 === n2) {
                continue
            }
            if (n1.used > 0 && n1.used <= n2.avail) {
                ++counter
            }
        }
    }
    return counter
}

fun main() {
    println(solve(readLines("day22.txt")))
}
