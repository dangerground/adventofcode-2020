package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Subject

class Day1Test extends Specification {

    public static final List<Integer> EXAMPLE_INPUT = [1721, 979, 366, 299, 675, 1456]
    @Subject
    Day1 testSubject = new Day1()

    def "part 1 - example get correct parts"() {
        when:
        def result = testSubject.getTwoNumbersMatching(EXAMPLE_INPUT, 2020)

        then:
        result.size() == 2
        result.contains(1721)
        result.contains(299)
    }

    def "part 1 - example get matching result"() {
        when:
        def result = testSubject.getMultipliedIntermediateOfTwo(EXAMPLE_INPUT, 2020)

        then:
        result == 514579
    }

    def "part 2 - example get correct parts"() {
        when:
        def result = testSubject.getThreeNumbersMatching(EXAMPLE_INPUT, 2020)

        then:
        result.size() == 3
        result.contains(979)
        result.contains(366)
        result.contains(675)
    }

    def "part 2 - example get matching result"() {
        when:
        def result = testSubject.getMultipliedIntermediateOfThree(EXAMPLE_INPUT, 2020)

        then:
        result == 241861950
    }

}
