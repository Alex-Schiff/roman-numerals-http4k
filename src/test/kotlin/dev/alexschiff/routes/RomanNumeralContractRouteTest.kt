package dev.alexschiff.routes

import dev.alexschiff.BASE_PATH
import dev.alexschiff.enums.NumeralSymbols.IV
import dev.alexschiff.romanNumeralsApp
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.NOT_IMPLEMENTED
import org.http4k.kotest.shouldHaveBody
import org.http4k.kotest.shouldHaveStatus
import org.junit.jupiter.api.Test

class RomanNumeralContractRouteTest {
  @Test
  fun `convert roman-numeral should return not yet implemented`() {
    val response = romanNumeralsApp(Request(GET, "$BASE_PATH$ROMAN_NUMERAL_PATH/${IV.name}"))

    response shouldHaveStatus NOT_IMPLEMENTED
    response shouldHaveBody "An operation is not implemented: Work in progress!"
  }
}
