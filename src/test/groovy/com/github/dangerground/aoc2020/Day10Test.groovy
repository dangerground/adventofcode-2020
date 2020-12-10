package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Unroll

class Day10Test extends Specification {

    @Unroll
    def "part 1"() {
        given:
        def testSubject = new Day10(input)

        expect:
        testSubject.part1() == result

        where:
        result | input
        35     | [16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4]
        220    | [28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3]
    }

    @Unroll
    def "part 2 #result = #input"() {
        given:
        def testSubject = new Day10(input)

        expect:
        testSubject.part2() == result

        where:
        result | input
        //8      | [16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4]
        19208  | [28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3]
    }
}
