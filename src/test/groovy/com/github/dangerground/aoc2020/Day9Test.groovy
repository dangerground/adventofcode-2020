package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class Day9Test extends Specification {

    public static final EXAMPLE_INPUT = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
            """.stripIndent().trim().lines().map({ it.toLong() }).collect(Collectors.toList())

    @Unroll
    def "is sum of last 5 numbers (#input); #check = #isValid"() {
        given:
        def testSubject = new Day9(input, 5)

        expect:
        testSubject.isSumOfLast(5) == isValid

        where:
        input               | isValid
        [1, 2, 3, 4, 5, 6]  | true
        [1, 2, 3, 4, 5, 10] | false
    }

    def "part 1"() {
        given:
        def testSubject = new Day9(EXAMPLE_INPUT, 5)

        when:
        def result = testSubject.part1()

        then:
        result == 127
    }

    def "part 2"() {
        given:
        def testSubject = new Day9(EXAMPLE_INPUT, 5)

        when:
        def result = testSubject.part2(127)

        then:
        result == 62
    }

}
