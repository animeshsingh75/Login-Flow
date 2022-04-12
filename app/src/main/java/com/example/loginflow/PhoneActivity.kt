package com.example.loginflow

import android.R.attr.phoneNumber
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.loginflow.databinding.ActivityPhoneBinding

class PhoneActivity : AppCompatActivity() {
    lateinit var binding: ActivityPhoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backArrowImage.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.otpButton.setOnClickListener {
            val intent = Intent(this, OtpActivity::class.java)
            intent.putExtra("phone_number",binding.phoneNumber.text.toString())
            startActivity(intent)
        }
    }
}