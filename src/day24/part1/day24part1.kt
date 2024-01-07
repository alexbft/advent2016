package day24.part1

import bootstrap.readLines

private data class Vector2(val x: Int, val y: Int) {
    operator fun plus(other: Vector2) = Vector2(x + other.x, y + other.y)
}

private fun calcDistance(rows: List<String>, start: Vector2, finish: Vector2): Int {
    var positions = listOf(start)
    val visited = mutableSetOf(start)
    for (dist in 0..100_000) {
        if (positions.isEmpty()) {
            throw Exception("no path from $start to $finish")
        }
        val newPositions = mutableListOf<Vector2>()
        for (cur in positions) {
            if (cur == finish) {
                return dist
            }
            for (move in listOf(Vector2(1, 0), Vector2(-1, 0), Vector2(0, 1), Vector2(0, -1))) {
                val next = cur + move
                if (rows[next.y][next.x] != '#' && next !in visited) {
                    visited.add(next)
                    newPositions.add(next)
                }
            }
        }
        positions = newPositions
    }
    throw Exception("path too long from $start to $finish")
}

private fun findMinPathRec(lastVisited: Int, notVisited: Set<Int>, distances: Map<Pair<Int, Int>, Int>): Int {
    if (notVisited.isEmpty()) {
        return 0
    }
    return notVisited.minOf { dest ->
        distances[lastVisited to dest]!! + findMinPathRec(
            dest,
            notVisited - dest,
            distances
        )
    }
}

fun solve(lines: List<String>): Int {
    val nodes = mutableMapOf<Int, Vector2>()
    for (y in lines.indices) {
        for (x in lines[0].indices) {
            val c = lines[y][x]
            if (c in '0'..'9') {
                nodes[c.digitToInt()] = Vector2(x, y)
            }
        }
    }
    val nodeKeys = nodes.keys.toList()
    val nodeDistances = mutableMapOf<Pair<Int, Int>, Int>()
    for (i in nodeKeys.indices) {
        for (j in i + 1..<nodeKeys.size) {
            val s = nodeKeys[i]
            val f = nodeKeys[j]
            val dist = calcDistance(lines, nodes[s]!!, nodes[f]!!)
            nodeDistances[s to f] = dist
            nodeDistances[f to s] = dist
        }
    }
    return findMinPathRec(0, nodes.keys - 0, nodeDistances)
}

fun main() {
    println(solve(readLines("day24.txt")))
}
