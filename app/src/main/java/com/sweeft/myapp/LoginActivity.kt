package com.sweeft.myapp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sweeft.myapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val actionBar = supportActionBar

            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back)
                title = ""
                supportActionBar?.elevation = 0F
                supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.menu_color)))
                actionBar.setDisplayHomeAsUpEnabled(true)
            }

            binding.etLoginPhone.addTextChangedListener(watcher)
            binding.etPassword.addTextChangedListener(watcher)

            binding.btnLogin.setOnClickListener {

                binding.layoutWarning.visibility=View.VISIBLE
            }
            binding.textView2.setOnClickListener {
                Toast.makeText(this@LoginActivity, "Forgot Password Clicked!", Toast.LENGTH_SHORT).show()
            }
            binding.etLoginPhone.filters = arrayOf(InputFilter.LengthFilter(15))

        }

        private val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
               updateButtonColor()
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.etLoginPhone.text?.isEmpty()!! || binding.etPassword.text?.isEmpty()!!) {
                    binding.layoutWarning.visibility = View.GONE
                }
                val password = binding.etPassword.text.toString()
                val phoneNumber = binding.etLoginPhone.text.toString()

                if (binding.etPassword.text!!.isNotEmpty()) {
                    val passContainsNumber = password.contains("\\d+".toRegex())
                    val passContainsUppercase = password.any { it.isUpperCase() }

                    if (!passContainsNumber || !passContainsUppercase || password.length < 9) {
                        binding.passwordLayout.error = "Invalid password"
                    }
                    else{ binding.passwordLayout.error = null
                    }
                }

                if(phoneNumber.length<9){
                    binding.layoutLoginPhone.error = "Invalid phone number"
                }
                else{ binding.layoutLoginPhone.error = null
                }
            }
        }

    private fun updateButtonColor() {
        val isLoginPhoneNotEmpty = binding.etLoginPhone.text?.isNotEmpty() == true
        val isPasswordNotEmpty = binding.etPassword.text?.isNotEmpty() == true

        binding.btnLogin.isEnabled = isLoginPhoneNotEmpty && isPasswordNotEmpty

        if (isLoginPhoneNotEmpty && isPasswordNotEmpty) {
            binding.btnLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.orange1))
        } else {
            binding.btnLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.orange3))
        }
    }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                android.R.id.home -> {
                    val intent = Intent(applicationContext, OnboardingActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.action_sign_up -> {
                    Toast.makeText(this@LoginActivity, "Sign up clicked!", Toast.LENGTH_SHORT).show()
                    /*
                    val intent = Intent(applicationContext, SignUpActivity::class.java)
                    startActivity(intent)
                     */
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_login, menu)

            val yourMenuItem = menu.findItem(R.id.action_sign_up)
            yourMenuItem.title = "Sign up"

            return true
        }

}
