package day20.part1

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
    if (bounds[0].x > 0u) {
        return 0u
    }
    var openCount = 0
    for (bound in bounds) {
        openCount += (if (bound.opening) 1 else -1)
        if (openCount == 0) {
            return bound.x
        }
    }
    throw Exception("All values covered")
}

fun main() {
    println(solve(readLines("day20.txt")))
}
