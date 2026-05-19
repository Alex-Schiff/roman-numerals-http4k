package dev.alexschiff.routes

import org.http4k.contract.ui.swaggerUiLite
import org.http4k.routing.RoutingHttpHandler

const val SWAGGER_UI_PATH = "/swagger-ui"

fun swaggerUiRoute(): RoutingHttpHandler = swaggerUiLite {
  url = "/openapi.json"
  pageTitle = "roman-numerals-http4k - Swagger UI"
}
