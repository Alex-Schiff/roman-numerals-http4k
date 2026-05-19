package dev.alexschiff

import dev.alexschiff.routes.NATURAL_NUMBER_PATH
import dev.alexschiff.routes.ROMAN_NUMERAL_PATH
import org.http4k.client.OkHttp
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.kotest.shouldHaveBody
import org.http4k.kotest.shouldHaveStatus
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EndToEndTest {
  private val client = OkHttp()
  private val server = romanNumeralsServer(0)

  @BeforeEach
  fun setUp() {
    server.start()
  }

  @AfterEach
  fun tearDown() {
    server.stop()
  }

  @Test
  fun `convert natural number to roman numeral`() {
    val response =
      client(Request(GET, "http://localhost:${server.port()}$BASE_PATH$NATURAL_NUMBER_PATH/10"))

    response shouldHaveStatus OK
    response shouldHaveBody "X"
  }

  @Test
  fun `convert roman numeral to natural number`() {
    val response =
      client(Request(GET, "http://localhost:${server.port()}$BASE_PATH$ROMAN_NUMERAL_PATH/X"))

    response shouldHaveStatus OK
    response shouldHaveBody "10"
  }
}
