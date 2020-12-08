package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

class Day7(input: List<String>) {

    private val bagrules = input.map { BagRule(it) }.map { it.color to it.innerBags }.toMap()

    fun getInnerBagCount(color: BagColor): Int {
        var total = bagrules[color]!!.entries.map { it.value }.sum()
        bagrules[color]!!.forEach {
            total += getInnerBagCount(it.key) * it.value
        }
        return total
    }

    private fun canContainColor(color: BagColor, searchColor: BagColor): Boolean {
        if (bagrules[color]!!.containsKey(searchColor)) {
            return true
        }
        bagrules[color]!!.forEach {
            if (canContainColor(it.key, searchColor)) {
                return true
            }
        }

        return false
    }

    fun part1(): Int {
        return bagrules.filter { canContainColor(it.key, "shiny gold") }.count()
    }

    fun part2(): Int {
        return getInnerBagCount("shiny gold")
    }
}

typealias BagColor = String

class BagRule(input: String) {

    val color: BagColor
    val innerBags = mutableMapOf<BagColor, Int>()

    init {
        val parts = input.split("contain")
        color = str2Color(parts[0])
        parts[1].trim().split(",").filter { it.trim() != "no other bags." }.forEach {
            val result = Regex("(\\d+) (.*)").matchEntire(it.trim())
            if (result != null) {
                innerBags[str2Color(result.groupValues[2])] = result.groupValues[1].toInt()
            }
        }
    }

    fun str2Color(string: String) = string.replace(Regex("bags?\\.?"), "").trim()
}

fun main() {
    val input = DayInput.asStringList(7)
    val day7 = Day7(input)

    // part 1
    val part1 = day7.part1()
    println("result part 1: $part1")

    // part2
    val part2 = day7.part2()
    println("result part 2: $part2")
}