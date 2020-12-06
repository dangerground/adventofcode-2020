package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

class Day5(input: List<String>) {
    val seats = input.map { Seat(it) }

    fun highestSeadId(): Int {
        return seats.maxOf { it.getId() }
    }

    fun mySeadId(): Int? {
        val map = mutableMapOf<Int, Boolean>()
        for (i in 96..911) {
            map.put(i, false)
        }
        seats.forEach { map.put(it.getId(), true) }
        println(map.filter { it.value == false })
        return 0
    }
}

class Seat(input: String) {
    var row = 0
    var column = 0

    init {
        input.substring(0, 7).forEachIndexed { index, c ->
            if (c == 'B') {
                row += Math.pow(2.0, (6.0 - index)).toInt()
            }
        }
        input.substring(7, 10).forEachIndexed { index, c ->
            if (c == 'R') {
                column += Math.pow(2.0, (2.0 - index)).toInt()
            }
        }
    }

    fun getId() = row * 8 + column
    override fun toString(): String {
        return "Seat(id=${getId()})"
    }
}

fun main() {
    val input = DayInput.asStringList(5)
    val day5 = Day5(input)

    // part 1
    //val part1 = day5.highestSeadId()
    //println("result part 1: $part1")

    // part2
    val part2 = day5.mySeadId()
    println("result part 2: $part2")
}