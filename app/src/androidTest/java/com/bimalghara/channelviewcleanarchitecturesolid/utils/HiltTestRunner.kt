package com.bimalghara.channelviewcleanarchitecturesolid.utils

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * custom test runner
 * @HiltAndroidApp in BaseApplication() is not available in test env therefore we need HiltTestApplication in JUnitRunner
 * assign this class to app level gradle -> testInstrumentationRunner ""
 */

/**
 * Created by BimalGhara
 */

class HiltTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}