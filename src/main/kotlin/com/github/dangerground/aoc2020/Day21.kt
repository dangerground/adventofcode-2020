package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput

fun main() {
    val process = Day21(DayInput.asStringList(21))

    println("result part 1: ${process.part1()}")
    println("result part 2: ${process.part2()}")
}

class Day21(val input: List<String>) {

    val ingredientsMap = input.map {
        val tmp = it.split(" (contains ")
        tmp[0].split(" ") to tmp[1].dropLast(1).split(", ").map { it.trim() }
    }.toMap()

    val alergenes = mutableMapOf<String, MutableSet<String>>()
    val foods = mutableMapOf<String, MutableSet<String>>()

    init {
        ingredientsMap.forEach { entry ->
            entry.value.forEach { alergene ->
                if (!alergenes.containsKey(alergene)) {
                    alergenes[alergene] = entry.key.toMutableSet()
                } else {
                    alergenes[alergene]!!.removeIf { !entry.key.contains(it) }
                    //alergenes[alergene]!!.addAll(entry.key)
                }
            }
        }
        ingredientsMap.forEach { entry ->
            entry.key.forEach { food ->
                if (!foods.containsKey(food)) {
                    foods[food] = entry.value.toMutableSet()
                } else {
                    foods[food]!!.addAll(entry.value)
                }
            }
        }

        val single = mutableSetOf<String>()
        var changed: Boolean
        do {
            changed = false
            alergenes.forEach {
                if (it.value.size == 1) {
                    single.add(it.value.first())
                } else {
                    val before = it.value.size
                    it.value.removeIf { single.contains(it) }
                    if (before != it.value.size) {
                        changed = true
                    }
                }
            }
        } while (changed)

        val singleFood = mutableMapOf<String, String>()
        do {
            changed = false
            foods.forEach {
                if (it.value.size == 1) {
                    singleFood[it.value.first()] = it.key
                } else {
                    val before = it.value.size
                    it.value.removeIf { singleFood.keys.contains(it) }
                    if (before != it.value.size) {
                        changed = true
                    }
                }
            }
        } while (changed)

        println("single: $foods")
    }

    fun part1(): Int {
        val allFoods = ingredientsMap.keys.flatten().toSet()

        val safeIngredients = allFoods.toMutableList()
        alergenes.values.forEach { list ->
            safeIngredients.removeIf { list.contains(it) }
        }

        return safeIngredients.map { ingr ->
            ingredientsMap.keys.count { list -> list.contains(ingr) }
        }.sum()
    }


    fun part2(): String {
        val dangerous = alergenes.map { it.key to it.value.first() }.toMap()

        println(alergenes)
//        println(dangerous)

        if (alergenes.map { it.value.size > 1 }.filter { it }.count() > 0) println("ERROR")
        return dangerous.keys.sorted().map { dangerous[it]!! }.joinToString(separator = ",")
    }
}
