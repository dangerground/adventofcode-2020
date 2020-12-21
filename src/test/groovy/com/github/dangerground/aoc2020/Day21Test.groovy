package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import spock.lang.Specification

import java.util.stream.Collectors

class Day21Test extends Specification {

    def EXAMPLE = """
        mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
        trh fvjkl sbzzf mxmxvkd (contains dairy)
        sqjhc fvjkl (contains soy)
        sqjhc mxmxvkd sbzzf (contains fish)""".stripIndent().trim().lines().collect(Collectors.toList())


    def "part 1"() {
        given:
        def testSubject = new Day21(EXAMPLE)

        expect:
        testSubject.part1() == 5
    }

    def "part 2"() {
        given:
        def testSubject = new Day21(EXAMPLE)

        expect:
        testSubject.part2() == "mxmxvkd,sqjhc,fvjkl"
    }

}
