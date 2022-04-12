package com.example.loginflow

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.loginflow.databinding.ActivityLoginFlow2Binding

class LoginFlow2Activity : AppCompatActivity() {
    lateinit var binding: ActivityLoginFlow2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFlow2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
    }

    override fun onBackPressed() {
        if(binding.rootMotionLayout.currentState==R.id.login){
            binding.rootMotionLayout.transitionToState(R.id.end)
        }
    }
    private fun setClickListeners() {
        binding.btnLogin.setOnClickListener {
            binding.rootMotionLayout.transitionToState(R.id.login)
            binding.tlUserNumber.requestFocus()
            binding.etUserNumber.requestFocus()
            showKeyboard(binding.tlUserNumber)
            showKeyboard(binding.etUserNumber)

        }
    }

    fun showKeyboard(view: View) {
        val inputMethodManager = this.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.showSoftInput(view, 0)
    }
}