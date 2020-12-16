package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.DayInput
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class Day16Test extends Specification {

    def EXAMPLE = """
        class: 1-3 or 5-7
        row: 6-11 or 33-44
        seat: 13-40 or 45-50
        
        your ticket:
        7,1,14
        
        nearby tickets:
        7,3,47
        40,4,50
        55,2,20
        38,6,12""".stripIndent().trim().lines().collect(Collectors.toList())
    def EXAMPLE2 = """
        class: 0-1 or 4-19
        departure row: 0-5 or 8-19
        departure seat: 0-13 or 16-19
        
        your ticket:
        11,12,13
        
        nearby tickets:
        3,9,18
        15,1,5
        5,14,9""".stripIndent().trim().lines().collect(Collectors.toList())

    def "part 1"() {
        given:
        def testSubject = new Day16(DayInput.linesToBatches(EXAMPLE))

        expect:
        testSubject.part1() == 71
    }

    def "part 2"() {
        given:
        def testSubject = new Day16(DayInput.linesToBatches(EXAMPLE2))

        expect:
        testSubject.part2() == 143
    }
}
