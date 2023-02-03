package com.bimalghara.sharedtest.sharedUtils

import java.io.InputStreamReader

/**
 * Created by BimalGhara
 */


object TestHelper {

    fun readFileFromResource(fileName: String): String {
        val inputStream = TestHelper::class.java.getResourceAsStream(fileName)
        val stringBuilder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            stringBuilder.append(it)
        }
        return stringBuilder.toString()
    }

}