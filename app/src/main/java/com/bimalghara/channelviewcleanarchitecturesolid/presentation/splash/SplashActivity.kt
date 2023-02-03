package com.bimalghara.channelviewcleanarchitecturesolid.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.bimalghara.channelviewcleanarchitecturesolid.R
import com.bimalghara.channelviewcleanarchitecturesolid.databinding.ActivitySplashBinding
import com.bimalghara.channelviewcleanarchitecturesolid.presentation.MainActivity
import com.bimalghara.channelviewcleanarchitecturesolid.presentation.base.BaseActivity
import com.bimalghara.channelviewcleanarchitecturesolid.utils.SPLASH_DELAY
import com.bimalghara.channelviewcleanarchitecturesolid.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by BimalGhara
 */

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    private val splashViewModel: SplashViewModel by viewModels()

    override fun initViewBinding() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observe(splashViewModel.isLoadingLiveData) { isLoading ->
            /*if(!isLoading)
                navigateToMainScreen()*/

            //no need to wait, as data loads cluster manner
            navigateToMainScreen()
        }
    }


    private fun navigateToMainScreen() {
        CoroutineScope(Dispatchers.Main).launch {
            //delay(SPLASH_DELAY.toLong())//no need to wait, as data loads cluster manner

            val nextScreenIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(nextScreenIntent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }
}