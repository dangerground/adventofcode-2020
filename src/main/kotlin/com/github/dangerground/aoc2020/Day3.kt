package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.InputUtil

class Day3(val input: List<String>) {

    val TREE = '#'

    val width: Int
    val lines: Int

    init {
        lines = input.size-1
        width = input[0].length
    }

    fun isTree(row: Int, column: Int): Boolean {
        if (row > lines) return false
        return input.get(row).get(column % width) == TREE
    }

    fun countTrees(direction: Direction): Int {
        return  (1..lines).count { isTree(it*direction.down, it*direction.right)  }
    }

    fun countDirectionsMultiplied(directions: List<Direction>): Long {
        var multiplied : Long = 1
        directions.forEach {
            multiplied *= countTrees(it)
        }

        return multiplied
    }
}

class Direction(val down: Int, val right: Int);

fun main() {
    val input = InputUtil.readInputForDayAsStringList(3)
    val day3 = Day3(input)

    // part 1
    //val part1 = day3.countTrees(Direction(1, 3))
    //println("result part 1: $part1")

    // part2
    val directions = listOf(
            Direction(1,1),
            Direction(1,3),
            Direction(1,5),
            Direction(1,7),
            Direction(2,1))
    val part2 = day3.countDirectionsMultiplied(directions)
    println("result part 2: $part2")
}