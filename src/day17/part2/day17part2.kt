package day17.part2

import md5.md5

private data class Vector2(val x: Int, val y: Int) {
    operator fun plus(other: Vector2) = Vector2(x + other.x, y + other.y)

    fun isInside(): Boolean = x in 0..3 && y in 0..3
}

private enum class Direction(val vector: Vector2, val code: Char) {
    Up(Vector2(0, -1), 'U'),
    Down(Vector2(0, 1), 'D'),
    Left(Vector2(-1, 0), 'L'),
    Right(Vector2(1, 0), 'R'),
}

private data class State(val position: Vector2, val path: String)

private const val openDoorChars = "bcdef"
private val directionOrder = listOf(Direction.Up, Direction.Down, Direction.Left, Direction.Right)

private fun getExits(seed: String, position: Vector2, path: String): List<Direction> {
    val hash = md5("$seed$path")
    return buildList {
        for ((i, direction) in directionOrder.withIndex()) {
            if (hash[i] in openDoorChars && (position + direction.vector).isInside()) {
                add(direction)
            }
        }
    }
}

fun solve(seed: String): Int {
    var states = listOf(State(Vector2(0, 0), ""))
    val targetPosition = Vector2(3, 3)
    val candidateLengths = mutableListOf<Int>()
    while (states.isNotEmpty()) {
        val newStates = mutableListOf<State>()
        for ((position, path) in states) {
            for (direction in getExits(seed, position, path)) {
                val newPosition = position + direction.vector
                val newPath = path + direction.code
                if (newPosition == targetPosition) {
                    candidateLengths.add(newPath.length)
                } else {
                    newStates.add(State(newPosition, newPath))
                }
            }
        }
        states = newStates
    }
    return candidateLengths.max()
}

fun main() {
    println(solve("vwbaicqe"))
}
