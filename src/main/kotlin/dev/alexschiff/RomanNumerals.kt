package dev.alexschiff

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.serverless.AppLoader
import org.http4k.serverless.GoogleCloudHttpFunction

object RomanNumeralsAppLoader : AppLoader {
  @Suppress("MagicNumber")
  override fun invoke(env: Map<String, String>): HttpHandler {
    return PrintRequest().then(routes("/ping" bind GET to { Response(OK).body("pong") }))
  }
}

@Suppress("Unused") class RomanNumeralsAppFunction : GoogleCloudHttpFunction(RomanNumeralsAppLoader)
