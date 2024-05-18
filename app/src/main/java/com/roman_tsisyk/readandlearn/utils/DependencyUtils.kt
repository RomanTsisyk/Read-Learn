package com.roman_tsisyk.readandlearn.utils

import android.content.Context

fun extractDependencies(context: Context): List<String> {
    val dependencies = mutableListOf<String>()
    try {
        val inputStream = context.assets.open("dependencies.txt")
        val reader = inputStream.bufferedReader()
        reader.useLines { lines ->
            lines.forEach { dependencies.add(it) }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return dependencies
}
