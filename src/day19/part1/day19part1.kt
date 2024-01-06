package day19.part1

fun solve(x: Int): Int {
    var highestBit = 1
    while (highestBit shl 1 <= x) {
        highestBit = highestBit shl 1
    }
    return (x - highestBit) * 2 + 1
}

fun main() {
    println(solve(3012210))
}
