package day13.part1

private data class Vector2(val x: Int, val y: Int) {
    operator fun plus(other: Vector2) = Vector2(x + other.x, y + other.y)
}

private enum class Direction(val vector: Vector2) {
    Up(Vector2(0, -1)),
    Down(Vector2(0, 1)),
    Right(Vector2(1, 0)),
    Left(Vector2(-1, 0)),
}

private fun canMoveTo(secret: Int, position: Vector2): Boolean {
    val (x, y) = position
    if (x < 0 || y < 0) {
        return false
    }
    val n = x*x + 3*x + 2*x*y + y + y*y + secret
    return n.countOneBits() % 2 == 0
}

fun solve(secret: Int, targetX: Int, targetY: Int): Int {
    val start = Vector2(1, 1)
    val finish = Vector2(targetX, targetY)
    val visited = mutableSetOf(start)
    var positions = listOf(start)
    for (step in 0..1_000_000) {
        val newPositions = mutableListOf<Vector2>()
        if (positions.isEmpty()) {
            throw Exception("No path")
        }
        for (pos in positions) {
            if (pos == finish) {
                return step
            }
            for (dir in Direction.entries) {
                val newPos = pos + dir.vector
                if (newPos !in visited && canMoveTo(secret, newPos)) {
                    visited.add(newPos)
                    newPositions.add(newPos)
                }
            }
        }
        positions = newPositions
    }
    throw Exception("Path too long")
}

fun main() {
    println(solve(1362, 31, 39))
}
