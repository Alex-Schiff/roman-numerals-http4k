package dev.alexschiff.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ConvertNaturalNumberTest {
  @ParameterizedTest
  @CsvSource(
    "1, I",
    "2, II",
    "3, III",
    "4, IV",
    "5, V",
    "6, VI",
    "9, IX",
    "10, X",
    "40, XL",
    "50, L",
    "90, XC",
    "100, C",
    "400, CD",
    "500, D",
    "900, CM",
    "1000, M",
    "3000, MMM",
    "39, XXXIX",
    "444, CDXLIV",
    "888, DCCCLXXXVIII",
    "2024, MMXXIV",
    "2999, MMCMXCIX",
  )
  fun `natural number to Roman numeral`(integer: Int, expectedRomanNumeral: String) {
    integer.convertNaturalNumber() shouldBe expectedRomanNumeral
  }

  @ParameterizedTest
  @CsvSource("0", "3001", "-1")
  fun `returns empty string for numbers out of range`(integer: Int) {
    integer.convertNaturalNumber() shouldBe ""
  }
}
