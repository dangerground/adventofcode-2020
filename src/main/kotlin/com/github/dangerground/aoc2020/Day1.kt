package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.InputUtil

class Day1 {

    fun getTwoNumbersMatching(input: List<Int>, matching: Int): List<Int> {
        input.forEach { it1 ->
            input.forEach { it2 ->
                if (it1 + it2 == matching) {
                    return listOf(it1, it2)
                }
            }
        }

        return listOf()
    }

    fun getThreeNumbersMatching(input: List<Int>, matching: Int): List<Int> {
        input.forEach { it1 ->
            input.forEach { it2 ->
                input.forEach { it3 ->
                    if (it1 + it2 + it3 == matching) {
                        return listOf(it1, it2, it3)
                    }
                }
            }
        }

        return listOf()
    }

    fun getMultipliedIntermediateOfTwo(input: List<Int>, matching: Int): Int {
        val numbers = getTwoNumbersMatching(input, matching)
        return numbers[0] * numbers[1]
    }

    fun getMultipliedIntermediateOfThree(input: List<Int>, matching: Int): Int {
        val numbers = getThreeNumbersMatching(input, matching)
        return numbers[0] * numbers[1] * numbers[2]
    }
}

fun main() {
    val day1 = Day1()
    val input = InputUtil.readInputForDayAsIntList(1)

    // part 1
//    val result = day1.getMultipliedIntermediateOfTwo(input, 2020)
//    println("day1 result part1: $result")

    // part 2
    val result = day1.getMultipliedIntermediateOfThree(input, 2020)
    println("day1 result part2: $result")
}