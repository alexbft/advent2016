package day20.part2

import bootstrap.readLines

private data class Bound(val x: UInt, val opening: Boolean)

fun solve(lines: List<String>): UInt {
    val bounds = mutableListOf<Bound>()
    for (line in lines) {
        val (op, cls) = line.split("-").map { it.toUInt() }
        bounds.add(Bound(op, true))
        if (cls < UInt.MAX_VALUE) {
            bounds.add(Bound(cls + 1u, false))
        }
    }
    bounds.sortWith(compareBy<Bound> { it.x }.thenBy { it.opening })
    var openCount = 0
    var counter = 0u
    var prevBound = 0u
    for (bound in bounds) {
        val prevCount = openCount
        openCount += (if (bound.opening) 1 else -1)
        if (prevCount == 0) {
            counter += (bound.x - prevBound)
        }
        prevBound = bound.x
    }
    if (openCount == 0) {
        counter += (UInt.MAX_VALUE - prevBound) + 1u
    }
    return counter
}

fun main() {
    println(solve(readLines("day20.txt")))
}
