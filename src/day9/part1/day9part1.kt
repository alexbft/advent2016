package day9.part1

import bootstrap.readLines

fun solve(s: String): Int {
    var i = 0
    val re = """\((\d+)x(\d+)\)""".toRegex()
    var counter = 0
    while (i < s.length) {
        val match = re.find(s, startIndex = i) ?: break
        counter += match.range.first - i
        val (len, repeat) = match.groupValues.drop(1).map { it.toInt() }
        i = match.range.last + len + 1
        counter += len * repeat
    }
    counter += s.length - i
    return counter
}

fun main() {
    println(solve(readLines("day9.txt")[0]))
}
