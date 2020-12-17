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
        testSubject.part1() == 112
    }
    def "part 1 bla"() {
        given:
        def testSubject = new Day17(new World(EXAMPLE))

        expect:
        testSubject.world.getCell(0, 0, 0) == 0
        testSubject.world.getCell(0, 0, 1) == 1
        testSubject.world.getCell(0, 2, 0) == 1
        testSubject.world.getCell(0, -1, 0) == 1
        testSubject.world.getCell(-1, 0, 0) == 0
        testSubject.world.getCell(0, 0, 0) == 0
        testSubject.world.getCell(0, 1, -1) == 1
        testSubject.world.getCell(0, 3, 2) == 0
        testSubject.world.getCell(0, 1, 3) == 0
    }

    def "part 2"() {
        given:
        def testSubject = new Day17(new World(EXAMPLE))

        expect:
        testSubject.part2() == 42
    }
}
