package com.github.dangerground.aoc2020


import spock.lang.Specification
import spock.lang.Unroll

class Day25Test extends Specification {

    static final CARD_PUBKEY = 5764801
    static final DOOR_PUBKEY = 17807724
    static final EXAMPLE = [CARD_PUBKEY, DOOR_PUBKEY]

    @Unroll
    def "part 1 loop size"() {
        given:
        def testSubject = new Day25(EXAMPLE)

        expect:
        testSubject.loopSize(key) == size

        where:
        key         | size
        CARD_PUBKEY | 8
        DOOR_PUBKEY | 11
    }

    @Unroll
    def "part 1 transform"() {
        given:
        def testSubject = new Day25(EXAMPLE)

        expect:
        testSubject.transform(publicKey, size) == size

        where:
        publicKey   | size | privateKey
        CARD_PUBKEY | 11   | 14897079
        DOOR_PUBKEY | 8    | 14897079
    }

    def "part 1"() {
        given:
        def testSubject = new Day25(EXAMPLE)

        expect:
        testSubject.part1() == 14897079
    }

    def "part 2"() {
        given:
        def testSubject = new Day25(EXAMPLE)

        expect:
        testSubject.part2() == 291
    }
}
