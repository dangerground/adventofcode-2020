package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

fun main() {
    val process = Day16(DayInput.batchesOfStringList(16))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day16(val batches: List<List<String>>) {

    val fields = batches[0].map { Field(it) }
    val nearbyTickets = batches[2].drop(1).map { it.split(",").map { it.toInt() } }
    val myTicket = batches[1][1].split(",").map { it.toInt() }

    fun part1(): Int {
        var invalid = 0
        nearbyTickets.forEach { ticket ->
            for (value in ticket) {
                invalid += isAllInvalid(value)
            }
        }
        return invalid
    }

    private fun isAllInvalid(value: Int): Int {
        fields.forEach { field ->
            if (field.isValid(value)) {
                return -1
            }
        }
        return value
    }

    fun part2(): Long {
        val remaining = nearbyTickets.filter { !it.any { isAllInvalid(it) > -1 } }.toMutableList()
        remaining.add(myTicket)

        val fieldResult = possibleFieldsForIndex(remaining)

        return fieldResult.filter { (_, f) -> f[0].name.startsWith("departure") }
            .map { (idx, _) -> myTicket[idx].toLong() }
            .reduce { i1, i2 -> i1 * i2 }
    }

    private fun reduceByCanOnlyBeThisOneIndex(fieldResult: List<Pair<Int, MutableList<Field>>>) {
        val lonely = mutableSetOf<Field>()
        var changed: Boolean
        do {
            changed = false
            fieldResult.forEach {
                if (it.second.size == 1) {
                    lonely.add(it.second[0])
                } else {
                    val removed = it.second.removeIf { lonely.contains(it) }
                    if (removed) {
                        changed = true
                    }
                }
            }
        } while (changed)
    }

    private fun possibleFieldsForIndex(remaining: MutableList<List<Int>>): List<Pair<Int, MutableList<Field>>> {
        val fieldResult = remaining[0].indices
            .map { idx ->
                remaining
                    .map { ticket -> allValid(ticket[idx]) }
                    .reduce { l1, l2 -> l1.intersect(l2).toList() }
                    .toMutableList()
            }
            .mapIndexed { idx, it -> idx to it }

        reduceByCanOnlyBeThisOneIndex(fieldResult)

        return fieldResult
    }

    private fun allValid(value: Int): List<Field> {
        return fields.filter { it.isValid(value) }
    }
}

class Field(input: String) {

    val regex = Regex("([a-z ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)")

    val name: String
    val range1: IntRange
    val range2: IntRange

    init {
        val groups = regex.matchEntire(input)!!.groupValues
        name = groups[1]
        range1 = IntRange(groups[2].toInt(), groups[3].toInt())
        range2 = IntRange(groups[4].toInt(), groups[5].toInt())
    }

    fun isValid(value: Int): Boolean {
        return range1.contains(value) || range2.contains(value)
    }

    override fun toString(): String {
        return name
    }

}

