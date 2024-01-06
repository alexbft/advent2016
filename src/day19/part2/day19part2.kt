package day19.part2

fun solve(x: Int): Int {
    var cur = 0
    for (n in 2..x) {
        val pivot = n / 2
        val cur1 = cur + 1
        cur = (if (cur1 < pivot) cur1 else cur1 + 1) % n
    }
    return cur + 1
}

fun main() {
    println(solve(3012210))
}
