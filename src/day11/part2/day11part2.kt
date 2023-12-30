package day11.part2

import bootstrap.readLines
import java.util.BitSet

private data class State(val curFloor: Int, val floors: List<BitSet>) {
    val isValid: Boolean
        get() = floors.all { floor ->
            var hasAnyGenerators = false
            var hasMismatchedKeys = false
            for (i in 0..<floor.size() step 2) {
                if (floor[i]) {
                    hasAnyGenerators = true
                    if (hasMismatchedKeys) {
                        return@all false
                    }
                }
                if (floor[i + 1] && !floor[i]) {
                    hasMismatchedKeys = true
                    if (hasAnyGenerators) {
                        return@all false
                    }
                }
            }
            true
        }

    fun moveItems(to: Int, items: List<Int>): State {
        val newFloors = floors.toMutableList()
        val newFloorFrom = floors[curFloor].clone() as BitSet
        val newFloorTo = floors[to].clone() as BitSet
        for (item in items) {
            newFloorFrom[item] = false
            newFloorTo[item] = true
        }
        newFloors[curFloor] = newFloorFrom
        newFloors[to] = newFloorTo
        return State(to, newFloors)
    }
}

private data class QueueItem(val state: State, val dist: Int)

private fun fromIndexSets(generators: List<Int>, chips: List<Int>): BitSet {
    val result = BitSet()
    for (g in generators) {
        result[g * 2] = true
    }
    for (c in chips) {
        result[c * 2 + 1] = true
    }
    return result
}

private fun toIndexList(floor: BitSet): List<Int> {
    return buildList {
        var i = 0
        while (true) {
            val nextSetBit = floor.nextSetBit(i)
            if (nextSetBit < 0) {
                break
            }
            add(nextSetBit)
            i = nextSetBit + 1
        }
    }
}

fun solve(lines: List<String>): Int {
    val generatorRe = """(\w+) generator""".toRegex()
    val chipRe = """(\w+)-compatible microchip""".toRegex()
    val generators = lines.map { line ->
        generatorRe.findAll(line).map { it.groupValues[1] }.toList()
    }.toMutableList()
    val chips = lines.map { line ->
        chipRe.findAll(line).map { it.groupValues[1] }.toList()
    }.toMutableList()
    generators[0] += listOf("elerium", "dilithium")
    chips[0] += listOf("elerium", "dilithium")
    val allGenerators = generators.flatten().toSet()
    val allChips = chips.flatten().toSet()
    if (allGenerators != allChips) {
        throw Exception("Mismatch: Generators = $allGenerators Chips = $allChips")
    }
    val toIndex = allGenerators.withIndex().associate { (i, v) -> v to i }
    val floors = generators.zip(chips).map { (g, c) -> fromIndexSets(g.map { toIndex[it]!! }, c.map { toIndex[it]!! }) }
    val initialState = State(0, floors)
    if (!initialState.isValid) {
        throw Exception("Initial state invalid: $initialState")
    }
    val finalState = State(3, listOf(BitSet(), BitSet(), BitSet(), fromIndexSets(toIndex.values.toList(), toIndex.values.toList())))
    val visited = mutableSetOf(initialState)
    val queue = ArrayDeque(listOf(QueueItem(initialState, 0)))
    while (queue.isNotEmpty()) {
        val (cur, dist) = queue.removeFirst()
        if (cur == finalState) {
            return dist
        }
        val items = toIndexList(cur.floors[cur.curFloor])
        for (toFloor in listOf(cur.curFloor - 1, cur.curFloor + 1)) {
            if (toFloor !in 0..3) {
                continue
            }
            val moves = mutableListOf<List<Int>>()
            for (i in items.indices) {
                for (j in i..<items.size) {
                    if (i == j) {
                        moves.add(listOf(items[i]))
                    } else {
                        moves.add(listOf(items[i], items[j]))
                    }
                }
            }
            for (move in moves) {
                val newState = cur.moveItems(toFloor, move)
                if (newState.isValid && newState !in visited) {
                    visited.add(newState)
                    queue.add(QueueItem(newState, dist + 1))
                }
            }
        }
    }
    throw Exception("Path not found")
}

fun main() {
    println(solve(readLines("day11.txt")))
}
