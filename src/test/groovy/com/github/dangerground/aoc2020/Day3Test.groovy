package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.Direction
import com.github.dangerground.aoc2020.util.World
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.stream.Collectors

class Day3Test extends Specification {

    static final EXAMPLE_INPUT = """
        ..##.......
        #...#...#..
        .#....#..#.
        ..#.#...#.#
        .#...##..#.
        ..#.##.....
        .#.#.#....#
        .#........#
        #.##...#...
        #...##....#
        .#..#...#.#
        """.stripIndent().trim().lines().collect(Collectors.toList())

    @Subject
    Day3 testSubject = new Day3(new World(EXAMPLE_INPUT))

    @Unroll
    def "test is tree (#row, #column) = #isTree"() {
        expect:
        testSubject.isTree(row, column) == isTree

        where:
        row | column | isTree
        1   | 3      | false
        2   | 6      | true
        3   | 9      | false
        4   | 12     | true
        5   | 15     | true
    }

    @Unroll
    def "test count for input #direction = #expected"() {
        when:
        def result = testSubject.countTrees(direction)

        then:
        result == expected

        where:
        direction           | expected
        new Direction(1, 1) | 2
        new Direction(1, 3) | 7
        new Direction(1, 5) | 3
        new Direction(1, 7) | 4
        new Direction(2, 1) | 2
    }

    def "test multiplied directions"() {
        def directions = [
                new Direction(1, 1),
                new Direction(1, 3),
                new Direction(1, 5),
                new Direction(1, 7),
                new Direction(2, 1)]
        when:
        def result = testSubject.countDirectionsMultiplied(directions)

        then:
        result == 336
    }
}
