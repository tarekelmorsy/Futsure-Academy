    package com.arEgTA.futsureacademy.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.arEgTA.futsureacademy.MainActivity
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.ui.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        if(savedInstanceState==null){
            lifecycleScope.launch {
                delay(1000)
                withContext(Dispatchers.Main){
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finishAffinity()
                }
            }
        }

    }
}