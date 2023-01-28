package com.bimalghara.channelviewcleanarchitecturesolid.presentation

import android.os.Bundle
import com.bimalghara.channelviewcleanarchitecturesolid.databinding.ActivityMainBinding
import com.bimalghara.channelviewcleanarchitecturesolid.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by BimalGhara
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val TAG = javaClass.simpleName

    private lateinit var binding: ActivityMainBinding

    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}