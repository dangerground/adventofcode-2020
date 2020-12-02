package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class Day2Test extends Specification {

    @Subject
    Day2 testSubject = new Day2()

    def "test input splitter"() {
        when:
        def result = testSubject.getPolicyAndPassword("1-3 a: abcde")

        then:
        result.password == " abcde"
        with(result.policy) {
            getChar() == "a"
            min == 1
            max == 3
        }
    }

    @Unroll
    def "input [#policy] is matching [#password] by count"(Policy policy, String password, boolean isValid) {
        when:
        def result = testSubject.isPasswordMatchingByCount(password, policy)

        then:
        result == isValid

        where:
        policy                | password     | isValid
        new Policy("a", 1, 3) | " abcde"     | true
        new Policy("b", 1, 3) | " cdefg"     | false
        new Policy("c", 2, 9) | " ccccccccc" | true
    }

    def "is input matching by count"() {
        expect:
        testSubject.isMatchingByCount("1-3 a: abcde")
    }

    @Unroll
    def "input [#policy] is matching [#password] by position"(Policy policy, String password, boolean isValid) {
        when:
        def result = testSubject.isPasswordMatchingByPosition(password, policy)

        then:
        result == isValid

        where:
        policy                | password     | isValid
        new Policy("a", 1, 3) | " abcde"     | true
        new Policy("b", 1, 3) | " cdefg"     | false
        new Policy("c", 2, 9) | " ccccccccc" | false

        // custom test data
        new Policy("a", 3, 5) | " aacaa"     | true
    }

    def "is input matching by position"() {
        expect:
        testSubject.isMatchingByPosition("1-3 a: abcde")
    }
}
