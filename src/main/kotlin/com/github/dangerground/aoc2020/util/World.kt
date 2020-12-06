package com.github.dangerground.aoc2020.util

class World(private val map: List<String>) {

    var endlessWidth = false

    fun getCell(row: Int, column: Int): Char? {
        val safeColumn = if (endlessWidth) column % getColumnCount() else column

        return map.getOrNull(row)?.getOrNull(safeColumn)
    }

    fun isChar(row: Int, column: Int, char: Char) = getCell(row, column) == char

    fun getRowCount() = map.size
    fun getColumnCount() = map[0].length
}