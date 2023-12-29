package day7.part1

import bootstrap.readLines

private fun containsAbba(s: String): Boolean {
    for (i in 0..s.length - 4) {
        val (a, b, c, d) = s.substring(i..i + 3).toList()
        if (a != b && a == d && b == c) {
            return true
        }
    }
    return false
}

fun supportsTls(line: String): Boolean {
    val re = """\[(\w+)]""".toRegex()
    val inBrackets = re.findAll(line).map { it.groupValues[1] }.toList()
    val outsideBrackets = re.split(line)
    return !inBrackets.any(::containsAbba) && outsideBrackets.any(::containsAbba)
}

fun solve(lines: List<String>): Int {
    return lines.count(::supportsTls)
}

fun main() {
    println(solve(readLines("day7.txt")))
}
