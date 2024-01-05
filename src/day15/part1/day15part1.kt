package day15.part1

import bootstrap.readLines

private data class Disc(val period: Long, val startPos: Long)

private fun lcm(a: Long, b: Long) = a * b / gcd(a, b)

private fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

fun solve(lines: List<String>): Long {
    val re = """Disc #\d has (\d+) positions; at time=0, it is at position (\d+)\.""".toRegex()
    val discs = lines.map { line ->
        val match = re.matchEntire(line) ?: throw Exception("No match at line $line")
        val (period, startPos) = match.destructured
        Disc(period.toLong(), startPos.toLong())
    }
    var fullPeriod = 1L
    var startTime = 0L
    for ((i, disc) in discs.withIndex()) {
        val adjust = i + 1 + disc.startPos
        while ((startTime + adjust) % disc.period != 0L) {
            startTime += fullPeriod
        }
        fullPeriod = lcm(fullPeriod, disc.period)
    }
    return startTime
}

fun main() {
    println(solve(readLines("day15.txt")))
}
