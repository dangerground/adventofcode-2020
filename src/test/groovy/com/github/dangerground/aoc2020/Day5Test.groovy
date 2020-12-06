package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.stream.Collectors

class Day5Test extends Specification {

    static final EXAMPLE_INPUT = """
        BFFFBBFRRR
        FFFBBBFRRR
        BBFFBBFRLL
        """.stripIndent().trim().lines().collect(Collectors.toList())

    @Subject
    Day5 testSubject = new Day5(EXAMPLE_INPUT)

    def "input converted to seats"() {
        expect:
        testSubject.getSeats().size() == 3
    }

    @Unroll
    def "test seat conversion(#input) = (#row,#column) = #id"() {
        when:
        def seat = new Seat(input)

        then:
        seat.row == row
        seat.column == column
        seat.id == id

        where:
        input        | row | column | id
        "FBFBBFFRLR" | 44  | 5      | 357
        "BFFFBBFRRR" | 70  | 7      | 567
        "FFFBBBFRRR" | 14  | 7      | 119
        "BBFFBBFRLL" | 102 | 4      | 820
        "BBFFBBBRLL" | 103 | 4      | 828
    }

    def "get highest seat id"() {
        expect:
        testSubject.highestSeadId() == 820
    }
}
