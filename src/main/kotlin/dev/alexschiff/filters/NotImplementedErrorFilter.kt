package dev.alexschiff.filters

import org.http4k.core.Filter
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_IMPLEMENTED

val customErrorHandler = Filter { next ->
  { request ->
    try {
      next(request)
    } catch (ex: NotImplementedError) {
      Response(NOT_IMPLEMENTED).body(ex.message ?: "Not yet implemented.")
    }
  }
}
