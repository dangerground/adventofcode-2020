package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import java.lang.Exception

class Day4(batches: List<List<String>>) {
    var passports = batches.map { Passport(it) }

    fun countPassportsWithRequiredField() = passports.filter { it.hasRequiredFields() }.count()
    fun countValidPassports() = passports.filter { it.isValid() }.count()
}

class Passport(lines: List<String>) {

    private val expectedFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    private val expectedEyeColor = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    private val expectedHairColor = Regex("^#[0-9a-f]{6}$")
    private val expectedPassportNumber = Regex("^[0-9]{9}$")

    private val fields = HashMap<String, String>()

    init {
        lines.joinToString(" ")
            .trim()
            .split(Regex.fromLiteral(" "))
            .map { it.split(":") }
            .forEach {
                fields[it[0]] = it[1]
            }
    }

    fun size() = fields.size

    fun hasRequiredFields(): Boolean {
        return expectedFields.filter { fields[it] != null }.count() == expectedFields.size
    }

    fun isValid(): Boolean {
        return hasRequiredFields()
                && isValidBirthYear()
                && isValidIssueYear()
                && isValidExpirationYear()
                && isValidHeight()
                && isValidHairColor()
                && isValidEyeColor()
                && isValidPassportNumber()

    }

    private fun isValidBirthYear() = isYearBetween("byr", 1920, 2002)
    private fun isValidIssueYear() = isYearBetween("iyr", 2010, 2020)
    private fun isValidExpirationYear() = isYearBetween("eyr", 2020, 2030)
    private fun isValidHeight() = isHeightBetween("in", 59, 76) || isHeightBetween("cm", 150, 193)
    private fun isValidHairColor() = fields.getOrDefault("hcl", "").matches(expectedHairColor)
    private fun isValidEyeColor() = expectedEyeColor.contains(fields.getOrDefault("ecl", ""))
    private fun isValidPassportNumber() = fields.getOrDefault("pid", "").matches(expectedPassportNumber)

    private fun isYearBetween(field: String, lower: Int, upper: Int): Boolean {
        return try {
            val year = fields.getOrDefault(field, "").toInt()
            year in lower..upper
        } catch (e: Exception) {
            false
        }
    }

    private fun isHeightBetween(unit: String, lower: Int, upper: Int): Boolean {
        val height = fields.getOrDefault("hgt", "")
        if (!height.matches(Regex("^\\d+$unit$"))) {
            return false
        }
        return try {
            val heightNumber = height.substring(0, height.length - unit.length).toInt()
            heightNumber in lower..upper
        } catch (e: Exception) {
            false
        }
    }
}

fun main() {
    val input = DayInput.batchesOfStringList(4)
    val day4 = Day4(input)

    // part 1
    val part1 = day4.countPassportsWithRequiredField()
    println("result part 1: $part1")

    // part2
    val part2 = day4.countValidPassports()
    println("result part 2: $part2")
}