package day9.part2

import bootstrap.readLines

fun solve(s: String): Long {
    var i = 0
    val re = """\((\d+)x(\d+)\)""".toRegex()
    var counter = 0L
    while (i < s.length) {
        val match = re.find(s, startIndex = i) ?: break
        counter += match.range.first - i
        val (len, repeat) = match.groupValues.drop(1).map { it.toInt() }
        i = match.range.last + len + 1
        counter += solve(s.substring(match.range.last + 1, i)) * repeat
    }
    counter += s.length - i
    return counter
}

fun main() {
    println(solve(readLines("day9.txt")[0]))
}
