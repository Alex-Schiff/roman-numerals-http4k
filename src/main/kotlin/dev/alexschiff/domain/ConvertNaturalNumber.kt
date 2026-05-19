package dev.alexschiff.domain

import dev.alexschiff.enums.NumeralSymbols

private const val MAX_NATURAL_NUMBER = 3000
val NATURAL_NUMBER_RANGE = 1..MAX_NATURAL_NUMBER

fun Int.convertNaturalNumber(): String =
  when {
    this in NATURAL_NUMBER_RANGE -> {
      var number = this
      val stringBuilder = StringBuilder()
      NumeralSymbols.entries
        .sortedByDescending { it.intValue }
        .forEach {
          while (number >= it.intValue) {
            stringBuilder.append(it.toString())
            number -= it.intValue
          }
        }
      stringBuilder.toString()
    }
    else -> ""
  }
