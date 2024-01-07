package day23.part2

fun solve(initA: Int): Int {
    // translate the input from bunny language to kotlin
    var a = initA
    var b = 0
    var c = 0
    var d = 0
    //cpy a b
    b = a
    //dec b
    b -= 1
    do {
        //cpy a d
        //cpy 0 a
        //cpy b c
        //inc a
        //dec c
        //jnz c -2
        //dec d
        //jnz d -5
        a *= b
        //dec b
        b -= 1
        //cpy b c
        //cpy c d
        //dec d
        //inc c
        //jnz d -2
        c = b * 2
        //tgl c
        //cpy -16 c
        //jnz 1 c (toggles to cpy 1 c, after c becomes 2, so this loop actually calculates factorial of a)
    } while (c > 2)
    //cpy 96 c
    c = 96
    //jnz 95 d (toggles to cpy 95 d)
    d = 95
    //inc a
    //inc d (toggles to dec d)
    //jnz d -2
    //inc c (toggles to dec c)
    //jnz c -5
    a += c * d
    return a
}

fun main() {
    println(solve(12))
}
