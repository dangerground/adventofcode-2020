package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

fun main() {
    val process = Day10(DayInput.asIntList(10))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day10(val input: List<Int>) {

    val max = input.max()

    fun part1(): Int {
        var currentJolt = 0
        var oneJoltDiff = 0
        var threeJoltDiff = 0

        while (true) {
            when {
                input.contains(currentJolt + 1) -> {
                    currentJolt += 1
                    oneJoltDiff++
                }
                input.contains(currentJolt + 2) -> {
                    currentJolt += 2
                }
                input.contains(currentJolt + 3) -> {
                    currentJolt += 3
                    threeJoltDiff++
                }
                else -> {
                    currentJolt += 3
                    threeJoltDiff++
                    break
                }
            }
        }

        println("$oneJoltDiff * $threeJoltDiff")
        return oneJoltDiff * threeJoltDiff;
    }


    fun part2(): Long {
        return prcountRecursive(1) + prcountRecursive(2) + prcountRecursive(3)
    }
    fun prcountRecursive(c: Int): Long {
//        println("----$c")
        return countRecursive(c)
    }

    fun countRecursive(current: Int): Long {
        if (current == max) {
//            print("$current!")
            return 1
        }
        if (!input.contains(current)) {
//            println()
            return 0
        }
//        print("$current, ")

        var result = 0L
        result += countRecursive(current + 1)
        result += countRecursive(current + 2)
        result += countRecursive(current + 3)
        //println("[$current] = $result")

        return result
    }


    fun part2NonRecursive(): Long {
        val sorted = input.sorted().toMutableList()

        val newList = mutableListOf<Int>()

        sorted.forEach {
            println("test $it -> ${sorted.contains(it-1)}, ${sorted.contains(it-2)}, ${sorted.contains(it-3)}")
            if (!sorted.contains(it-1)
                    xor !sorted.contains(it+1)) {
                newList.add(it)
            }
        }
        println(newList)

//        sorted.removeIf {!sorted.contains(it-1) && !sorted.contains(it-2) && sorted.contains(it-3)}
        println(sorted.subList(1, sorted.size-1))


        return countNonRecursive(1) + countNonRecursive(2) + countNonRecursive(3)
    }

    fun countNonRecursive(start: Int = 1): Long {
        if (input[start-1] > 3) {
            return 0
        }

        var sum = 1L
        var i = start - 1
        while(i < input.size) {
            val element = input[i]
            print("$element")
            var step = 1
            val thisSum = when {
                input.contains(element + 1) && input.contains(element + 2) && input.contains(element + 3) -> {
                    print("(3)")
                    step = 3
                    4
                }
                input.contains(element + 1) && input.contains(element + 2) -> {
                    print("(2)")
                    step = 2
                    2
                }
                input.contains(element + 1) || input.contains(element + 3) -> {
                    print("(1)")
                    0
                }
                else -> {
                    1
                }
            }
            print(", ")
            sum += thisSum
            i += step
        }
        return sum
    }
}

