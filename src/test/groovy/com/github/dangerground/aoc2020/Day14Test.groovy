package com.github.dangerground.aoc2020


import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class Day14Test extends Specification {

    static final INITIAL = """
        mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
        mem[8] = 11
        mem[7] = 101
        mem[8] = 0
        """.stripIndent().trim().lines().collect(Collectors.toList())

    def "part 1"() {
        given:
        def testSubject = new Day14(INITIAL)

        expect:
        testSubject.part1() == 165
    }

    def "part 2"() {
        given:
        def input = """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
            """.stripIndent().trim().lines().collect(Collectors.toList())
        def testSubject = new Day14(input)

        expect:
        testSubject.part2() == 208
    }
}
