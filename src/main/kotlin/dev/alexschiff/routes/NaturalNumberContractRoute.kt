package dev.alexschiff.routes

import dev.alexschiff.domain.convertNaturalNumber
import org.http4k.contract.ContractRoute
import org.http4k.contract.Tag
import org.http4k.contract.div
import org.http4k.contract.meta
import org.http4k.core.ContentType.Companion.TEXT_PLAIN
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_IMPLEMENTED
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.Path
import org.http4k.lens.int

const val NATURAL_NUMBER_PATH = "/natural-number"

fun naturalNumberContractRoute(basePath: String): ContractRoute =
  "$basePath$NATURAL_NUMBER_PATH" /
    Path.int().of("naturalNumber", "The natural number to convert") meta
    {
      summary = "Convert natural number to Roman numeral."
      description = "Convert natural number to Roman numeral."
      tags += Tag("Conversion", "Endpoints for numeral conversion")
      consumes += TEXT_PLAIN
      produces += TEXT_PLAIN
      returning(NOT_IMPLEMENTED to "Not implemented yet")
    } bindContract
    GET to
    { naturalNumber: Int ->
      { _: Request -> Response(OK).body(naturalNumber.convertNaturalNumber()) }
    }
