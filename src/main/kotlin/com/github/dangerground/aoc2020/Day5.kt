package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import kotlin.math.pow

class Day5(input: List<String>) {
    val seats = input.map { Seat(it) }

    fun highestSeadId(): Int {
        return seats.maxOf { it.getId() }
    }

    fun mySeatId(): Int {
        val seatIds = seats.map { it.getId() }
        val candidates = seatIds.filter { !seatIds.contains(it - 1) }.map { it - 1 }
        return seatIds.filter { candidates.contains(it + 1) && !seatIds.contains(it + 1) }
                .map { it + 1 }
                .first()
    }
}

class Seat(input: String) {
    val row = getNumber(input, 0, 7, 'B')
    val column = getNumber(input, 7, 3, 'R')

    private fun getNumber(input: String, lower: Int, length: Int, high: Char): Int {
        val upper = lower + length

        return input.substring(lower, upper).mapIndexed { index, c ->
            if (c == high) {
                2.0.pow(length.toDouble() - 1 - index)
            } else {
                0
            }
        }.sumOf { it.toInt() }
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
    val part1 = day5.highestSeadId()
    println("result part 1: $part1")

    // part2
    val part2 = day5.mySeatId()
    println("result part 2: $part2")
}