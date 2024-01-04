package day12.part1

import bootstrap.readLines

private class Computer(private val lines: List<String>) {
    private var ip = 0
    val registers = mutableMapOf<Char, Int>()

    private fun resolve(regOrValue: String): Int {
        return regOrValue.toIntOrNull() ?: registers[regOrValue[0]] ?: 0
    }

    private fun executeNext(): Boolean {
        val line = lines[ip]
        val parts = line.split(" ")
        val cmd = parts[0]
        val op1 = parts[1]
        val op2 = if (parts.size > 2) parts[2] else ""
        var jumped = false
        when (cmd) {
            "cpy" -> {
                registers[op2[0]] = resolve(op1)
            }
            "inc" -> {
                registers[op1[0]] = registers.getOrDefault(op1[0], 0) + 1
            }
            "dec" -> {
                registers[op1[0]] = registers.getOrDefault(op1[0], 0) - 1
            }
            "jnz" -> {
                if (resolve(op1) != 0) {
                    ip += op2.toInt()
                    jumped = true
                }
            }
            else -> {
                throw Exception("Unknown command: $line")
            }
        }
        if (!jumped) {
            ++ip
        }
        return ip in lines.indices
    }

    fun run() {
        while (executeNext()) {}
    }
}

fun solve(lines: List<String>): Int {
    val cpu = Computer(lines)
    cpu.run()
    return cpu.registers['a']!!
}

fun main() {
    println(solve(readLines("day12.txt")))
}
