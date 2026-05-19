package dev.alexschiff

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.kotest.shouldHaveStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RomanNumeralsTest {
  @Test
  fun `swagger-ui redirects to trailing slash`() {
    val response = romanNumeralsApp(Request(GET, "/swagger-ui"))

    response shouldHaveStatus FOUND
    assertEquals("/swagger-ui/", response.header("Location"))
  }

  @Test
  fun `openapi spec is available`() {
    val response = romanNumeralsApp(Request(GET, "/openapi.json"))

    response shouldHaveStatus OK
  }
}
