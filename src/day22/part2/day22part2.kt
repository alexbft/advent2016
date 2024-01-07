package day22.part2

import bootstrap.readLines

private data class Node(val used: Int, val size: Int)

private data class Vector2(val x: Int, val y: Int) {
    operator fun plus(other: Vector2) = Vector2(x + other.x, y + other.y)
}

private data class State(val emptyTilePos: Vector2, val dataPos: Vector2)

fun solve(lines: List<String>): Int {
    val lineRe = """/dev/grid/node-x(\d+)-y(\d+)\s+(\d+)T\s+(\d+)T.*""".toRegex()
    val nodes = lines.drop(2).associate { line ->
        val match = lineRe.matchEntire(line) ?: throw Exception("No match at line $line")
        val (x, y, size, used) = match.destructured
        Vector2(x.toInt(), y.toInt()) to Node(used.toInt(), size.toInt())
    }
    // In the input data, there is exactly one node with used = 0,
    // and there is no pair of non-empty nodes that can be combined.
    val xRange = 0..nodes.keys.maxOf { it.x }
    val yRange = 0..nodes.keys.maxOf { it.y }
    val startEmptyTilePos = nodes.entries.find { it.value.used == 0 }!!.key
    val startDataPos = Vector2(xRange.last, 0)
    val targetDataPos = Vector2(0, 0)
    var states = listOf(State(startEmptyTilePos, startDataPos))
    val visited = mutableSetOf(states.first())
    var moveCount = 0
    while (states.isNotEmpty()) {
        val newStates = mutableListOf<State>()
        for ((emptyTilePos, dataPos) in states) {
            if (dataPos == targetDataPos) {
                return moveCount
            }
            for (move in listOf(Vector2(1, 0), Vector2(0, 1), Vector2(-1, 0), Vector2(0, -1))) {
                val newEmptyTilePos = emptyTilePos + move
                if (newEmptyTilePos.x !in xRange || newEmptyTilePos.y !in yRange) {
                    continue
                }
                // We can do this, because the nodes are mostly interchangeable, except for some large filled nodes
                if (nodes[emptyTilePos]!!.size < nodes[newEmptyTilePos]!!.used) {
                    continue
                }
                val newDataPos = if (newEmptyTilePos == dataPos) emptyTilePos else dataPos
                val newState = State(newEmptyTilePos, newDataPos)
                if (newState in visited) {
                    continue
                }
                visited.add(newState)
                newStates.add(newState)
            }
        }
        states = newStates
        ++moveCount
    }
    throw Exception("no path")
}

fun main() {
    println(solve(readLines("day22.txt")))
}
