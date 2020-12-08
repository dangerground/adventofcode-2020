package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class Day8Test extends Specification {

    public static final List<String> EXAMPLE_INPUT = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
            """.stripIndent().trim().lines().collect(Collectors.toList())

    def "instruction"() {
        when:
        def result = new Instruction(line)

        then:
        result.instr == instr

        where:
        line      | instr | argument
        "acc -99" | "acc" | -99
        "jmp +4"  | "jmp" | 4
    }

    def "part 1"() {
        given:
        def testSubject = new Day8(EXAMPLE_INPUT)

        when:
        def result = testSubject.part1()

        then:
        result == 5
    }

    def "part 2 - manually"() {
        given:
        def copy = EXAMPLE_INPUT.collect()
        copy[7] = "nop -4"
        def testSubject = new Day8(copy)

        when:
        def result = testSubject.boot()

        then:
        result == 8
    }

    def "part 2"() {
        given:
        def testSubject = new Day8(EXAMPLE_INPUT)

        when:
        def result = testSubject.part2()

        then:
        result == 8
    }

}
