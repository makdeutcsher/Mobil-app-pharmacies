package Amine.android.com.project

import Amine.android.com.project.Main5Activity
import Amine.android.com.project.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
//import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


import java.util.Objects

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_splash)



        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, Main5Activity::class.java)
            this@SplashActivity.startActivity(intent)
            this@SplashActivity.finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

    companion object {

        private val SPLASH_DISPLAY_LENGTH = 2000
    }
}
