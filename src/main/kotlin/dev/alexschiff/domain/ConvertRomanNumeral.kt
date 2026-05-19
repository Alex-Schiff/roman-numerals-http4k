package dev.alexschiff.domain

import dev.alexschiff.enums.NumeralSymbols

fun String.convertRomanNumeral(): Int {
  var number = 0
  var i = 0
  while (i <= this.length - 1) {
    when {
      i == this.length - 1 -> {
        number += NumeralSymbols.valueOf(this.substring(i)).intValue
        i++
      }
      else -> {
        try {
          number += NumeralSymbols.valueOf(this.substring(i, i + 2)).intValue
          i += 2
        } catch (_: Exception) {
          number += NumeralSymbols.valueOf(this.substring(i, i + 1)).intValue
          i++
        }
      }
    }
  }
  return number
}
