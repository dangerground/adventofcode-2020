package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import kotlin.math.absoluteValue

fun main() {
    val process = Day12(DayInput.asStringList(12))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day12(val input: List<String>) {

    val directions = listOf('E', 'S', 'W', 'N')

    fun part1(): Int {
        var north = 0
        var east = 0
        var currentDirection = 'E'
        input.forEach {
            val diff = it.substring(1).toInt()
            var command = it[0]
            if (command == 'F') {
                command = currentDirection
            }
            when (command) {
                'N' -> north += diff
                'S' -> north -= diff
                'E' -> east += diff
                'W' -> east -= diff
                'L' -> currentDirection = getCurrent(currentDirection, 0-diff)
                'R' -> currentDirection =  getCurrent(currentDirection, diff)
            }
            println("$it -> $east, $north ($currentDirection)")
        }

        return north.absoluteValue+east.absoluteValue
    }

    private fun getCurrent(currentDirection: Char, diff: Int): Char {
        val indexDiff = diff / 90
        val currentIndex = directions.indexOf(currentDirection)
        var nextIndex = currentIndex + indexDiff
        while (nextIndex < 0) {
            nextIndex += 4
        }
        return directions[nextIndex % 4]
    }

    var shipNorth = 0
    var shipEast = 0

    var wpNorth = 1
    var wpEast = 10
    var currentDirection = 'E'

    fun part2(): Int {
        input.forEach {
            val diff = it.substring(1).toInt()
            var command = it[0]
            when (command) {
                'N' -> wpNorth += diff
                'S' -> wpNorth -= diff
                'E' -> wpEast += diff
                'W' -> wpEast -= diff
                'F' -> {
                    shipEast += diff * wpEast
                    shipNorth += diff * wpNorth
                }
                'L' -> rotateLeft(diff)
                'R' -> rotateRight(diff)
            }
            println("$it -> ($shipEast, $shipNorth) ($wpEast, $wpNorth)")
        }

        return shipNorth.absoluteValue+shipEast.absoluteValue
    }

    private fun rotateLeft(diff: Int) {
        if (diff == 180) {
            wpEast *= -1
            wpNorth *= -1
        } else if (diff == 90) {
            val tmp1 = wpEast
            val tmp2 = wpNorth
            wpEast = tmp2 * -1
            wpNorth = tmp1
        } else if (diff == 270) {
            val tmp1 = wpEast
            val tmp2 = wpNorth
            wpEast = tmp2
            wpNorth = tmp1 * -1
        }
    }
    private fun rotateRight(diff: Int) {
        if (diff == 180) {
            wpEast *= -1
            wpNorth *= -1
        } else if (diff == 90) {
            val tmp1 = wpEast
            val tmp2 = wpNorth
            wpEast = tmp2
            wpNorth = tmp1 * -1
        } else if (diff == 270) {
            val tmp1 = wpEast
            val tmp2 = wpNorth
            wpEast = tmp2 * -1
            wpNorth = tmp1
        }
    }

}

