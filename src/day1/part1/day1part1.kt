package day1.part1

import bootstrap.readLines
import kotlin.math.abs

private data class Vector2(val x: Int, val y: Int) {
    operator fun plus(other: Vector2) = Vector2(x + other.x, y + other.y)
    operator fun times(scalar: Int) = Vector2(x * scalar, y * scalar)
    fun distance() = abs(x) + abs(y)
}

private enum class Direction(val vector: Vector2) {
    NORTH(Vector2(0, 1)),
    EAST(Vector2(1, 0)),
    SOUTH(Vector2(0, -1)),
    WEST(Vector2(-1, 0));

    fun turnLeft() = when (this) {
        NORTH -> WEST
        EAST -> NORTH
        SOUTH -> EAST
        WEST -> SOUTH
    }

    fun turnRight() = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }
}

fun solve(lines: List<String>): Int {
    val moves = lines[0].split(", ")
    var position = Vector2(0, 0)
    var direction = Direction.NORTH

    for (move in moves) {
        val turn = move[0]
        val distance = move.substring(1).toInt()
        direction = when (turn) {
            'L' -> direction.turnLeft()
            'R' -> direction.turnRight()
            else -> throw IllegalArgumentException("Invalid turn: $turn")
        }
        position += direction.vector * distance
    }

    return position.distance()
}

fun main() {
    println(solve(readLines("day1.txt")))
}
