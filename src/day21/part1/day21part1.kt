package day21.part1

import bootstrap.readLines

typealias TransformFunc = (s: MutableList<Char>) -> MutableList<Char>

private fun swapPositionsFunc(x: Int, y: Int): TransformFunc = { s ->
    s[y] = s[x].also { s[x] = s[y] }
    s
}

private fun swapLettersFunc(a: Char, b: Char): TransformFunc = { s ->
    val x = s.indexOf(a)
    val y = s.indexOf(b)
    s[y] = s[x].also { s[x] = s[y] }
    s
}

private fun rotate(s: MutableList<Char>, steps: Int): MutableList<Char> {
    return if (steps > 0) {
        val tail = s.subList(s.size - steps, s.size)
        val head = s.subList(0, s.size - steps)
        (tail + head).toMutableList()
    } else if (steps < 0) {
        val head = s.subList(0, -steps)
        val tail = s.subList(-steps, s.size)
        (tail + head).toMutableList()
    } else {
        s
    }
}

private fun rotateFunc(steps: Int): TransformFunc = { s ->
    rotate(s, steps)
}

private fun rotateByIndexFunc(a: Char): TransformFunc = { s ->
    val index = s.indexOf(a)
    val steps = (if (index >= 4) index + 2 else index + 1) % s.size
    rotate(s, steps)
}

private fun reverseFunc(x: Int, y: Int): TransformFunc = { s ->
    var l = x
    var r = y
    while (l < r) {
        s[l] = s[r].also { s[r] = s[l] }
        ++l
        --r
    }
    s
}

private fun moveFunc(x: Int, y: Int): TransformFunc = { s ->
    val c = s.removeAt(x)
    s.add(y, c)
    s
}

fun solve(lines: List<String>, start: String): String {
    val reSwapPositions = """swap position (\d) with position (\d)""".toRegex()
    val reSwapLetters = """swap letter (\w) with letter (\w)""".toRegex()
    val reRotate = """rotate (left|right) (\d) steps?""".toRegex()
    val reRotateByIndex = """rotate based on position of letter (\w)""".toRegex()
    val reReverse = """reverse positions (\d) through (\d)""".toRegex()
    val reMove = """move position (\d) to position (\d)""".toRegex()
    val transforms = lines.map { line ->
        reSwapPositions.matchEntire(line)?.let { match ->
            val (x, y) = match.destructured
            swapPositionsFunc(x.toInt(), y.toInt())
        } ?: reSwapLetters.matchEntire(line)?.let { match ->
            val (a, b) = match.destructured
            swapLettersFunc(a.single(), b.single())
        } ?: reRotate.matchEntire(line)?.let { match ->
            val (dir, steps) = match.destructured
            if (dir == "left") rotateFunc(-steps.toInt()) else rotateFunc(steps.toInt())
        } ?: reRotateByIndex.matchEntire(line)?.let { match ->
            val a = match.groupValues[1].single()
            rotateByIndexFunc(a)
        } ?: reReverse.matchEntire(line)?.let { match ->
            val (x, y) = match.destructured
            reverseFunc(x.toInt(), y.toInt())
        } ?: reMove.matchEntire(line)?.let { match ->
            val (x, y) = match.destructured
            moveFunc(x.toInt(), y.toInt())
        } ?: throw Exception("No match for line $line")
    }
    var chars = start.toMutableList()
    for (t in transforms) {
        chars = t(chars)
    }
    return chars.joinToString("")
}

fun main() {
    println(solve(readLines("day21.txt"), "abcdefgh"))
}
