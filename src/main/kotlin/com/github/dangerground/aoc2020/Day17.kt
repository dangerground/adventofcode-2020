package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import com.github.dangerground.aoc2020.util.World

fun main() {
    val process = Day17(DayInput.asWorld(17))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day17(input: World) {

    var world = World3D()
    var nextWorld = world.newEmpty()

    init {
        for (r in 0 until input.getRowCount()) {
            for (c in 0 until input.getColumnCount()) {
                world.setActive(0, r, c, input.isChar(r, c, '#'))
            }
        }
    }

    fun part1(): Int {
        println("$world")

        var zRange = 1
        var xyRange = 0

        for (z in world.zRange().first - zRange..world.zRange().last + zRange) {
            for (y in world.yRange().first - xyRange..world.yRange().last + xyRange) {
                for (x in world.xRange().first - xyRange..world.xRange().last + xyRange) {
                    val self = world.getCell(z, y, x)
                    val t = world.countActiveNeighbours(z, y, x)
                    val active = (self && (t == 2 || t == 3)) || (!self && t == 3)
                    println("($z, $y, $x) = [$self, $t] = $active")
                    nextWorld.setActive(z, y, x, active)
                }
            }
        }

        println("$nextWorld")

        return world.countActive()
    }

    fun part2(): Long {
        return -1
    }
}

class World3D() {
    val active = mutableMapOf<Int, MutableMap<Int, MutableMap<Int, Boolean>>>()

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
            if (!active.containsKey(sz)) {

            }
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
        if (!active.containsKey(z)
            || !active[z]!!.containsKey(y)
            || !active[z]!![y]!!.containsKey(x)
        ) {
            return false
        }
        val value = active[z]!![y]!![x]
        return value != null && value == true
    }

    fun setActive(z: Int, y: Int, x: Int, active: Boolean) {
        if (!this.active.containsKey(z)) {
            this.active[z] = mutableMapOf()
        }
        if (!this.active[z]!!.containsKey(y)) {
            this.active[z]!![y] = mutableMapOf()
        }
        this.active[z]!![y]!![x] = active
    }

    fun newEmpty(): World3D {
        return World3D()
    }

    fun zRange(): IntRange {
        val whole = active.keys
        val zmin = whole.minOrNull()!!
        val zmax = whole.maxOrNull()!!
        return IntRange(zmin, zmax)
    }

    fun yRange(): IntRange {
        val whole = active.map { (_, v) -> v.keys }
            .reduce { l1, l2 -> mutableSetOf(l1.maxOrNull()!!, l2.maxOrNull()!!, l1.minOrNull()!!, l2.minOrNull()!!) }
        val ymin = whole.minOrNull()!!
        val ymax = whole.maxOrNull()!!
        return IntRange(ymin, ymax)
    }

    fun xRange(): IntRange {
        val whole = active.map { (_, v) ->
            v.map { (_, v) -> v.keys }
                .reduce { l1, l2 ->
                    mutableSetOf(l1.maxOrNull()!!, l2.maxOrNull()!!, l1.minOrNull()!!, l2.minOrNull()!!)
                }
        }
            .reduce { l1, l2 -> mutableSetOf(l1.maxOrNull()!!, l2.maxOrNull()!!, l1.minOrNull()!!, l2.minOrNull()!!) }
        val xmin = whole.minOrNull()!!
        val xmax = whole.maxOrNull()!!
        return IntRange(xmin, xmax)
    }

    override fun toString(): String {
        var result = ""
        active.forEach { (k, v) ->
            result += "z=$k\n"
            v.forEach { (_, u) ->
                result += "${u.map { if (it.value) '#' else '.' }.toCharArray().contentToString()}\n"
            }
        }
        return result
    }
}

