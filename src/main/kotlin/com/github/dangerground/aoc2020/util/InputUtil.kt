package com.github.dangerground.aoc2020.util

import java.io.File

class InputUtil {

    companion object {

        @JvmStatic
        fun readInputForDayAsStringList(day: Int): List<String> {
            val name = "input$day.txt"

            val resource = this::class.java.classLoader.getResource(name)
            val file = resource.file

            return File(file).readText().lines()
        }

        @JvmStatic
        fun readBatchesForDayAsStringList(day: Int): List<List<String>> {
            val input = readInputForDayAsStringList(day)

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
        fun readInputForDayAsIntList(day: Int): List<Int> {
            return readInputForDayAsStringList(day)
                    .map { s -> s.toInt() }
                    .toList()
        }
    }
}