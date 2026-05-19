package dev.alexschiff.routes

import dev.alexschiff.romanNumeralsApp
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.kotest.shouldHaveStatus
import org.junit.jupiter.api.Test

class SwaggerUiRouteTest {
  @Test
  fun `swagger-ui route returns 200`() {
    val response = romanNumeralsApp(Request(GET, "$SWAGGER_UI_PATH/"))

    response shouldHaveStatus OK
  }
}
