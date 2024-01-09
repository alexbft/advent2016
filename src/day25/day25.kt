package day25

fun program(initA: Int, generateCount: Int): List<Int> {
    val result = mutableListOf<Int>()
    // translated to kotlin
    // 1) cpy a d
    var d = initA
    // 2) cpy 9 c
    var c = 9
    // 3) cpy 282 b
    var b = 282
    // 4) inc d
    // 5) dec b
    // 6) jnz b -2 (-> 4)
    // 7) dec c
    // 8) jnz c -5 (-> 3)
    d += b * c
    // 9) cpy d a
    do {
        var a = d
        // 10) jnz 0 0 (nop)
        do {
            // 11) cpy a b
            // 12) cpy 0 a
            // 13) cpy 2 c
            // 14) jnz b 2 (-> 16)
            // 15) jnz 1 6 (-> 21)
            // 16) dec b
            // 17) dec c
            // 18) jnz c -4 (-> 14)
            // 19) inc a
            // 20) jnz 1 -7 (-> 13)
            c = a % 2
            a /= 2

            // 21) cpy 2 b
            // 22) jnz c 2 (-> 24)
            // 23) jnz 1 4 (-> 27)
            // 24) dec b
            // 25) dec c
            // 26) jnz 1 -4 (-> 22)
            // 27) jnz 0 0 (nop)
            b = c
            // 28) out b
            result.add(b)
            if (result.size >= generateCount) {
                return result
            }
            // 29) jnz a -19 (-> 10)
        } while (a != 0)
        // 30) jnz 1 -21 (-> 9)
    } while (true)
}

private const val maxN = 10_000_000

fun solve(): Int {
    // need to find minimal a such as:
    // (a + 9 * 282) as binary is 101010(...)
    for (a in 0..maxN) {
        var n = a + 9 * 282
        var expectedNextBit = 0
        var match = true
        while (n > 0) {
            if ((n and 1) != expectedNextBit) {
                match = false
                break
            }
            n = n shr 1
            expectedNextBit = 1 - expectedNextBit
        }
        if (match) {
            return a
        }
    }
    throw Exception("not found")
}

fun main() {
    println(solve())
}
