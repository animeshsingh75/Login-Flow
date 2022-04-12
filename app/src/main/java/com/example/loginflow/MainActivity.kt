package com.example.loginflow

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setOnClickListener {
            binding.animationView.cancelAnimation()
            val intent = Intent(this, PhoneActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
    override fun onResume() {
        binding.animationView.resumeAnimation()
        super.onResume()
    }

    override fun onPause() {
        binding.animationView.pauseAnimation()
        super.onPause()
    }
}