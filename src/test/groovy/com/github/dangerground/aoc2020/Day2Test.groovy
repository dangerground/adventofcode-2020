package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class Day2Test extends Specification {

    @Subject
    Day2 testSubject = new Day2()

    def "test input splitter"() {
        when:
        def result = new PasswordAndPolicy("1-3 a: abcde")

        then:
        with(result) {
            password == " abcde"
            policy == "a"
            num1 == 1
            num2 == 3
        }
    }


    @Unroll
    def "input [#input] is matching [#isValid] by count"() {
        given:
        def pwAndPolicy = new PasswordAndPolicy(input)

        when:
        def result = testSubject.isPasswordMatchingByCount(pwAndPolicy)

        then:
        result == isValid

        where:
        input              | isValid
        "1-3 a: abcde"     | true
        "1-3 b: cdefg"     | false
        "2-9 c: ccccccccc" | true
    }

    def "is input matching by count"() {
        expect:
        testSubject.isMatchingByCount("1-3 a: abcde")
    }

    @Unroll
    def "input [#input] is matching [#isValid] by position"() {
        given:
        def pwAndPolicy = new PasswordAndPolicy(input)

        when:
        def result = testSubject.isPasswordMatchingByPosition(pwAndPolicy)

        then:
        result == isValid

        where:
        input              | isValid
        "1-3 a: abcde"     | true
        "1-3 b: cdefg"     | false
        "2-9 c: ccccccccc" | false

        // custom test data
        "3-5 a: aacaa"     | true
    }

    def "is input matching by position"() {
        expect:
        testSubject.isMatchingByPosition("1-3 a: abcde")
    }
}
