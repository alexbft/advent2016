package day7.part2

import bootstrap.readLines

private fun all3s(s: String): List<List<Char>> {
    return buildList {
        for (i in 0..s.length - 3) {
            add(s.substring(i..i + 2).toList())
        }
    }
}

private fun findAbas(s: String): List<String> {
    return all3s(s).mapNotNull { (a, b, c) ->
        if (a != b && a == c) "$a$b" else null
    }
}

private fun containsBab(s: String, ab: String): Boolean {
    val a = ab[0]
    val b = ab[1]
    return all3s(s).any { (a2, b2, c2) ->
        a2 == b && b2 == a && c2 == b
    }
}

fun supportsSsl(line: String): Boolean {
    val re = """\[(\w+)]""".toRegex()
    val inBrackets = re.findAll(line).map { it.groupValues[1] }.toList()
    val outsideBrackets = re.split(line)
    val abs = outsideBrackets.flatMap(::findAbas)
    return abs.any { ab ->
        inBrackets.any { containsBab(it, ab) }
    }
}

fun solve(lines: List<String>): Int {
    return lines.count(::supportsSsl)
}

fun main() {
    println(solve(readLines("day7.txt")))
}
