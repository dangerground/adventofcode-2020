package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

class Day2 {

    fun isPasswordMatchingByCount(pwAndPolicy: PasswordAndPolicy): Boolean {
        val count = getPasswordLength(pwAndPolicy)
        return count >= pwAndPolicy.num1
                && count <= pwAndPolicy.num2
    }

    private fun getPasswordLength(pwAndPolicy: PasswordAndPolicy) =
            pwAndPolicy.password.count { a -> a.toString() == pwAndPolicy.policy }

    fun isMatchingByCount(line: String) =
            isPasswordMatchingByCount(PasswordAndPolicy(line))

    fun isPasswordMatchingByPosition(pwAndPolicy: PasswordAndPolicy): Boolean {
        var matched = false
        var lastPos = -1

        do {
            lastPos = pwAndPolicy.password.indexOf(pwAndPolicy.policy, lastPos + 1)
            if (lastPos == pwAndPolicy.num1 || lastPos == pwAndPolicy.num2) {
                if (matched) {
                    return false
                } else {
                    matched = true
                }
            }
        } while (lastPos != -1)

        return matched
    }

    fun isMatchingByPosition(line: String) =
            isPasswordMatchingByPosition(PasswordAndPolicy(line))
}

class PasswordAndPolicy(line: String) {
    private val inputSplitter = Regex("^(\\d+)-(\\d+) ([a-z]):( [a-z]+)$")

    val password: String
    val num1:Int
    val num2:Int
    val policy: String

    init {
        val result = inputSplitter.matchEntire(line) ?: throw Exception("Invalid input")

        num1 = result.groupValues[1].toInt()
        num2 = result.groupValues[2].toInt()
        policy = result.groupValues[3]

        password = result.groupValues[4]
    }
}

fun main() {
    val input = DayInput.asStringList(2)
    val day2 = Day2()

    // part 1
    //val part1 = input.count { day2.isMatchingByCount(it) }
    //println("result part 1: $part1")

    // part2
    val part2 = input.count { day2.isMatchingByPosition(it) }
    println("result part 2: $part2")
}