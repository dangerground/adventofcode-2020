package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.World
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class Day12Test extends Specification {

    static final INITIAL = """
        F10
        N3
        F7
        R90
        F11
        """.stripIndent().trim().lines().collect(Collectors.toList())

    def "part 1"() {
        given:
        def testSubject = new Day12(INITIAL)

        expect:
        testSubject.part1() == 25
    }


    def "part 2"() {
        given:
        def testSubject = new Day12(INITIAL)

        expect:
        testSubject.part2() == 286
    }
}
