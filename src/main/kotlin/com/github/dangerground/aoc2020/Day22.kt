package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import java.util.*

fun main() {
    val process = Day22(DayInput.batchesOfStringList(22))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day22(val input: List<List<String>>) {

    val player1: Queue<Int> = LinkedList(input[0].drop(1).map { it.toInt() }.toList())
    val player2: Queue<Int> = LinkedList(input[1].drop(1).map { it.toInt() }.toList())

    fun part1(): Int {
        println("player1")
        println(player1)
        println("player2")
        println(player2)

        while (!player1.isEmpty() && !player2.isEmpty()) {
            if (player1.first() > player2.first()) {
                player1.add((player1.remove()))
                player1.add(player2.remove())
            } else {
                player2.add((player2.remove()))
                player2.add(player1.remove())
            }
        }

        println("player1")
        println(player1)
        println("player2")
        println(player2)

        return player1.reversed().mapIndexed { idx, it -> (idx + 1) * it }.sum() + player2.reversed()
            .mapIndexed { idx, it -> (idx + 1) * it }.sum()
    }

    fun part2(): Int {
        return -1
    }
}
