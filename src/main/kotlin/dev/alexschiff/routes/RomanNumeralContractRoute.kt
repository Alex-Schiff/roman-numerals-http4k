package dev.alexschiff.routes

import dev.alexschiff.domain.convertRomanNumeral
import dev.alexschiff.enums.NUMERAL_SYMBOLS_REGEX
import org.http4k.contract.ContractRoute
import org.http4k.contract.Tag
import org.http4k.contract.div
import org.http4k.contract.meta
import org.http4k.core.ContentType.Companion.TEXT_PLAIN
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.Path
import org.http4k.lens.string

const val ROMAN_NUMERAL_PATH = "/roman-numeral"

fun romanNumeralContractRoute(basePath: String): ContractRoute =
  "$basePath$ROMAN_NUMERAL_PATH" /
    Path.string()
      .map(
        {
          it.uppercase().also { romanNumeral ->
            require(NUMERAL_SYMBOLS_REGEX.toRegex().matches(romanNumeral)) {
              "$romanNumeral is not a valid Roman numeral"
            }
          }
        },
        { it },
      )
      .of("romanNumeral", "The Roman numeral to convert") meta
    {
      summary = "Convert Roman numeral to natural number."
      description = "Convert Roman numeral to natural number."
      tags += Tag("Conversion", "Endpoints for numeral conversion")
      consumes += TEXT_PLAIN
      produces += TEXT_PLAIN
    } bindContract
    GET to
    { romanNumeral: String ->
      { _: Request -> Response(OK).body(romanNumeral.convertRomanNumeral().toString()) }
    }
