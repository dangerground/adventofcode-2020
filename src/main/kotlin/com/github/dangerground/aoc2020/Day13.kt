package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

fun main() {
    val process = Day13(DayInput.asStringList(13))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day13(val input: List<String>) {

    val earliestDeparture = input[0].toInt()
    val busLines = input[1].split(",")
        .map { it.replace("x", "0") }
        .map { it.toLong() }

    init {
        println(busLines)
    }

    fun part1(): Long {
        //println(busLines.filter { it != 0 }.map { Pair(it, it - earliestDeparture % it) })
        val lineDiff = busLines
            .filter { it != 0L }
            .map { Pair(it, it - earliestDeparture % it) }
            .minByOrNull { it.second }!!

        //println(lineDiff)
        return lineDiff.first * lineDiff.second
    }

    fun part2Linear(): Long {

        val all = busLines.mapIndexed { idx, it -> Pair(idx, it) }

        val test = all.filter { it.second != 0L }
        val diff = test.first().second

        val begin = System.currentTimeMillis()
        var i = diff * diff
        do {

            if (!test.map {
                    //    println("($i + ${it.first}) % ${it.second}")
                    (i + it.first) % it.second == 0L
                }.contains(false)) {
                println((System.currentTimeMillis() - begin))
                return i * diff
            }
            i += diff
        } while (true)
        return -1
    }

    fun part2(): Long {

        var all = busLines.mapIndexed { idx, it -> Pair(idx, it) }
        val indices = all.map { it.first }
        var steps = all.map { it.second }
        var values = all.map { it.second }

        val testable = all.filter { it.second != 0L }
        val diff = testable.first().second

        val begin = System.currentTimeMillis()

        var i = 0L
        while (i < 15) {
            val max = values.maxOf { it }
            values = indices.map {
                if (values[it] == 0L) {
                    return@map 0
                }
                if (values[it] == max) {
                    println("m $max")
                    return@map max
                }
                val tmp = (values[it]) / steps[it]
                println("$tmp = $max / ${steps[it]} ((${values[it]}) / ${steps[it]})")
                return@map (tmp+1) * steps[it]
            }
            println("$max - $values")
            i = values.first()
            if (!indices.filter { values[it] != 0L }.map {
                    println("(($i + ${it}) == ${values[it]}")
                    (i + it) == values[it]
                }.contains(false)) {
                println((System.currentTimeMillis() - begin))
                return i
            }

        }
        return -1
    }

}

