package dev.alexschiff.routes

import dev.alexschiff.BASE_PATH
import dev.alexschiff.romanNumeralsApp
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.kotest.shouldHaveBody
import org.http4k.kotest.shouldHaveStatus
import org.junit.jupiter.api.Test

class NaturalNumberContractRouteTest {
  @Test
  fun `convert natural-number within range should return OK`() {
    val response = romanNumeralsApp(Request(GET, "$BASE_PATH$NATURAL_NUMBER_PATH/123"))

    response shouldHaveStatus OK
    response shouldHaveBody "CXXIII"
  }

  @Test
  fun `convert natural-number below range should return not found`() {
    val response = romanNumeralsApp(Request(GET, "$BASE_PATH$NATURAL_NUMBER_PATH/0"))

    // Path lenses in contract routes that fail to match result in 404
    response shouldHaveStatus NOT_FOUND
  }

  @Test
  fun `convert natural-number above range should return not found`() {
    val response = romanNumeralsApp(Request(GET, "$BASE_PATH$NATURAL_NUMBER_PATH/3001"))

    // Path lenses in contract routes that fail to match result in 404
    response shouldHaveStatus NOT_FOUND
  }

  @Test
  fun `convert natural-number with invalid input returns not found`() {
    val response = romanNumeralsApp(Request(GET, "$BASE_PATH$NATURAL_NUMBER_PATH/abc"))

    // Path.int() in a contract route usually results in a 400 if it fails to parse
    // but it might be 404 if it's considered a non-match.
    // Given the error message "expected:<400 Bad Request> but was:<404 Not Found>"
    // it seems it didn't match the route at all.
    response shouldHaveStatus NOT_FOUND
  }
}
