package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

class Day9(val input: List<Long>, val checkLast: Int) {

    fun isSumOfLast(ptr: Int): Boolean {
        val check = input[ptr]
        val subList = input.subList(ptr - checkLast, ptr)

        subList.forEach { it1 ->
            subList.forEach { it2 ->
                if (it1 != it2 && it1 + it2 == check) {
                    return true
                }
            }
        }

        return false
    }

    fun part1(): Long {
        for (ptr in checkLast until input.size) {
            if (!isSumOfLast(ptr)) {
                return input[ptr]
            }
        }

        return -1;
    }

    fun part2(reference: Long): Long {
        val list = mutableListOf<Long>()
        for (ptr in input.indices) {
            list.clear()
            var sum = 0L

            for (last in 0..999) {
                val element = input[ptr + last]
                list.add(element)
                sum += element

                if (sum > reference) {
                    break
                } else if (sum == reference) {
                    return firstPlusLast(list)
                }
            }

        }

        return -1
    }

    private fun firstPlusLast(list: MutableList<Long>): Long {
        list.sort()
        return list.first() + list.last()
    }
}


fun main() {
    val input = DayInput.asLongList(9)
    val day9 = Day9(input, 25)

    // part 1
    val part1 = day9.part1()
    println("result part 1: $part1")

    // part2
    val part2 = day9.part2(167829540L)
    println("result part 2: $part2")
}