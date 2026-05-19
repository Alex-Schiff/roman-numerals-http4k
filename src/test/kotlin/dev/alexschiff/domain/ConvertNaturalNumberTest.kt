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
  )
  fun `natural number to Roman numeral`(integer: Int, expectedRomanNumeral: String) {
    integer.convertNaturalNumber() shouldBe expectedRomanNumeral
  }
}
