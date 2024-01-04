package day14.part2.multithread

import kotlinx.coroutines.*
import java.math.BigInteger
import java.security.MessageDigest

class HashGenerator {
    private val md = MessageDigest.getInstance("MD5")
    fun generate(s: String): String {
        var tmp = s
        for (i in 0..<2017) {
            val hash = md.digest(tmp.toByteArray(Charsets.US_ASCII))
            tmp = String.format("%032x", BigInteger(1, hash))
        }
        return tmp
    }
}

private class MtHashGenerator(private val salt: String) {
    private val preloadNum = 32
    private val cache = mutableMapOf<Int, String>()
    private var nextIndex = 0

    fun generate(index: Int): String {
        if (nextIndex <= index) {
            runBlocking {
                val deferredResults = (nextIndex..<nextIndex+preloadNum).map { preloadIndex ->
                    // Crucial - if you do not specify Default here, no actual multithreading happens
                    async(Dispatchers.Default) {
                        generateInternal(preloadIndex)
                    }
                }
                val results = deferredResults.awaitAll()
                for ((i, result) in results.withIndex()) {
                    cache[nextIndex + i] = result
                }
            }
            nextIndex += preloadNum
        }
        return cache[index]!!
    }

    private fun generateInternal(index: Int): String {
        // println("Start generating for $index")
        return HashGenerator().generate("$salt$index")
    }
}

private const val maxIndex = 1_000_000

private data class AnalyzeResult(val pentiples: List<Char>, val firstTriple: Char?)

private fun analyze(s: String): AnalyzeResult {
    var prev = ' '
    var prevCounter = 0
    var firstTriple: Char? = null
    val pentiples = mutableListOf<Char>()
    for (c in s) {
        if (c == prev) {
            ++prevCounter
            if (firstTriple == null && prevCounter == 2) {
                firstTriple = c
            }
            if (prevCounter == 4) {
                pentiples.add(c)
            }
        } else {
            prev = c
            prevCounter = 0
        }
    }
    return AnalyzeResult(pentiples, firstTriple)
}

fun solve(salt: String): Int {
    val hashGenerator = MtHashGenerator(salt)
    val keyIndices = mutableListOf<Int>()
    val candidates = mutableMapOf<Char, ArrayDeque<Int>>()
    var stopIndex = -1

    for (i in 0..maxIndex) {
        if (stopIndex != -1 && i >= stopIndex) {
            return keyIndices.sorted()[63]
        }
        val hash = hashGenerator.generate(i)
        val analyzeResult = analyze(hash)
        for (p in analyzeResult.pentiples) {
            val recentCandidates = candidates[p] ?: continue
            while (recentCandidates.isNotEmpty()) {
                val first = recentCandidates.removeFirst()
                if (i - first <= 1000) {
                    println("$first: ${hashGenerator.generate(first)} - validated at $i: $hash")
                    keyIndices.add(first)
                    if (keyIndices.size == 64) {
                        // Even if we have already found 64 keys, generate some more in hope that we'll find a key with lower index
                        stopIndex = first + 1000
                    }
                }
            }
        }
        if (analyzeResult.firstTriple != null) {
            candidates.getOrPut(analyzeResult.firstTriple) { ArrayDeque() }.add(i)
        }
    }
    throw Exception("64 keys not found in 0..$maxIndex")
}

fun main() {
    println(solve("qzyelonm"))
}
