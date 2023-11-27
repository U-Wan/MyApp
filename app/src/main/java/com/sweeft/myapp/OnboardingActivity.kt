package com.sweeft.myapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sweeft.myapp.databinding.ActivitySplashScreenSecondBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenSecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnLogin.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener {
            Toast.makeText(this@OnboardingActivity, "Register clicked!", Toast.LENGTH_SHORT).show()

           // register()
        }

        termsAndConditionsStyling()

    }

    private fun register() {
        TODO("Not yet implemented")
    }



    private fun termsAndConditionsStyling() {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@OnboardingActivity, "Clicked!", Toast.LENGTH_SHORT).show()
            }
        }

        val spannableString = SpannableString("By selecting one of the options above, you accept the terms and conditions")

        val termsAndConditionsText = "terms and conditions"
        val startIndex = spannableString.indexOf(termsAndConditionsText)
        val endIndex = startIndex + termsAndConditionsText.length
        val color = ContextCompat.getColor(this,R.color.orange1)

     spannableString.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
       spannableString.setSpan(clickableSpan, startIndex, endIndex, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(color), startIndex, endIndex, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvTermsAndConditions.movementMethod = LinkMovementMethod.getInstance()
        binding.tvTermsAndConditions.text = spannableString


    }

}
