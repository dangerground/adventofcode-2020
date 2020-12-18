package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.World
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class Day18Test extends Specification {

    def EXAMPLE = """
        .#.
        ..#
        ###""".stripIndent().trim().lines().collect(Collectors.toList())

    @Unroll
    def "part 1  #line == #result"() {
        given:
        def testSubject = new Day18(EXAMPLE)

        expect:
        testSubject.calculate(line) == result

        where:
        line                                              | result
        "2 * 3 + (4 * 5)"                                 | 26
        "5 + (8 * 3 + 9 + 3 * 4 * 3)"                     | 437
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"       | 12240
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2" | 13632
    }

    @Unroll
    def "part 2  #line == #result"() {
        given:
        def testSubject = new Day18(EXAMPLE)

        expect:
        testSubject.calculate2(line) == result

        where:
        line                                              | result
        "1 + 2 * 3 + 4 * 5 + 6"                           | 231
        "1 + (2 * 3) + (4 * (5 + 6))"                     | 51
        "2 * 3 + (4 * 5)"                                 | 46
        "5 + (8 * 3 + 9 + 3 * 4 * 3)"                     | 1445
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"       | 669060
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2" | 23340
    }

}
