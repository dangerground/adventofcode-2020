package com.github.dangerground.aoc2020

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.stream.Collectors

class Day4Test extends Specification {

    static final EXAMPLE_INPUT = """
        ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
        byr:1937 iyr:2017 cid:147 hgt:183cm
        
        iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
        hcl:#cfa07d byr:1929
        
        hcl:#ae17e1 iyr:2013
        eyr:2024
        ecl:brn pid:760753108 byr:1931
        hgt:179cm
        
        hcl:#cfa07d eyr:2025 pid:166559648
        iyr:2011 ecl:brn hgt:59in
        """.stripIndent().trim().lines().collect(Collectors.toList())

    @Subject
    Day4 testSubject = new Day4(EXAMPLE_INPUT)

    @Unroll
    def "test is data stored as expected"() {
        expect:
        with(testSubject.getPassports()) {
            size() == 4
            get(0).size() == 8
            get(1).size() == 7
            get(2).size() == 7
            get(3).size() == 6
        }
    }

    @Unroll
    def "test for valid passport #number == #isValid"() {
        when:
        def result = testSubject.getPassports().get(number).hasRequiredFields()

        then:
        result == isValid

        where:
        number | isValid
        0      | true
        1      | false
        2      | true
        3      | false
    }

    def "count valid passports"() {
        expect:
        testSubject.countPassportsWithRequiredField() == 2
    }

    @Unroll
    def "has valid birthyear #input = #isValid"() {
        given:
        def passport = new Passport([input])

        expect:
        passport.isValidBirthYear() == isValid

        where:
        input       | isValid
        "byr:19373" | false
        "byr:abcd"  | false
        "byr:1937"  | true
        "byr:1919"  | false
        "byr:2003"  | false
    }

    @Unroll
    def "has valid issue year #input = #isValid"() {
        given:
        def passport = new Passport([input])

        expect:
        passport.isValidIssueYear() == isValid

        where:
        input       | isValid
        "iyr:19373" | false
        "iyr:abcd"  | false
        "iyr:2017"  | true
        "iyr:2009"  | false
        "iyr:2021"  | false
    }

    @Unroll
    def "has valid expiration year #input = #isValid"() {
        given:
        def passport = new Passport([input])

        expect:
        passport.isValidExpirationYear() == isValid

        where:
        input       | isValid
        "eyr:19373" | false
        "eyr:abcd"  | false
        "eyr:2024"  | true
        "eyr:2019"  | false
        "eyr:2031"  | false
    }

    @Unroll
    def "has valid height #input = #isValid"() {
        given:
        def passport = new Passport([input])

        expect:
        passport.isValidHeight() == isValid

        where:
        input       | isValid
        "hgt:45"    | false
        "hgt:59"    | false
        "hgt:59in"  | true
        "hgt:58in"  | false
        "hgt:77in"  | false
        "hgt:150cm" | true
        "hgt:149cm" | false
        "hgt:194cm" | false
        "hgt:19045" | false
    }

    @Unroll
    def "has valid hair color #input = #isValid"() {
        given:
        def passport = new Passport([input])

        expect:
        passport.isValidHairColor() == isValid

        where:
        input         | isValid
        "hcl:none"    | false
        "hcl:123456"  | false
        "hcl:#123456" | true
        "hcl:#123fcd" | true
        "hcl:#123ggg" | false
    }

    @Unroll
    def "has valid eye color #input = #isValid"() {
        given:
        def passport = new Passport([input])

        expect:
        passport.isValidEyeColor() == isValid

        where:
        input      | isValid
        "ecl:none" | false
        "ecl:amb"  | true
        "ecl:blu"  | true
        "ecl:brn"  | true
        "ecl:gry"  | true
        "ecl:grn"  | true
        "ecl:hzl"  | true
        "ecl:oth"  | true
        "ecl:unk"  | false
    }

    @Unroll
    def "has valid passport number #input = #isValid"() {
        given:
        def passport = new Passport([input])

        expect:
        passport.isValidPassportNumber() == isValid

        where:
        input            | isValid
        "pid:000000001"  | true
        "pid:00000000a"  | false
        "pid:1234567890" | false
    }

    def "count valid passports with validated fields"() {
        expect:
        testSubject.countValidPassports() == 2
    }

}
