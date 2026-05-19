package dev.alexschiff.routes

import dev.alexschiff.ROOT_URI
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
    val response = romanNumeralsApp(Request(GET, "$ROOT_URI$ROMAN_NUMERAL_URI/IV"))

    response shouldHaveStatus NOT_IMPLEMENTED
    response shouldHaveBody "An operation is not implemented: Work in progress!"
  }
}
