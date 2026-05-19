package dev.alexschiff.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ConvertRomanNumeralTest {
  @ParameterizedTest
  @CsvSource(
    "I, 1",
    "II, 2",
    "III, 3",
    "IV, 4",
    "V, 5",
    "VI, 6",
    "IX, 9",
    "X, 10",
    "XL, 40",
    "L, 50",
    "XC, 90",
    "C, 100",
    "CD, 400",
    "D, 500",
    "CM, 900",
    "M, 1000",
    "XIV, 14",
    "MCMXCIV, 1994",
    "MMM, 3000",
    "XXXIX, 39",
    "CDXLIV, 444",
    "DCCCLXXXVIII, 888",
    "MMXXIV, 2024",
    "MMCMXCIX, 2999",
  )
  fun `roman numeral to natural number`(romanNumeral: String, expectedInteger: Int) {
    romanNumeral.convertRomanNumeral() shouldBe expectedInteger
  }
}
