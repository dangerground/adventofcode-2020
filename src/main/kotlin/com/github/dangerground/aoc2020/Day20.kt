package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import com.github.dangerground.aoc2020.util.World

fun main() {
    val process = Day20(DayInput.batchesOfStringList(20))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day20(val input: List<List<String>>) {

    val tiles = input.map { tileNum(it[0]) to World(it.drop(1).toMutableList()) }.toMap()

    val tileNeighbours = tiles.map { it.key to 0 }.toMap().toMutableMap()
    val options = tiles.mapValues {
        listOf(
            it.value,
            it.value.rotateLeft(),
            it.value.rotateLeft().rotateLeft(),
            it.value.rotateLeft().rotateLeft().rotateLeft(),
            it.value.flip(),
            it.value.rotateLeft().flip(),
            it.value.rotateLeft().rotateLeft().flip(),
            it.value.rotateLeft().rotateLeft().flip().rotateLeft().flip()
        )
    }

    val relations = tiles.mapValues { Information() }

    //01234567890123456789
    //                   #
    //#    ##    ##    ###
    // #  #  #  #  #  #
    val seamonster = listOf(
        Pair(0, 19),
        Pair(1, 0), Pair(1, 5), Pair(1, 6), Pair(1, 11), Pair(1, 12), Pair(1, 17), Pair(1, 18), Pair(1, 19),
        Pair(2, 1), Pair(2, 4), Pair(2, 7), Pair(2, 10), Pair(2, 13), Pair(2, 16)
    )

    private fun tileNum(txt: String) = txt.replace("[^0-9]".toRegex(), "").toLong()
    fun part1(): Long {
        val tileNeighbours = tiles.map { it.key to 0 }.toMap().toMutableMap()

        for ((t1, w1Opts) in options) {
            for ((t2, w2Opts) in options) {
                if (t1 == t2) {
                    continue
                }

                next@ for (w1 in w1Opts) {
                    for (w2 in w2Opts) {
                        val matchingEdge = matchingEdge(w1, w2)
                        if (matchingEdge != null) {
                            //println(">--------------Found (${t1} / ${t2} / $matchingEdge)------------------>")
                            tileNeighbours[t1] = tileNeighbours[t1]!! + 1
                            break@next
                        }
                    }
                }
            }
        }

        return tileNeighbours.filter { it.value == 2 }.map { it.key }.reduce { i1, i2 -> i1 * i2 }
    }


    fun matchingEdge(w1: World, w2: World): Side? {

        // match right
        var matching = true
        for (r in 0 until w1.getRowCount()) {
            if (w1.getCell(r, 0) != w2.getCell(r, w2.getColumnCount() - 1)) {
                matching = false
                break
            }
        }
        if (matching) {
            return Side.LEFT
        }

        // match left
        matching = true
        for (r in 0 until w1.getRowCount()) {
            if (w2.getCell(r, 0) != w1.getCell(r, w1.getColumnCount() - 1)) {
                matching = false
                break
            }
        }
        if (matching) {
            return Side.RIGHT
        }

        // match top
        matching = true
        for (c in 0 until w1.getRowCount()) {
            if (w2.getCell(0, c) != w1.getCell(w2.getRowCount() - 1, c)) {
                matching = false
                break
            }
        }
        if (matching) {
            return Side.TOP
        }

        // match bottom
        matching = true
        for (c in 0 until w1.getRowCount()) {
            if (w1.getCell(0, c) != w2.getCell(w2.getRowCount() - 1, c)) {
                matching = false
                break
            }
        }
        if (matching) {
            return Side.BOTTOM
        }

        return null
    }

    fun part2(): Int {

        calculateRelationships()
        println(relations)


        val coord = getPositions()
        //println(coord)

        val matrix = mutableMapOf<Int, MutableMap<Int, Long>>()
        coord.map { it.value.second }.forEach { matrix[it] = mutableMapOf() }
        val filled = matrix.map { (row, _)-> row to coord.filter { it.value.second == row }.map { Pair(it.value.first, it.key) }.toMap().toMutableMap()}


       // println(coord)

        // debug print table
        filled.forEach {
            println()
            it.second.forEach {
                print(" ${it.value} ")
            }
        }

        return -1
    }

    private fun getPositions(): MutableMap<Long, Pair<Int, Int>> {
        val remainingKeys = tiles.keys.toMutableList()

        val coord = mutableMapOf<Long, Pair<Int, Int>>()
        coord[remainingKeys[0]] = Pair(0, 0)
        remainingKeys.removeAt(0)

        println(remainingKeys)

        /*
        val pos = mutableMapOf<Int, MutableMap<Int, Long>>()
        pos[0] = mutableMapOf(0 to remainingKeys[0])
        */
        while (!remainingKeys.isEmpty()) {
            val tileNum = remainingKeys.random()

            //println("$tileNum -> $coord")
            val relation = relations[tileNum]!!
            coord.toList().forEach { (tile, c) ->
                if (tile == relation.right) {
                    //println(" Right ")
                    coord[tileNum] = Pair(c.first - 1, c.second)
                    remainingKeys.remove(tileNum)
                }
                else if (tile == relation.left) {
                   // println(" Left ")
                    coord[tileNum] = Pair(c.first + 1, c.second)
                    remainingKeys.remove(tileNum)
                }
                else if (tile == relation.top) {
                    //println(" Top ")
                    coord[tileNum] = Pair(c.first, c.second + 1)
                    remainingKeys.remove(tileNum)
                }
                else if (tile == relation.bottom) {
                    //println(" Bottom ")
                    coord[tileNum] = Pair(c.first, c.second - 1)
                    remainingKeys.remove(tileNum)
                }
                else {
                    return@forEach
                }
            }
        }
        return coord
    }

    private fun calculateRelationships() {
        for ((t1, w1Opts) in options) {
            for ((t2, w2Opts) in options) {
                if (t1 == t2) {
                    continue
                }

                next@ for (w1 in w1Opts) {
                    for (w2 in w2Opts) {
                        val matchingEdge = matchingEdge(w1, w2)
                        if (matchingEdge != null) {
                            when (matchingEdge) {
                                Side.TOP -> {
                                    relations[t1]!!.top = t2
                                    //relations[t2]!!.bottom = t1
                                }
                                Side.BOTTOM -> {
                                    relations[t1]!!.bottom = t2
                                    //relations[t2]!!.top = t1
                                }
                                Side.LEFT -> {
                                    relations[t1]!!.left = t2
                                    //relations[t2]!!.right = t1
                                }
                                Side.RIGHT -> {
                                    relations[t1]!!.right = t2
                                    //relations[t2]!!.left = t1
                                }
                                else -> {
                                }
                            }
                            println(">--------------Found (${t1} / ${t2} / $matchingEdge)------------------>")
                            tileNeighbours[t1] = tileNeighbours[t1]!! + 1
                            break@next
                        }
                    }
                }
            }
        }

        println("neighbours")
        println(tileNeighbours)
    }
}

class Information {
    var left: Long? = null
    var right: Long? = null
    var top: Long? = null
    var bottom: Long? = null

    override fun toString(): String {
        return "Information(left=$left, right=$right, top=$top, bottom=$bottom)"
    }
}

enum class Side {
    TOP,
    LEFT,
    BOTTOM,
    RIGHT
}
