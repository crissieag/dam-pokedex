package br.com.up.pokedex.utils

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { w -> w.replaceFirstChar { c -> c.uppercaseChar() } }
