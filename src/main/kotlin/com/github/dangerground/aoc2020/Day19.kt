package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import com.github.dangerground.aoc2020.util.World
import kotlin.math.max

fun main() {
    val process = Day19(DayInput.batchesOfStringList(19))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day19(val input: List<List<String>>) {

    val rules = input[0].map { rule ->
        val tmp = rule.split(":");
        return@map tmp[0].toInt() to tmp[1]
    }.toMap().toMutableMap()

    val messages = input[1]

    fun expandRules(rule: Int): String {
        var content = rules[rule]!!

        val regex = "(\\d+) \\| \\1 \\b$rule\\b".toRegex()
        if (content.contains(regex)) {
            val result = regex.matchEntire(content)!!.groupValues
            content = "( ${result[1]} )+"
            println("::$content")
        }

        val regex2 = "(\\d+) (\\d+) \\| \\1 \\b$rule\\b \\2".toRegex()
        if (rule == 11 && content.contains(regex2)) {
            val result = regex2.matchEntire(content)!!.groupValues
            content = "( ${result[1]} ${result[2]}  |  ${result[1]} ${result[1]} ${result[2]} ${result[2]} | ${result[1]} ${result[1]} ${result[1]} ${result[2]} ${result[2]} ${result[2]} | ${result[1]} ${result[1]} ${result[1]} ${result[1]} ${result[2]} ${result[2]} ${result[2]} ${result[2]}  | ${result[1]} ${result[1]} ${result[1]} ${result[1]} ${result[1]} ${result[2]} ${result[2]} ${result[2]} ${result[2]} ${result[2]} )"
            println("::$content")
        }

        val numbers = content.replace("| ", "")
            .split(" ")
            .sortedByDescending { it.length }
            .filter { it.matches("\\d+".toRegex()) }
            .map { it.toInt() }

        numbers.forEach {
            val expandRules = expandRules(it)
            content = content.replace("$it", if(expandRules.contains('|')) "($expandRules)" else expandRules)
        }
        return content
    }

    fun part1(): Int {
        val rule = cleanup(expandRules(0)).toRegex()
        println(rule)

        return messages.map { rule.matches(it) }.count { it }
    }

    private fun cleanup(rule: String): String {
        return rule.replace("\"","").replace(" ", "")

    }

    fun part2(): Int {
        rules[8] = "42 | 42 8"
        rules[11] = "42 31 | 42 11 31"
        val rule = cleanup(expandRules(0)).toRegex()
        println(rule)

        return messages.map { rule.matches(it) }.count { it }
    }
}
