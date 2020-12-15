package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

fun main() {
    val process = Day15(DayInput.asStringList(15))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day15(val input: List<String>) {

    val numbers = input[0].split(",").map { it.toInt() }

    val spoken = mutableMapOf<Int, MutableList<Int>>()

    fun part2(): Int {
        spoken.clear()
        numbers.forEach {
            spoken[it] = mutableListOf()
        }

        var start = 1;
        spoken.forEach { (_, inTurn) ->
            inTurn.add(start++)
        }
        var lastNumber = spoken.map { it.key }.last()

        if (!spoken.containsKey(0)) {
            spoken[0] = mutableListOf()
        }

        var size : Int
        var latest = 0

        val begin = System.currentTimeMillis()
        //for (turn in start..2020) {
        for (turn in start..30000000) {
            if (turn % 1000000 == 0) {
                println("Turn $turn")
            }
            //println("Turn $turn ($lastNumber) - $spoken ")
            val lastTurns = spoken[lastNumber]!!
            size = lastTurns.size
            if (size == 1) {
                lastNumber = 0
            } else {
                latest = lastTurns[size - 1]
                lastNumber = latest - lastTurns[size - 2]
            }

            updatOrReplace(lastNumber, turn)
        }
        println("Time: ${System.currentTimeMillis() - begin}")
        return lastNumber
    }

    private fun updatOrReplace(lastNumber: Int, i: Int) {
        if (!spoken.containsKey(lastNumber)) {
            //println("new $lastNumber")
            spoken[lastNumber] = mutableListOf(i)
        } else {
            //println("add $lastNumber")
            spoken[lastNumber]!!.add(i)
            if (spoken[lastNumber]!!.size > 2) {
                //spoken[lastNumber]!!.fir
                spoken[lastNumber]!!.removeAt(0)
            }
        }
    }


    fun part1(): Int {
        spoken.clear()
        numbers.forEach {
            spoken[it] = mutableListOf()
        }

        var start = 1;
        spoken.forEach { (_, inTurn) ->
            inTurn.add(start++)
        }
        var lastNumber = spoken.map { it.key }.last()

        if (!spoken.containsKey(0)) {
            spoken[0] = mutableListOf()
        }

        for (i in start..2020) {
            //println("Turn $i ($lastNumber) - $spoken ")
            if (spoken[lastNumber]!!.size == 1) {
                lastNumber = 0
                spoken[0]!!.add(i)
                continue
            } else if (spoken[lastNumber]!!.size > 1) {
                val tmp = spoken[lastNumber]!!.reversed()
                lastNumber = tmp[0] - tmp[1]
                if (spoken.containsKey(lastNumber)) {
                    spoken[lastNumber]!!.add(i)
                } else {
                    spoken[lastNumber] = mutableListOf(i)
                }
            }
        }
        return lastNumber
    }
}

