package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import kotlin.math.pow

fun main() {
    val process = Day14(DayInput.asStringList(14))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day14(val input: List<String>) {

    var mask = "X".repeat(36)
    var mem = mutableMapOf<Long, Long>()

    val regex = Regex("mem\\[(\\d+)] = (\\d+)")

    fun part1(): Long {
        input.forEach {
            if (it.startsWith("mask")) {
                mask = it.substring(7).reversed()
                //println("mask $mask")
            } else {
                val groups = regex.matchEntire(it)!!
                val memPos = groups.groupValues[1]
                val memVal = groups.groupValues[2]
                val numToBitArray = numToBitArray(memVal.toLong())
                val applyMask = applyMask(numToBitArray)

                val bitsToLong = bitsToLong(applyMask)

                println("value:  ${numToBitArray.contentToString()} (decimal $memVal)")
                println("mask:   ${mask}")
                println("result: ${applyMask.contentToString()} (decimal $bitsToLong)")

                mem[memPos.toLong()] = bitsToLong

            }
        }
        return mem.map { it.value }.sum()
    }

    private fun bitsToLong(applyMask: LongArray): Long {
        var result = 0L
        applyMask.forEachIndexed { index, i ->
            if (i == 1L) {
                result += 2.0.pow(index).toLong()
            }
        }
        return result
    }

    fun numToBitArray(num: Long): IntArray {
        val result = IntArray(36)
        var remaining = num
        for (i in 35 downTo 0) {
            val test = 2.0.pow(i)
            if (remaining >= test) {
                remaining -= test.toLong()
                result[i] = 1
            }
        }
        return result
    }

    fun applyMask(x: IntArray): LongArray {
        val y = x.toList().map {it.toLong()}.toLongArray()
        mask.forEachIndexed { index, c ->
            if (c != 'X') {
                y[index] = c.toString().toLong()
            }
        }
        return  y
    }

    fun part2(): Long {
        mem.clear()

        input.forEach {
            if (it.startsWith("mask")) {
                mask = it.substring(7).reversed()
                //println("mask $mask")
            } else {
                val groups = regex.matchEntire(it)!!
                val memPos = groups.groupValues[1]
                val memVal = groups.groupValues[2]
                val numToBitArray = numToBitArray(memPos.toLong())
                val applyMask = applyMask2(numToBitArray)

                val expandFloating = expandFloating(applyMask)
                expandFloating.forEach {
                    val pos = bitsToLong(it.map { it.toLong() }.toTypedArray().toLongArray())
                    mem[pos] = memVal.toLong()
                }
                //println("$expandFloating")

                //val bitsToLong = bitsToLong(applyMask)

                //println("value:  ${numToBitArray.contentToString()} (decimal $memVal)")
                //println("mask:   ${mask}")
                //println("result: ${applyMask} (decimal bitsToLong)")

//                mem[memPos.toInt()] = bitsToLong

            }
        }
        return mem.map { it.value }.sum()
    }

    private fun expandFloating(applyMask: List<String>): List<List<String>> {
        val toProcess = mutableListOf(applyMask)
        val results = mutableListOf<List<String>>()
        while (toProcess.size > 0) {
            val current = toProcess.removeAt(0)
            if (!current.contains("X")) {
                results.add(current)
            } else {
                //println("current $current")
                val  pos = current.indexOf("X")

                val first = current.toMutableList()
                first[pos] = "1"
                toProcess.add(first)

                val second = current.toMutableList()
                second[pos] = "0"
                toProcess.add(second)
            }
        }

        return results
    }

    fun applyMask2(x: IntArray): List<String> {
        val y = x.map { it.toString() }.toMutableList()
        mask.forEachIndexed { index, c ->
            if (c != '0') {
                y[index] = c.toString()
            }
        }
        return y
    }
}

