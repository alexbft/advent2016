package day16

fun fromBitString(s: String): List<Boolean> {
    return s.map { it == '1' }
}

fun toBitString(v: List<Boolean>): String {
    return v.joinToString("") { if (it) "1" else "0" }
}
