package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.World
import spock.lang.Specification

import java.util.stream.Collectors

class Day17Test extends Specification {

    def EXAMPLE = """
        .#.
        ..#
        ###""".stripIndent().trim().lines().collect(Collectors.toList())

    def "part 1"() {
        given:
        def testSubject = new Day17(new World(EXAMPLE))

        expect:
        testSubject.part1() == 42
    }

    def "part 2"() {
        given:
        def testSubject = new Day17(new World(EXAMPLE))

        expect:
        testSubject.part2() == 42
    }
}
