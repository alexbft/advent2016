package day8.part2

import bootstrap.readLines

private const val defaultWidth = 50
private const val defaultHeight = 6

class Grid(private val width: Int, private val height: Int) {
    private val cells = BooleanArray(width * height) { false }

    fun rect(rectWidth: Int, rectHeight: Int) {
        for (y in 0..<rectHeight) {
            for (x in 0..<rectWidth) {
                cells[y * width + x] = true
            }
        }
    }

    fun shiftRow(row: Int, shift: Int) {
        val rowCells = (0..<width).map { cells[row * width + it] }
        for (x in 0..<width) {
            cells[row * width + (x + shift) % width] = rowCells[x]
        }
    }

    fun shiftCol(col: Int, shift: Int) {
        val colCells = (0..<height).map { cells[it * width + col] }
        for (y in 0..<height) {
            val row = (y + shift) % height
            cells[row * width + col] = colCells[y]
        }
    }

    val display: String
        get() = buildString {
            for (y in 0..<height) {
                for (x in 0..<width) {
                    append(if (cells[y * width + x]) '#' else '.')
                }
                append(System.lineSeparator())
            }
        }
}

fun solveFull(lines: List<String>, width: Int, height: Int): Grid {
    val grid = Grid(width, height)
    val rectRe = """rect (\d+)x(\d+)""".toRegex()
    val shiftRowRe = """rotate row y=(\d+) by (\d+)""".toRegex()
    val shiftColRe = """rotate column x=(\d+) by (\d+)""".toRegex()
    for (line in lines) {
        rectRe.matchEntire(line)?.also { match ->
            val (w, h) = match.destructured
            grid.rect(w.toInt(), h.toInt())
        } ?: shiftRowRe.matchEntire(line)?.also { match ->
            val (row, shift) = match.destructured
            grid.shiftRow(row.toInt(), shift.toInt())
        } ?: shiftColRe.matchEntire(line)?.also { match ->
            val (col, shift) = match.destructured
            grid.shiftCol(col.toInt(), shift.toInt())
        } ?: throw Exception("No match for line: $line")
    }
    return grid
}

fun solve(lines: List<String>): String {
    return solveFull(lines, defaultWidth, defaultHeight).display
}

fun main() {
    println(solve(readLines("day8.txt")))
}
