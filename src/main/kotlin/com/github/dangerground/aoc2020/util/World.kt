package com.github.dangerground.aoc2020.util

class World(val map: MutableList<String>) {

    var endlessWidth = false

    fun getCell(row: Int, column: Int): Char? {
        val safeColumn = if (endlessWidth) column % getColumnCount() else column

        return map.getOrNull(row)?.getOrNull(safeColumn)
    }

    fun isChar(row: Int, column: Int, char: Char) = getCell(row, column) == char

    fun getRowCount() = map.size
    fun getColumnCount() = map[0].length

    fun setCell(row: Int, column: Int, char: Char) {
        val line = map[row].toCharArray().toMutableList()
        line[column] = char
        map[row] = String(line.toCharArray())
    }

    fun canSee(row: Int, column: Int, char: Char, direction: Direction): Boolean {
        var r = row + direction.down
        var c = column + direction.right
        while (true) {
            if (c < 0 || c >= getColumnCount() || r < 0 || r >= getRowCount()) {
                return false
            }

            if (isChar(r, c, char)) {
                return true
            }

            if (isChar(r, c, 'L')) {
                return false
            }
            //setCell(r, c, '-')

            r += direction.down
            c += direction.right
        }
    }

    fun debug() {
        map.forEach {
            println(it)
        }
    }


    fun flip() : World {
        val newWorld = World(map.toList().toMutableList())
        val rowCount = getRowCount() - 1
        for (r in 0..rowCount) {
            for (c in 0 until getColumnCount()) {
                newWorld.setCell(rowCount - r, c, getCell(r, c)!!)
            }
        }

        return newWorld
    }

    fun rotateLeft() : World {
        val newWorld = World(map.toList().toMutableList())
        val rowCount = getRowCount() - 1
        for (r in 0 until getRowCount()) {
            for (c in 0 until getColumnCount()) {
                newWorld.setCell(rowCount - c, r, getCell(r, c)!!)
            }
        }

        return newWorld
    }
}

class Direction(val down: Int, val right: Int) {
    override fun toString(): String {
        return "Direction(down=$down, right=$right)"
    }
}