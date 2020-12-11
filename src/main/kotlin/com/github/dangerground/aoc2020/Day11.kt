package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import com.github.dangerground.aoc2020.util.Direction
import com.github.dangerground.aoc2020.util.World

fun main() {
    val process = Day11(DayInput.asWorld(11))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day11(val input: World) {

    var world = World(input.map.toList().toMutableList())
    var nextWorld = World(input.map)

    val directions = mutableListOf<Direction>()

    init {
        for (r in -1..1) {
            for (c in -1..1) {
                if (r != 0 || c != 0) {
                    directions.add(Direction(r, c))
                }
            }
        }
    }

    fun countNeighbors(row: Int, column: Int): Int {
        var neighbors = 0
        for (r in row - 1..row + 1) {
            for (c in column - 1..column + 1) {
                if (r == row && c == column) {
                    continue
                }
                if (world.isChar(r, c, '#')) {
                    neighbors++
                }
            }
        }
        return neighbors
    }

    var changed = false
    fun nextTick() {
        changed = false
        nextWorld = World(world.map.toList().toMutableList())
        for (r in 0 until world.getRowCount()) {
            for (c in 0 until world.getColumnCount()) {
                val countNeighbors = countNeighbors(r, c)
                val isEmpty = world.isChar(r, c, 'L')
                val isOccupied = world.isChar(r, c, '#')
                if (countNeighbors == 0 && isEmpty) {
                    nextWorld.setCell(r, c, '#')
                    changed = true
                } else if (isOccupied && countNeighbors >= 4) {
                    nextWorld.setCell(r, c, 'L')
                    changed = true
                }
            }
        }
        world = World(nextWorld.map)
    }

    fun part1(): Int {
        do {
            nextTick()
        } while (changed)
        return countOccupied()
    }

    private fun countOccupied() = world.map.map { it.count { it == '#' } }.sum()

    fun part2(): Int {
        resetWorld()
        do {
            nextTick2()
        } while (changed)
        return countOccupied()
    }

    private fun resetWorld() {
        world = World(input.map.toList().toMutableList())
    }


    fun countLineNeighbors(row: Int, column: Int): Int {
        return directions.filter { world.canSee(row, column, '#', it) }.count()
    }

    fun nextTick2() {
        changed = false
        nextWorld = World(world.map.toList().toMutableList())
        for (r in 0 until world.getRowCount()) {
            for (c in 0 until world.getColumnCount()) {
                val countNeighbors = countLineNeighbors(r, c)
                val isEmpty = world.isChar(r, c, 'L')
                val isOccupied = world.isChar(r, c, '#')
                if (countNeighbors == 0 && isEmpty) {
                    nextWorld.setCell(r, c, '#')
                    changed = true
                } else if (isOccupied && countNeighbors >= 5) {
                    nextWorld.setCell(r, c, 'L')
                    changed = true
                }
            }
        }
        world = World(nextWorld.map)
    }
}

