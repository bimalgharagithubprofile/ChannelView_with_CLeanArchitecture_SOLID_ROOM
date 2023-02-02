package com.bimalghara.channelviewcleanarchitecturesolid.utils

import java.io.InputStreamReader

/**
 * Created by BimalGhara
 */


object Helper {

    fun readFileFromResource(fileName: String): String {
        val inputStream = Helper::class.java.getResourceAsStream(fileName)
        val stringBuilder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            stringBuilder.append(it)
        }
        return stringBuilder.toString()
    }

}