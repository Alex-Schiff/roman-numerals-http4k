package dev.alexschiff.filters

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_IMPLEMENTED
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.kotest.shouldHaveBody
import org.http4k.kotest.shouldHaveStatus
import org.junit.jupiter.api.Test

class NotImplementedErrorFilterTest {

  @Test
  fun `catches NotImplementedError and returns 501`() {
    val app = customErrorHandler.then { _: Request -> TODO("Not implemented yet") }

    val response = app(Request(GET, "/"))

    response shouldHaveStatus NOT_IMPLEMENTED
    response shouldHaveBody "An operation is not implemented: Not implemented yet"
  }

  @Test
  fun `passes through successful response`() {
    val app = customErrorHandler.then { _: Request -> Response(OK).body("success") }

    val response = app(Request(GET, "/"))

    response shouldHaveStatus OK
    response shouldHaveBody "success"
  }
}
