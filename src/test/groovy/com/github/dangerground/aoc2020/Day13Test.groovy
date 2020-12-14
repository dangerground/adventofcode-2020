package com.github.dangerground.aoc2020


import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class Day13Test extends Specification {

    static final INITIAL = """
        939
        7,13,x,x,59,x,31,19
        """.stripIndent().trim().lines().collect(Collectors.toList())

    def "part 1"() {
        given:
        def testSubject = new Day13(INITIAL)

        expect:
        testSubject.part1() == 295
    }

    @Unroll
    def "part 2"() {
        given:
        def testSubject = new Day13(input)

        expect:
        testSubject.part2() == expected

        // 1068781 / 7 = 0
        // 1068782 / 13 = 0

        where:
        input                        | expected
        //['0', '7,13'] | 1068781
        //['0', '7,13,x,x,59,x,31,19'] | 1068781
        ['0', '17,x,13,19']          | 3417
        //['0', '67,7,59,61'] | 754018
        //['0', '67,x,7,59,61'] | 779210
        //['0', '67,7,x,59,61'] | 1261476
        //['0', '1789,37,47,1889'] | 1202161486
    }
}
