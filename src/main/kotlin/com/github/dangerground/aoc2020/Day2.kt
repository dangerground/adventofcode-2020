package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

class Day2 {

    fun getPolicyAndPassword(input: String): Parts {
        val parts = input.split(":")
        val policy = parts[0].split(" ")
        val boundaries = policy[0].split("-")


        return Parts(Policy(policy[1], boundaries[0].toInt(), boundaries[1].toInt()), parts[1])
    }

    fun isPasswordMatchingByCount(password: String, policy: Policy): Boolean {
        val count = password.count { a -> a.toString() == policy.char }
        return count >= policy.min
                && count <= policy.max
    }

    fun isMatchingByCount(input: String): Boolean {
        val parts = getPolicyAndPassword(input)
        return isPasswordMatchingByCount(parts.password, parts.policy)
    }

    fun isPasswordMatchingByPosition(password: String, policy: Policy): Boolean {
        var matched = false
        var lastPos = -1

        do {
            lastPos = password.indexOf(policy.char, lastPos + 1)
            if (lastPos == policy.min || lastPos == policy.max) {
                if (matched) {
                    return false
                } else {
                    matched = true
                }
            }
        } while (lastPos != -1)

        return matched
    }

    fun isMatchingByPosition(input: String): Boolean {
        val parts = getPolicyAndPassword(input)
        return isPasswordMatchingByPosition(parts.password, parts.policy)
    }
}

class Parts(val policy: Policy, val password: String)
class Policy(val char: String, val min: Int, val max: Int)

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