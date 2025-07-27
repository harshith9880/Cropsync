package com.example.greenpulse_04

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.greenpulse_04.R
import com.google.android.gms.common.SignInButton


class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val emailSignIn = findViewById<EditText>(R.id.email_sign_in)
        val passwordSignIn = findViewById<EditText>(R.id.password_sign_in)
        val signInButton = findViewById<Button>(R.id.sign_in_button)
        val googleSignInButton = findViewById<SignInButton>(R.id.google_sign_in_button)
        val switchToSignUp = findViewById<TextView>(R.id.switch_to_sign_up)

        // Sign-In Button Click Listener
        signInButton.setOnClickListener {
            val email = emailSignIn.text.toString()
            val password = passwordSignIn.text.toString()

            if (validateCredentials(email, password)) {
                navigateToMainActivity()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Google Sign-In Button Click Listener (Placeholder)
        googleSignInButton.setOnClickListener {
            signInWithGoogle()
        }

        // Switch to Sign-Up Page
        switchToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        // Add actual authentication logic here (e.g., Firebase Authentication)
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Clears backstack
        startActivity(intent)
    }

    private fun signInWithGoogle() {
        // Implement Google Sign-In Logic
    }
}
