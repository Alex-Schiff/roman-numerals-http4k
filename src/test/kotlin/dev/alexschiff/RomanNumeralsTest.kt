package dev.alexschiff

import dev.alexschiff.routes.SWAGGER_UI_PATH
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.kotest.shouldHaveHeader
import org.http4k.kotest.shouldHaveStatus
import org.junit.jupiter.api.Test

class RomanNumeralsTest {
  @Test
  fun `swagger-ui redirects to trailing slash`() {
    val response = romanNumeralsApp(Request(GET, SWAGGER_UI_PATH))

    response shouldHaveStatus FOUND
    response.shouldHaveHeader("Location", "$SWAGGER_UI_PATH/")
  }

  @Test
  fun `openapi spec is available`() {
    val response = romanNumeralsApp(Request(GET, "/openapi.json"))

    response shouldHaveStatus OK
  }
}
