package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.stream.Collectors

class Day7Test extends Specification {

    public static final List<String> EXAMPLE_INPUT = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
            """.stripIndent().trim().lines().collect(Collectors.toList())

    def "bagrules"() {
        when:
        def result = new BagRule(rule)

        then:
        result.innerBags.size() == bagcount

        where:
        rule                                                              | bagcount
        "faded blue bags contain no other bags."                          | 0
        "dotted black bags contain no other bags."                        | 0
        "light red bags contain 1 bright white bag, 2 muted yellow bags." | 2
    }

    def "part 1"() {
        given:
        def testSubject = new Day7(EXAMPLE_INPUT)

        when:
        def result = testSubject.part1()

        then:
        result == 4
    }

    @Unroll
    def "count bags #color = #count"() {
        given:
        def testSubject = new Day7(EXAMPLE_INPUT)

        expect:
        testSubject.getInnerBagCount(color) == count

        where:
        color          | count
        "faded blue"   | 0
        "dotted black" | 0
        "vibrant plum" | 11
        "dark olive"   | 7
        "shiny gold"   | 32 // 1 + 1*7 + 2 + 2*11
    }

    def "part 2"() {
        given:
        def example = """
            shiny gold bags contain 2 dark red bags.
            dark red bags contain 2 dark orange bags.
            dark orange bags contain 2 dark yellow bags.
            dark yellow bags contain 2 dark green bags.
            dark green bags contain 2 dark blue bags.
            dark blue bags contain 2 dark violet bags.
            dark violet bags contain no other bags.
            """.stripIndent().trim().lines().collect(Collectors.toList())

        def testSubject = new Day7(example)

        when:
        def result = testSubject.part2()

        then:
        result == 126
    }

}
