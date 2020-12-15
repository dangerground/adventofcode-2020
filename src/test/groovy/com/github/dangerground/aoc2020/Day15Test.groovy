package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class Day15Test extends Specification {

    @Unroll
    def "part 1 #input | number"() {
        given:
        def testSubject = new Day15(input)

        expect:
        testSubject.part1() == number

        where:
        input   | number
        ['0,3,6'] | 436
        /*['1,3,2'] | 1
        ['2,1,3'] | 10
        ['1,2,3'] | 27
        ['2,3,1'] | 78
        ['3,2,1'] | 438
        ['3,1,2'] | 1836*/
    }

    @Unroll
    def "part 2 #input | number"() {
        given:
        def testSubject = new Day15(input)

        expect:
        testSubject.part2() == number

        where:
        input   | number
        ['0,3,6'] | 175594
        //['2,1,3'] | 3544142
        //['1,2,3'] | 261214
        //['2,3,1'] | 6895259
        //['3,2,1'] | 18
        //['1,3,2'] | 2578
        //['3,1,2'] | 362
    }
}
