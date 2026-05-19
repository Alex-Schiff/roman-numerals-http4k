package dev.alexschiff

import dev.alexschiff.filters.customErrorHandler
import dev.alexschiff.routes.SWAGGER_UI_PATH
import dev.alexschiff.routes.naturalNumberContractRoute
import dev.alexschiff.routes.romanNumeralContractRoute
import dev.alexschiff.routes.swaggerUiRoute
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
import org.http4k.routing.bind
import org.http4k.routing.routes
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

private const val SERVER_PORT = 9000

fun main() {
  val app: HttpHandler = PrintRequest().then(romanNumeralsApp)

  val server = app.asServer(SunHttp(SERVER_PORT)).start()

  println("Server started on ${server.port()}")
}
