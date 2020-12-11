package com.github.dangerground.aoc2020

import com.github.dangerground.aoc2020.util.World
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class Day11Test extends Specification {

    static final INITIAL = """
        L.LL.LL.LL
        LLLLLLL.LL
        L.L.L..L..
        LLLL.LL.LL
        L.LL.LL.LL
        L.LLLLL.LL
        ..L.L.....
        LLLLLLLLLL
        L.LLLLLL.L
        L.LLLLL.LL
        """.stripIndent().trim().lines().collect(Collectors.toList())

    static final TICK_1 = """
        #.##.##.##
        #######.##
        #.#.#..#..
        ####.##.##
        #.##.##.##
        #.#####.##
        ..#.#.....
        ##########
        #.######.#
        #.#####.##
        """.stripIndent().trim().lines().collect(Collectors.toList())

    static final TICK_2 = """
        #.LL.L#.##
        #LLLLLL.L#
        L.L.L..L..
        #LLL.LL.L#
        #.LL.LL.LL
        #.LLLL#.##
        ..L.L.....
        #LLLLLLLL#
        #.LLLLLL.L
        #.#LLLL.##
        """.stripIndent().trim().lines().collect(Collectors.toList())

    static final TICK_END = """
        #.#L.L#.##
        #LLL#LL.L#
        L.#.L..#..
        #L##.##.L#
        #.#L.LL.LL
        #.#L#L#.##
        ..L.L.....
        #L#L##L#L#
        #.LLLLLL.L
        #.#L#L#.##
        """.stripIndent().trim().lines().collect(Collectors.toList())


    @Unroll
    def "isOccupied"() {
        given:
        def testSubject = new Day11(new World(input))

        expect:
        testSubject.countNeighbors(row, column) == neighbors

        // L.L
        // LLL
        // L.L

        where:
        input                 | row | column | neighbors
        ["L.L", "LLL", "L.L"] | 0   | 0      | 0
        ["L.L", "#LL", "L.L"] | 0   | 0      | 1
    }

    @Unroll
    def "next Tick #input - #newWorld "() {
        given:
        def testSubject = new Day11(new World(input))

        when:
        testSubject.nextTick()

        then:
        testSubject.world.map == newWorld

        // #.#
        // #L#
        // #.#

        where:
        input                 | newWorld
        ["L.L", "LLL", "L.L"] | ["#.#", "###", "#.#"]
        ["#.#", "###", "#.#"] | ["#.#", "#L#", "#.#"]
    }

    def "run all Ticks"() {
        given:
        def testSubject = new Day11(new World(INITIAL))

        when:
        testSubject.nextTick()

        then:
        testSubject.world.map == TICK_1

        when:
        testSubject.nextTick()

        then:
        testSubject.world.map == TICK_2

        when:
        testSubject.nextTick()
        testSubject.nextTick()
        testSubject.nextTick()

        then:
        testSubject.world.map == TICK_END

    }
    def "stable seats"() {
        given:
        def testSubject = new Day11(new World(TICK_END))

        when:
        testSubject.nextTick()

        then:
        testSubject.world.map == TICK_END
    }

    def "part 1"() {
        given:
        def testSubject = new Day11(new World(INITIAL))

        expect:
        testSubject.part1() == 37
    }

    def "count line sight"() {
        given:
        def input = """
        .......#.
        ...#.....
        .#.......
        .........
        ..#L....#
        ....#....
        .........
        #........
        ...#.....""".stripIndent().trim().lines().collect(Collectors.toList())

        def testSubject = new Day11(new World(input))

        expect:
        testSubject.nextWorld.debug()
        testSubject.countLineNeighbors(4, 3) == 8
    }

    def "count only next seat"() {
        given:
        def input = """
        .............
        .L.L.#.#.#.#.
        .............""".stripIndent().trim().lines().collect(Collectors.toList())

        def testSubject = new Day11(new World(input))

        expect:
        testSubject.nextWorld.debug()
        testSubject.countLineNeighbors(1, 1) == 0
    }

    def "see nothing directly"() {
        given:
        def input = """
        .##.##.
        #.#.#.#
        ##...##
        ...L...
        ##...##
        #.#.#.#
        .##.##.""".stripIndent().trim().lines().collect(Collectors.toList())

        def testSubject = new Day11(new World(input))

        expect:
        testSubject.nextWorld.debug()
        testSubject.countLineNeighbors(3, 3) == 0
    }

    def "part2 ticks"() {
        given:
        def input = """
        #.L#.##.L#
        #L#####.LL
        L.#.#..#..
        ##L#.##.##
        #.##.#L.##
        #.#####.#L
        ..#.#.....
        LLL####LL#
        #.L#####.L
        #.L####.L#""".stripIndent().trim().lines().collect(Collectors.toList())

        def testSubject = new Day11(new World(INITIAL))

        when:
        testSubject.nextTick2()
        testSubject.nextTick2()
        testSubject.nextTick2()

        then:
        testSubject.nextWorld.debug()
        testSubject.world.map == input
    }

    def "part 2"() {
        given:
        def testSubject = new Day11(new World(INITIAL))

        expect:
        testSubject.part2() == 26
        testSubject.world.map == """
            #.L#.L#.L#
            #LLLLLL.LL
            L.L.L..#..
            ##L#.#L.L#
            L.L#.LL.L#
            #.LLLL#.LL
            ..#.L.....
            LLL###LLL#
            #.LLLLL#.L
            #.L#LL#.L#""".stripIndent().trim().lines().collect(Collectors.toList())
    }
}
