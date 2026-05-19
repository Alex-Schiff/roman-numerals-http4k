package dev.alexschiff.routes

import org.http4k.contract.ui.swaggerUiLite
import org.http4k.routing.RoutingHttpHandler

fun swaggerUiRoute(): RoutingHttpHandler = swaggerUiLite {
  url = "/openapi.json"
  pageTitle = "roman-numerals-http4k - Swagger UI"
}
