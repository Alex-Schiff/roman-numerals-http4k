package dev.alexschiff

import dev.alexschiff.filters.customErrorHandler
import dev.alexschiff.routes.SWAGGER_UI_PATH
import dev.alexschiff.routes.naturalNumberContractRoute
import dev.alexschiff.routes.romanNumeralContractRoute
import dev.alexschiff.routes.swaggerUiRoute
import org.http4k.config.Environment
import org.http4k.config.EnvironmentKey
import org.http4k.contract.contract
import org.http4k.contract.openapi.ApiInfo
import org.http4k.contract.openapi.v3.ApiServer
import org.http4k.contract.openapi.v3.OpenApi3
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.FOUND
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.format.Jackson
import org.http4k.lens.int
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Http4kServer
import org.http4k.server.SunHttp
import org.http4k.server.asServer

const val BASE_PATH = "/convert"

val contract = contract {
  renderer =
    OpenApi3(
      ApiInfo("roman-numerals-http4k", "v1.0", "Roman Numerals Conversion API"),
      Jackson,
      servers = listOf(ApiServer(Uri.of("/"), "Default Server")),
    )
  descriptionPath = "/openapi.json"

  routes += naturalNumberContractRoute(BASE_PATH)
  routes += romanNumeralContractRoute(BASE_PATH)
}

val romanNumeralsApp: HttpHandler =
  customErrorHandler.then(
    routes(
      contract,
      "$SWAGGER_UI_PATH/" bind swaggerUiRoute(),
      SWAGGER_UI_PATH bind GET to { Response(FOUND).header("Location", "$SWAGGER_UI_PATH/") },
    )
  )

fun romanNumeralsServer(port: Int): Http4kServer =
  PrintRequest().then(romanNumeralsApp).asServer(SunHttp(port))

fun main() {
  val defaultConfig = Environment.defaults(
    port of DEFAULT_SERVER_PORT
  )
  val env = Environment.fromResource("app.properties") overrides defaultConfig
  val server = romanNumeralsServer(port(env))
  server.start()

  println("Server started on ${server.port()}")
}

private val port = EnvironmentKey.int().required("port")
private const val DEFAULT_SERVER_PORT = 8000
