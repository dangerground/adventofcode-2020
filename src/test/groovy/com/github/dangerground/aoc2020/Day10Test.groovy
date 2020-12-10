package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Unroll

class Day10Test extends Specification {

    @Unroll
    def "part 1"() {
        given:
        def testSubject = new Day10(input)

        expect:
        testSubject.part1() == result

        where:
        result | input
        35     | [16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4]
        220    | [28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3]
    }

    @Unroll
    def "part 2 #result = #input"() {
        given:
        def testSubject = new Day10(input)

        expect:
        testSubject.part2() == result
        testSubject.part2NonRecursive() == result

        where:
        result | input
        //1      | [3, 5, 8, 11]
        //2      | [1, 3, 5, 8, 11]
        //3      | [2, 3, 5, 8, 11]
        //3      | [1, 3, 5, 6, 9]
//        4      | [1, 2, 3] // 123, 13, 13, 3
//        7      | [1, 2, 3, 4] // 1234, 124, 134, 14; 234, 24; 34
//        2      | [1, 4, 5, 6] // 1456, 146
        //8      | [16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4]
        8      | [1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19]
//        19208  | [28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3]

    }

    /*
    5! = 20 /
    5, 6, 7, 10, 11 = 3
    5, 6, 7, 10 = 2
    5, 7, 10, 11 = 3
    5, 7, 10 = 1

       6, 7, 10, 11
       6, 7, 10

       7, 10, 11
       7, 10
    */
}
