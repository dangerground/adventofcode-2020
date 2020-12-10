package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

fun main() {
    val process = Day10(DayInput.asIntList(10))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day10(adapters: List<Int>) {

    private val sortedAdapters = mutableListOf<List<Int>>()

    init {
        val input = adapters.toMutableList()
        input.add(0)

        var newList1 = mutableListOf<Int>()
        input.sorted().forEach {
            if (newList1.isNotEmpty() && newList1.last() + 3 == it) {
                sortedAdapters.add(newList1)
                newList1 = mutableListOf()
            }
            newList1.add(it)
        }
        if (!newList1.isEmpty()) {
            sortedAdapters.add(newList1)
        }
    }

    fun part1(): Int {
        val oneJoltDiff = sortedAdapters.map { it.size-1 }.sum()
        val threeJoltDiff = sortedAdapters.size

        return oneJoltDiff * threeJoltDiff;
    }

    fun part2(): Long {
        return sortedAdapters
            .map { permutations(it) }
            .reduce { i1, i2 -> i1 * i2 }
    }

    private fun permutations(i: List<Int>): Long {
        // copied values from recursive function for all possible ways
        // TODO find a reprentative mathematical way of computation
        return when (i.size) {
            5 -> 7
            4 -> 4
            3 -> 2
            2 -> 1
            1 -> 1
            else -> 0
        }
    }
}

