package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

class Day8(input: List<String>) {

    val instructions = input.map { Instruction(it) }
    val handheld = Handheld()

    fun part1(): Int {
        return try {
            boot(instructions)
            -1
        } catch (e: java.lang.Exception) {
            handheld.regAcc
        }
    }

    fun boot(instructions: List<Instruction>): Int {
        handheld.reset()
        while (handheld.instrPtr < instructions.size) {
            handheld.instr(instructions[handheld.instrPtr])
        }

        return handheld.regAcc
    }

    fun part2(): Int {
        var changedInstr = -1
        val instructions = instructions.toMutableList()
        while (changedInstr < instructions.size ) {
            changedInstr++
            while(changedInstr < instructions.size && !switchInstr(instructions, changedInstr)) {
                changedInstr++
            }
            try {
                boot(instructions)
                return handheld.regAcc
            } catch (e: java.lang.Exception) {
                // revert change
                switchInstr(instructions, changedInstr)
            }
        }
        return handheld.regAcc
    }

    fun switchInstr(instructions: MutableList<Instruction>, ptr: Int): Boolean {
        if (instructions[ptr].instr == "jmp") {
            instructions[ptr].instr = "nop"
            return true
        } else if (instructions[ptr].instr == "nop") {
            instructions[ptr].instr = "jmp"
            return true
        }

        return false
    }
}

class Instruction(line: String) {
    var instr: String
    val argument: Int

    init {
        val match = Regex("([a-z]+) ([+-]\\d+)").matchEntire(line)!!
        instr = match.groupValues[1]
        argument = match.groupValues[2].replace("+", "").toInt()

    }

    override fun toString(): String {
        return "$instr $argument"
    }
}

class Handheld {
    var regAcc = 0
    var instrPtr = 0

    val instrCount = mutableSetOf<Int>()

    fun reset() {
        regAcc = 0
        instrPtr = 0
        instrCount.clear()
    }

    fun instr(next: Instruction) {
        checkInstrPtr()

        //println(next)
        when (next.instr) {
            "acc" -> {
                regAcc += next.argument; instrPtr++
            }
            "jmp" -> {
                instrPtr += next.argument
            }
            "nop" -> {
                instrPtr++
            }
        }
    }

    private fun checkInstrPtr() {
        if (instrCount.contains(instrPtr)) {
            throw Exception()
        }
        instrCount.add(instrPtr)
    }
}

fun main() {
    val input = DayInput.asStringList(8)
    val day8 = Day8(input)

    // part 1
    val part1 = day8.part1()
    println("result part 1: $part1")

    // part2
    val part2 = day8.part2()
    println("result part 2: $part2")
}