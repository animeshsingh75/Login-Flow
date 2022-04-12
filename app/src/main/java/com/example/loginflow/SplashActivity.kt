package com.example.loginflow

import android.animation.Animator
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.airbnb.lottie.LottieDrawable
import com.example.loginflow.databinding.ActivityMainBinding
import com.example.loginflow.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var repeat=0
        val sharedPreferences: SharedPreferences? =
            this.getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val launch = sharedPreferences?.getInt("launch", 1)
        val myEdit = sharedPreferences?.edit()
        myEdit?.putInt("launch", (launch?: 0) + 1)
        myEdit?.apply()
        binding.animationView.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                binding.animationView.setAnimation(R.raw.splash_loading)
                binding.animationView.playAnimation()
                binding.animationView.repeatCount= 1
                binding.animationView.repeatMode= LottieDrawable.RESTART
                Log.d("SplashActivity", "onAnimationEnd: $repeat")
                if(repeat==1){
                    if(launch==1){
                        val intent= Intent(this@SplashActivity,MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        myEdit?.putInt("launch", 1)
                        myEdit?.apply()
                        val intent= Intent(this@SplashActivity,LoginFlow2Activity::class.java)
                        startActivity(intent)
                    }
                }
                repeat++
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }
        })
        hideSystemBars()
    }

    override fun onResume() {
        binding.animationView.resumeAnimation()
        super.onResume()
    }

    override fun onPause() {
        binding.animationView.pauseAnimation()
        super.onPause()
    }

    private fun hideSystemBars() {
        supportActionBar?.hide()
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}