package com.example.greenpulse_04

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.greenpulse_04.R

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val nameSignUp = findViewById<EditText>(R.id.name_sign_up)
        val emailSignUp = findViewById<EditText>(R.id.email_sign_up)
        val passwordSignUp = findViewById<EditText>(R.id.password_sign_up)
        val confirmPasswordSignUp = findViewById<EditText>(R.id.confirm_password_sign_up)
        val signUpButton = findViewById<Button>(R.id.sign_up_button)
        val switchToSignIn = findViewById<TextView>(R.id.switch_to_sign_in)

        // Handle Sign-Up button click
        signUpButton.setOnClickListener {
            val name = nameSignUp.text.toString()
            val email = emailSignUp.text.toString()
            val password = passwordSignUp.text.toString()
            val confirmPassword = confirmPasswordSignUp.text.toString()

            // Sign-up logic here, like validating inputs, creating account, etc.
        }

        // Switch to Sign-In activity
        switchToSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}