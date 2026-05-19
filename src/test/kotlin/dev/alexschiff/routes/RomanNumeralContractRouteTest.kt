package dev.alexschiff.routes

import dev.alexschiff.BASE_PATH
import dev.alexschiff.enums.NUMERAL_SYMBOLS_REGEX
import dev.alexschiff.enums.NumeralSymbols.IV
import dev.alexschiff.romanNumeralsApp
import io.kotest.matchers.shouldBe
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.kotest.shouldHaveBody
import org.http4k.kotest.shouldHaveStatus
import org.http4k.lens.Path
import org.http4k.lens.string
import org.junit.jupiter.api.Test

class RomanNumeralContractRouteTest {
  @Test
  fun `convert roman-numeral should return ok for valid numeral`() {
    val response = romanNumeralsApp(Request(GET, "$BASE_PATH$ROMAN_NUMERAL_PATH/${IV.name}"))

    response shouldHaveStatus OK
    response shouldHaveBody "4"
  }

  @Test
  fun `convert roman-numeral should return not found for invalid numeral`() {
    val response = romanNumeralsApp(Request(GET, "$BASE_PATH$ROMAN_NUMERAL_PATH/INVALID"))

    response shouldHaveStatus NOT_FOUND
  }

  @Test
  fun `roman numeral path lens can be unmapped`() {
    val romanNumeralLens =
      Path.string()
        .map(
          {
            it.uppercase().also { romanNumeral ->
              require(NUMERAL_SYMBOLS_REGEX.toRegex().matches(romanNumeral))
            }
          },
          { it },
        )
        .of("romanNumeral")

    romanNumeralLens(IV.name) shouldBe IV.name
  }
}
