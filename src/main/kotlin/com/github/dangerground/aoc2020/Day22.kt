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
        while (!player1.isEmpty() && !player2.isEmpty()) {
            if (player1.first() > player2.first()) {
                player1.add((player1.remove()))
                player1.add(player2.remove())
            } else {
                player2.add((player2.remove()))
                player2.add(player1.remove())
            }
        }

        return calcResult()
    }

    private fun calcResult() = player1.reversed().mapIndexed { idx, it -> (idx + 1) * it }.sum() + player2.reversed()
        .mapIndexed { idx, it -> (idx + 1) * it }.sum()

    fun part2(): Long {

        val player1: Queue<Long> = LinkedList(input[0].drop(1).map { it.toLong() }.toList())
        val player2: Queue<Long> = LinkedList(input[1].drop(1).map { it.toLong() }.toList())
        playRecursive(player1, player2)


        println("\n== Post-game results ==")
        println("Player 1's deck: $player1")
        println("Player 2's deck: $player2")

        return calc(player1) + calc(player2)
    }

    private fun calc(player1: Queue<Long>) =
        player1.reversed().mapIndexed { idx, it -> (idx + 1) * it }.sum()

    var game = 0
    fun playRecursive(rec1: Queue<Long>, rec2: Queue<Long>): Int {
        game++

        val thisgame = game
        println()
        println("=== Game $thisgame ===")
        val played1Rounds = mutableSetOf<Long>()
        val played2Rounds = mutableSetOf<Long>()

        var round = 0
        do {
            round++

            println()
            println("-- Round $round (Game $thisgame) --")

            println("Player 1's deck: $rec1")
            println("Player 2's deck: $rec2")

            val calc1 = rec1.hashCode().toLong()
            val calc2 = rec2.hashCode().toLong()

            // case 1: remember rounds
            if (played1Rounds.contains(calc1) && played2Rounds.contains(calc2)) {
                return 1
            }
            played1Rounds.add(calc1)
            played2Rounds.add(calc2)

            // draw cards
            val card1 = rec1.remove()
            val card2 = rec2.remove()

            println("Player 1 plays: $card1")
            println("Player 2 plays: $card2")

            if (rec1.size >= card1 && rec2.size >= card2) {
                println("Playing a sub-game to determine the winner...")
                val winner = playRecursive(
                    LinkedList(rec1.toList().subList(0, card1.toInt())),
                    LinkedList(rec2.toList().subList(0, card2.toInt()))
                )
                println("The winner of game ${thisgame + 1} is player $winner!")
                println()
                println("...anyway, back to game 1.")
                if (winner == 1) {
                    println("Player 1 wins round $round of game $thisgame!")
                    rec1.add(card1)
                    rec1.add(card2)
                } else { // winer == 2
                    println("Player 2 wins round $round of game $thisgame!")
                    rec2.add(card2)
                    rec2.add(card1)
                }
            } else {
                // determined
                if (card1 > card2) {
                    println("Player 1 wins round $round of game $thisgame!")
                    rec1.add(card1)
                    rec1.add(card2)
                } else {
                    println("Player 2 wins round $round of game $thisgame!")
                    rec2.add(card2)
                    rec2.add(card1)
                }
            }


        } while (!rec1.isEmpty() && !rec2.isEmpty())
        return if (rec1.isEmpty()) 2 else  1
    }
}

class Win(val winner: Int?, val cards: List<Int>?, val done: Boolean?, result: Int? = null)
