package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import com.github.dangerground.aoc2020.util.World
import kotlin.math.max

fun main() {
    val process = Day18(DayInput.asStringList(18))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day18(val input: List<String>) {

    fun calculate(line: String): Long {
        val ops = parseLine(line)

        return ops2num(ops)
    }
    fun calculate2(line: String): Long {
        val ops = parseLine(line)

        return ops2num2(ops)
    }

    private fun parseLine(line: String) = line.map {
        when {
            it.isDigit() -> AST(Op.NUM, it.toString().toLong())
            it == '+' -> AST(Op.PLUS)
            it == '*' -> AST(Op.MUL)
            it == '(' -> AST(Op.OPEN)
            it == ')' -> AST(Op.CLOSE)
            it == ' ' -> null
            else -> {
                println("Unknown $it")
                null
            }
        }
    }.filterNotNull()

    private fun ops2num(ops: List<AST>): Long {
        println()
        println(ops)
        var left = 0L
        var right: Long? = null
        val subOps = mutableListOf<AST>()
        var open = 0
        var op: Op? = null
        var lastOp: Op? = null

        val newOps = mutableListOf<AST>()

        ops.forEach {
            if (open == 1 && it.op == Op.CLOSE) {
                newOps.add(AST(Op.NUM, ops2num(subOps)))
                subOps.clear()
            } else if (open > 0) {
                subOps.add(it)
            } else if (it.op != Op.OPEN) {
                newOps.add(it)
            }
            if (it.op == Op.OPEN) {
                open++
            } else if (it.op == Op.CLOSE) {
                open--
            }
        }

        println("new: $newOps")

        newOps.forEach {
            if (open > 0) {
                subOps.add(it)
            }

            when (it.op) {
                Op.OPEN -> {
                    //print("(")

                    if (open == 0) {
                        lastOp = op
                    }
                    open++
                }
                Op.CLOSE -> {
                    //print(")")
                    open--
                    if (open == 0) {
                        op = lastOp
                        val num = ops2num(subOps.dropLast(1))
                        subOps.clear()

                        print(num)
                        if (left == 0L) {
                            left = num
                            op = null
                            return@forEach
                        } else {
                            right = num
                        }
                    }
                }
                Op.NUM -> {
                    //print(it.value)
                    if (left == 0L) {
                        left = it.value!!
                    } else {
                        right = it.value
                    }
                }
                else -> {
                    //print(if (it.op == Op.MUL) "*" else "+")
                    op = it.op
                }
            }

            if (open == 0 && right != null && op != null) {
                print(":: $left $op $right")
                if (op == Op.PLUS) {
                    left += right!!
                } else if (op == Op.MUL) {
                    left *= right!!
                }
                println(" = $left")

                right = null
                op = null
            }
        }

        //println(" = $left")
        return left
    }

    private fun ops2num2(ops: List<AST>): Long {
        println()
        println(ops)
        var left = 0L
        var right: Long? = null
        val subOps = mutableListOf<AST>()
        var open = 0
        var op: Op? = null
        var lastOp: Op? = null

        val newOps = mutableListOf<AST>()

        ops.forEach {
            if (open == 1 && it.op == Op.CLOSE) {
                newOps.add(AST(Op.NUM, ops2num2(subOps)))
                subOps.clear()
            } else if (open > 0) {
                subOps.add(it)
            } else if (it.op != Op.OPEN) {
                newOps.add(it)
            }
            if (it.op == Op.OPEN) {
                open++
            } else if (it.op == Op.CLOSE) {
                open--
            }
        }


        val newOps2 = mutableListOf<AST>()
        val subList2 = mutableListOf<AST>()
        // NUM PLUS MUL
        if (newOps.any { it.op == Op.MUL }) {
            println("handle: $newOps")

            newOps.forEach {
                println("ast: $it")
                if (it.op == Op.MUL) {
                    println("SUB")
                    newOps2.add(AST(Op.NUM, ops2num2(subList2)))
                    newOps2.add(it)
                    subList2.clear()
                } else {
                    subList2.add(it)
                }
            }
            newOps2.add(AST(Op.NUM, ops2num2(subList2)))
        } else {
            println("skipped: $newOps")
            newOps2.addAll(newOps)
        }

        println("new2: $newOps2")


        newOps2.forEach {
            if (open > 0) {
                subOps.add(it)
            }

            when (it.op) {
                Op.OPEN -> {
                    //print("(")

                    if (open == 0) {
                        lastOp = op
                    }
                    open++
                }
                Op.CLOSE -> {
                    //print(")")
                    open--
                    if (open == 0) {
                        op = lastOp
                        val num = ops2num(subOps.dropLast(1))
                        subOps.clear()

                        print(num)
                        if (left == 0L) {
                            left = num
                            op = null
                            return@forEach
                        } else {
                            right = num
                        }
                    }
                }
                Op.NUM -> {
                    //print(it.value)
                    if (left == 0L) {
                        left = it.value!!
                    } else {
                        right = it.value
                    }
                }
                else -> {
                    //print(if (it.op == Op.MUL) "*" else "+")
                    op = it.op
                }
            }

            if (open == 0 && right != null && op != null) {
                print(":: $left $op $right")
                if (op == Op.PLUS) {
                    left += right!!
                } else if (op == Op.MUL) {
                    left *= right!!
                }
                println(" = $left")

                right = null
                op = null
            }
        }

        //println(" = $left")
        return left
    }

    fun part1(): Long {
        return input.map { calculate(it) }.sum()
    }

    fun part2(): Long {
        return input.map { calculate2(it) }.sum()
    }
}

class AST(val op: Op, val value: Long? = null) {
    override fun toString(): String {
        return "$op ($value)"
    }
}

enum class Op {
    PLUS,
    MUL,
    OPEN,
    CLOSE,
    NUM
}