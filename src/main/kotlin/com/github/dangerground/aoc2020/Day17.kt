package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import com.github.dangerground.aoc2020.util.World
import kotlin.math.max

fun main() {
    val process = Day17(DayInput.asWorld(17))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day17(input: World) {

    var world = World3D()
    var nextWorld = World3D()


    var world4 = World4D()
    var nextWorld4 = World4D()

    init {
        val puffer = 6
        for (r in 0 until input.getRowCount()) {
            for (c in 0 until input.getColumnCount()) {
                world.setActive(puffer, r + puffer, c + puffer, input.isChar(r, c, '#'))
                world4.setActive(puffer, puffer, r + puffer, c + puffer, input.isChar(r, c, '#'))
            }
        }
    }

    fun part1(): Int {
        world.print()

        val puffer = 6
        for (run in 0 until puffer) {
            nextTick()
            world = nextWorld
            nextWorld = World3D()
        }

        world.print()

        return world.countActive()
    }

    private fun nextTick() {
        for (z in 0..world.maxZ + 1) {
            for (y in 0..world.maxY + 1) {
                for (x in 0..world.maxX + 1) {
                    val self = world.getCell(z, y, x)
                    val t = world.countActiveNeighbours(z, y, x)
                    val active = (self && (t == 2 || t == 3)) || (!self && t == 3)
                    nextWorld.setActive(z, y, x, active)
                }
            }
        }
    }

    fun part2(): Int {
        world4.print()
        nextTick4()
        world4.print()


        val puffer = 6
        for (run in 0 until puffer) {
            nextTick4()
            world4 = nextWorld4
            nextWorld4 = World4D()
        }

//        world.print()

        return world4.countActive()
    }

    private fun nextTick4() {
        for (w in 0..world4.maxW + 1) {
            for (z in 0..world4.maxZ + 1) {
                for (y in 0..world4.maxY + 1) {
                    for (x in 0..world4.maxX + 1) {
                        val self = world4.getCell(w, z, y, x)
                        val t = world4.countActiveNeighbours(w, z, y, x)
                        val active = (self && (t == 2 || t == 3)) || (!self && t == 3)
                        nextWorld4.setActive(w, z, y, x, active)
                    }
                }
            }
        }
    }

}

class World3D() {
    val active = mutableMapOf<Int, MutableMap<Int, MutableMap<Int, Boolean>>>()

    var maxZ = 0
    var maxY = 0
    var maxX = 0

    fun countActive(): Int {
        return active.map { (_, u) ->
            u.map { (_, u) ->
                u.count { it.value }
            }.sum()
        }.sum()
    }

    fun countActiveNeighbours(z: Int, y: Int, x: Int): Int {
        var result = 0

        for (sz in z - 1..z + 1) {
            for (sy in y - 1..y + 1) {
                for (sx in x - 1..x + 1) {
                    if (getCell(sz, sy, sx) && (sz != z || sy != y || sx != x)) {
                        result++
                    }
                }
            }
        }

        return result
    }

    fun getCell(z: Int, y: Int, x: Int): Boolean {

        if (!active.containsKey(z) || !active[z]!!.containsKey(y) || !active[z]!![y]!!.containsKey(x)) {
            return false
        }
        return active[z]!![y]!![x]!!
    }

    fun setActive(z: Int, y: Int, x: Int, active: Boolean) {
        maxZ = max(maxZ, z)
        maxY = max(maxY, y)
        maxX = max(maxX, x)

        if (!this.active.containsKey(z)) {
            this.active[z] = mutableMapOf()
        }
        if (!this.active[z]!!.containsKey(y)) {
            this.active[z]!![y] = mutableMapOf()
        }
        this.active[z]!![y]!![x] = active
    }

    fun print() {
        active.forEach { (k, v) ->
            println("\nz=$k")
            v.forEach { (_, u) ->
                println(u.map { if (it.value) '#' else '.' }.toCharArray().contentToString())
            }
        }
    }
}


class World4D() {
    val active = mutableMapOf<Int, MutableMap<Int, MutableMap<Int, MutableMap<Int, Boolean>>>>()

    var maxW = 0
    var maxZ = 0
    var maxY = 0
    var maxX = 0

    fun countActive(): Int {
        return active.map { (_, u) ->
            u.map { (_, u) ->
                u.map { (_, u) ->
                    u.count { it.value }
                }.sum()
            }.sum()
        }.sum()
    }

    fun countActiveNeighbours(w: Int, z: Int, y: Int, x: Int): Int {
        var result = 0

        for (sw in w - 1..w + 1) {
            for (sz in z - 1..z + 1) {
                for (sy in y - 1..y + 1) {
                    for (sx in x - 1..x + 1) {
                        if (getCell(sw, sz, sy, sx) && (sw != w || sz != z || sy != y || sx != x)) {
                            result++
                        }
                    }
                }
            }
        }

        return result
    }

    fun getCell(w: Int, z: Int, y: Int, x: Int): Boolean {

        if (!active.containsKey(w) || !active[w]!!.containsKey(z) || !active[w]!![z]!!.containsKey(y) || !active[w]!![z]!![y]!!.containsKey(x)) {
            return false
        }
        return active[w]!![z]!![y]!![x]!!
    }

    fun setActive(w: Int, z: Int, y: Int, x: Int, active: Boolean) {
        maxW = max(maxW, w)
        maxZ = max(maxZ, z)
        maxY = max(maxY, y)
        maxX = max(maxX, x)

        if (!this.active.containsKey(w)) {
            this.active[w] = mutableMapOf()
        }
        if (!this.active[w]!!.containsKey(z)) {
            this.active[w]!![z] = mutableMapOf()
        }
        if (!this.active[w]!![z]!!.containsKey(y)) {
            this.active[w]!![z]!![y] = mutableMapOf()
        }
        this.active[w]!![z]!![y]!![x] = active
    }

    fun print() {
        active.forEach { (w, v) ->
            v.forEach { (z, v) ->
                println("\nz=$z, w=$w")
                v.forEach { (_, u) ->
                    println(u.map { if (it.value) '#' else '.' }.toCharArray().contentToString())
                }
            }
        }
    }
}

