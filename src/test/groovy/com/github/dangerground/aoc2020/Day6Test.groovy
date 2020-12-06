package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.stream.Collectors

class Day6Test extends Specification {

    static final EXAMPLE_INPUT = [
            ["abc"],
            ["a", "b", "c"],
            ["ab", "ac"],
            ["a", "a", "a", "a"],
            ["b"]
    ]

    @Subject
    Day6 testSubject = new Day6(EXAMPLE_INPUT)

    def "part 1"() {
        when:
        def result = testSubject.part1()

        then:
        result == 11
    }
    def "part 2"() {
        when:
        def result = testSubject.part2()

        then:
        result == 6
    }

}
