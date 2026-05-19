package dev.alexschiff

import org.http4k.server.SunHttp
import org.http4k.server.asServer

val app = RomanNumeralsAppLoader(emptyMap())

fun main() {
  app.asServer(SunHttp(8000)).start()
}
