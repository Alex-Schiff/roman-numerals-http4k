package dev.alexschiff

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.kotest.shouldHaveBody
import org.http4k.kotest.shouldHaveStatus
import org.junit.jupiter.api.Test

class RomanNumeralsTest {
  @Test
  fun `ping should return pong`() {
    val response = app(Request(GET, "/ping"))
    response shouldHaveStatus OK
    response shouldHaveBody "pong"
  }
}
