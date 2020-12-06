package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

class Day1 {

    fun getTwoNumbersMatching(input: List<Int>, matching: Int): List<Int> {
        input.forEach { it1 ->
            input.forEach { it2 ->
                if (it1 + it2 == matching) {
                    return listOf(it1, it2)
                }
            }
        }

        throw Exception("Unexpectd")
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

        throw Exception("Unexpectd")
    }

    fun getMultipliedIntermediateOfTwo(input: List<Int>, matching: Int) =
        getTwoNumbersMatching(input, matching)
            .reduce { num1, num2 -> num1 * num2 }

    fun getMultipliedIntermediateOfThree(input: List<Int>, matching: Int) =
        getThreeNumbersMatching(input, matching)
            .reduce { num1, num2 -> num1 * num2 }
}

fun main() {
    val day1 = Day1()
    val input = DayInput.asIntList(1)

    // part 1
    val part1 = day1.getMultipliedIntermediateOfTwo(input, 2020)
    println("result part1: $part1")

    // part 2
    val part2 = day1.getMultipliedIntermediateOfThree(input, 2020)
    println("result part2: $part2")
}