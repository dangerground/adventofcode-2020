package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

class Day6(batches: List<List<String>>) {

    val groups = mutableListOf<Group>()

    init {
        batches.forEach {
            groups.add(Group(it))
        }
    }

    fun part1(): Int {
        return groups.sumOf { it.countAnswers() }
    }

    fun part2(): Int {
        return groups.sumOf {
            it.countAmbiguouslyAnswers()
        }
    }
}

class Group(private val answers: List<String>) {
    private val totalQuestionsAnswered = mutableSetOf<Char>()
    private val questionsAnsweredAmbiguously: Set<Char>

    init {
        val questionsAnswered = answers[0].toCharArray().toMutableSet()
        answers.forEachIndexed { index, member ->
            member.forEach { answer ->
                this.totalQuestionsAnswered.add(answer)
            }
            if (index > 0) {
                questionsAnswered.removeIf { !member.contains(it) }
            }
        }
        questionsAnsweredAmbiguously = questionsAnswered
    }

    fun countAnswers() = totalQuestionsAnswered.count()
    fun countAmbiguouslyAnswers() = questionsAnsweredAmbiguously.count()
    override fun toString(): String {
        return "Group(answers=$answers questionsAnsweredAmbiguously=${questionsAnsweredAmbiguously.size} ($questionsAnsweredAmbiguously))"
    }
}


fun main() {
    val input = DayInput.batchesOfStringList(6)
    val day6 = Day6(input)

    // part 1
    //val part1 = day6.part1()
    //println("result part 1: $part1")

    // part2
    val part2 = day6.part2()
    println("result part 2: $part2")
}