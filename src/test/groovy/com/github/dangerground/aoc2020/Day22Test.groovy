package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import spock.lang.Specification

import java.util.stream.Collectors

class Day22Test extends Specification {

    def EXAMPLE = """
        Player 1:
        9
        2
        6
        3
        1
        
        Player 2:
        5
        8
        4
        7
        10""".stripIndent().trim().lines().collect(Collectors.toList())


    def "part 1"() {
        given:
        def testSubject = new Day22(DayInput.linesToBatches(EXAMPLE))

        expect:
        testSubject.part1() == 306
    }

    def "part 2"() {
        given:
        def testSubject = new Day22(DayInput.linesToBatches(EXAMPLE))

        expect:
        testSubject.part2() == 291
    }

    def "part 2 - with infinite"() {
        given:
        def input = """
            Player 1:
            43
            19
            
            Player 2:
            2
            29
            14""".stripIndent().trim().lines().collect(Collectors.toList())
        def testSubject = new Day22(DayInput.linesToBatches(input))

        expect:
        testSubject.part2() == 291
    }

}
