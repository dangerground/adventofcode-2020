package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import com.github.dangerground.aoc2020.util.Direction
import com.github.dangerground.aoc2020.util.World

class Day3(private val world: World) {

    init {
        world.endlessWidth = true
    }

    fun isTree(row: Int, column: Int) =
        world.isChar(row, column, '#')

    fun countTrees(direction: Direction) =
        (0 until world.getRowCount()).count { isTree(it * direction.down, it * direction.right) }

    fun countDirectionsMultiplied(directions: List<Direction>): Long {
        var multiplied: Long = 1
        directions.forEach {
            multiplied *= countTrees(it)
        }

        return multiplied
    }
}

fun main() {
    val input = DayInput.asWorld(3)
    val day3 = Day3(input)

    // part 1
    val part1 = day3.countTrees(Direction(1, 3))
    println("result part 1: $part1")

    // part2
    val directions = listOf(
        Direction(1, 1),
        Direction(1, 3),
        Direction(1, 5),
        Direction(1, 7),
        Direction(2, 1)
    )
    val part2 = day3.countDirectionsMultiplied(directions)
    println("result part 2: $part2")
}