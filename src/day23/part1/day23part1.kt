package day23.part1

import bootstrap.readLines

private class Computer(lines: List<String>) {
    private val modifiedLines = lines.toMutableList()
    private var ip = 0
    val registers = mutableMapOf<Char, Int>()

    private fun resolve(regOrValue: String): Int {
        return regOrValue.toIntOrNull() ?: registers[regOrValue[0]] ?: 0
    }

    private fun regId(regOrError: String): Char {
        return if (regOrError.length == 1 && regOrError[0] in 'a'..'d') {
            regOrError[0]
        } else {
            'e' // e as in error
        }
    }

    private fun executeNext(): Boolean {
        val line = modifiedLines[ip]
        val parts = line.split(" ")
        val cmd = parts[0]
        val op1 = parts[1]
        val op2 = if (parts.size > 2) parts[2] else ""
        var jumped = false
        when (cmd) {
            "cpy" -> {
                registers[regId(op2)] = resolve(op1)
            }
            "inc" -> {
                registers[regId(op1)] = registers.getOrDefault(regId(op1), 0) + 1
            }
            "dec" -> {
                registers[regId(op1)] = registers.getOrDefault(regId(op1), 0) - 1
            }
            "jnz" -> {
                if (resolve(op1) != 0) {
                    ip += resolve(op2)
                    jumped = true
                }
            }
            "tgl" -> {
                val ptr = ip + resolve(op1)
                if (ptr in modifiedLines.indices) {
                    val tglParts = modifiedLines[ptr].split(" ").toMutableList()
                    if (tglParts.size > 2) {
                        tglParts[0] = if (tglParts[0] == "jnz") "cpy" else "jnz"
                    } else {
                        tglParts[0] = if (tglParts[0] == "inc") "dec" else "inc"
                    }
                    modifiedLines[ptr] = tglParts.joinToString(" ")
                }
            }
            else -> {
                throw Exception("Unknown command: $line")
            }
        }
        if (!jumped) {
            ++ip
        }
        return ip in modifiedLines.indices
    }

    fun run() {
        while (executeNext()) {}
    }
}

fun solve(lines: List<String>): Int {
    val cpu = Computer(lines)
    cpu.registers['a'] = 12
    cpu.run()
    return cpu.registers['a']!!
}

fun main() {
    println(solve(readLines("day23.txt")))
}
