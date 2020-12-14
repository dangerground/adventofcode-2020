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

    fun part2(): Long {

        val all = busLines.mapIndexed { idx, it -> Pair(idx, it) }
        val diff = all.maxByOrNull { it.second }!!

        val test = all.map { Pair(it.first - diff.first, it.second) }.filter { it.second != 0L }

        val begin = System.currentTimeMillis()
        var i = diff.first.toLong()
        while (i < 5000) {

            if (!test.map {
                    println("($i + ${it.first})  % ${it.second}")
                    (i + it.first) % it.second == 0L
                }.contains(false)) {
                println((System.currentTimeMillis() - begin))
                return i
            }
            i += diff.second
        }
        return -1
/*
        println(busLines.size)
        println(list)

        return -1
        */
    }

}

