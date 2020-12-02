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
        fun readInputForDayAsIntList(day: Int): List<Int> {
            return readInputForDayAsStringList(day)
                    .map { s -> s.toInt() }
                    .toList()
        }
    }
}