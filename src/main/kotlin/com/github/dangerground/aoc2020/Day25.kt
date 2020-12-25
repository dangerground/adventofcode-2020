package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

fun main() {
    val process = Day25(DayInput.asLongList(25))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day25(val input: List<Long>) {

    val divisor = 20201227
    val subject = 7L

    fun loopSize(reference: Long) : Int {
        var count = 0

        var value = 1L

        do {
            value *= subject
            value %= divisor
            count++
        }while (value != reference);

        return count
    }

    fun transform(reference: Long, loopSize: Int) : Long {
        var value = 1L

        for (i in 0 until loopSize) {
            value *= reference
            value %= divisor
        }

        return value
    }

    fun part1(): Long {
        return transform(input[1], loopSize(input[0]))
    }

    fun part2(): Long {
        return -1
    }

}
