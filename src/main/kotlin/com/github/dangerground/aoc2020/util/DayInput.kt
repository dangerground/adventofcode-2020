package com.github.dangerground.aoc2020.util

import java.io.File

class DayInput {

    companion object {

        @JvmStatic
        fun asStringList(day: Int): List<String> {
            val name = "input$day.txt"

            val resource = this::class.java.classLoader.getResource(name)
            val file = resource.file

            return File(file).readText().lines()
        }

        @JvmStatic
        fun batchesOfStringList(day: Int): List<List<String>> {
            val input = asStringList(day)

            return linesToBatches(input)
        }

        @JvmStatic
        fun linesToBatches(input: List<String>): List<List<String>> {
            val batches = mutableListOf<List<String>>()

            var tmpList = mutableListOf<String>()
            input.forEach {
                if (it.isEmpty()) {
                    batches.add(tmpList)
                    tmpList = mutableListOf<String>()
                } else {
                    tmpList.add(it)
                }
            }
            batches.add(tmpList)

            return batches
        }

        @JvmStatic
        fun asIntList(day: Int): List<Int> {
            return asStringList(day)
                .map { s -> s.toInt() }
                .toList()
        }

        @JvmStatic
        fun asLongList(day: Int): List<Long> {
            return asStringList(day)
                .map { s -> s.toLong() }
                .toList()
        }

        @JvmStatic
        fun asWorld(day: Int): World {
            val name = "input$day.txt"

            val resource = this::class.java.classLoader.getResource(name)
            val file = resource.file

            return World(File(file).readText().lines())
        }
    }
}
