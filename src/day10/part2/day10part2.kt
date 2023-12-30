package day10.part2

import bootstrap.readLines

private enum class DestType { Output, Bot }

private data class Destination(val type: DestType, val id: Int)

private class Bot(val id: Int) {
    private val hold = mutableListOf<Int>()
    var highDest: Destination? = null
    var lowDest: Destination? = null

    fun add(chip: Int) {
        hold.add(chip)
        if (hold.size > 2) {
            throw Exception("too many chips to hold")
        }
    }

    fun clear() {
        hold.clear()
    }

    val isFull: Boolean get() = hold.size >= 2

    val high: Int get() = hold.max()

    val low: Int get() = hold.min()
}

fun solve(lines: List<String>): Long {
    val bots = mutableMapOf<Int, Bot>()

    val initRe = """value (\d+) goes to bot (\d+)""".toRegex()
    val ruleRe = """bot (\d+) gives low to (bot|output) (\d+) and high to (bot|output) (\d+)""".toRegex()
    for (line in lines) {
        initRe.matchEntire(line)?.also { match ->
            val (v, botId) = match.groupValues.drop(1).map { it.toInt() }
            bots.getOrPut(botId) { Bot(botId) }.add(v)
        } ?: ruleRe.matchEntire(line)?.also { match ->
            val botId = match.groupValues[1].toInt()
            val lowDestType = if (match.groupValues[2] == "bot") DestType.Bot else DestType.Output
            val lowDestId = match.groupValues[3].toInt()
            val highDestType = if (match.groupValues[4] == "bot") DestType.Bot else DestType.Output
            val highDestId = match.groupValues[5].toInt()
            val bot = bots.getOrPut(botId) { Bot(botId) }
            bot.lowDest = Destination(lowDestType, lowDestId)
            bot.highDest = Destination(highDestType, highDestId)
        } ?: throw Exception("No match for line: $line")
    }

    val outputs = mutableMapOf<Int, Int>()
    val queue = ArrayDeque<Int>()
    for (bot in bots.values) {
        if (bot.isFull) {
            queue.add(bot.id)
        }
    }
    while (queue.isNotEmpty()) {
        val bot = bots[queue.removeFirst()]!!
        if (!bot.isFull) {
            continue
        }
        if (bot.highDest!!.type == DestType.Bot) {
            val destId = bot.highDest!!.id
            bots[destId]!!.add(bot.high)
            queue.add(destId)
        } else {
            outputs[bot.highDest!!.id] = bot.high
        }
        if (bot.lowDest!!.type == DestType.Bot) {
            val destId = bot.lowDest!!.id
            bots[destId]!!.add(bot.low)
            queue.add(destId)
        } else {
            outputs[bot.lowDest!!.id] = bot.low
        }
        bot.clear()
    }

    return outputs[0]!!.toLong() * outputs[1]!! * outputs[2]!!
}

fun main() {
    println(solve(readLines("day10.txt")))
}
