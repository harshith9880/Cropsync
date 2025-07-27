package com.example.greenpulse_04

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        adapter = TaskAdapter()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.tasks.observe(this, Observer { tasks ->
            adapter.submitList(tasks)
        })

        /*findViewById<Button>(R.id.btnSyncCalendar).setOnClickListener {
            viewModel.syncWithCalendar()
        }*/

        /*findViewById<Button>(R.id.btnGoToAiPrediction).setOnClickListener {
            startActivity(Intent(this, AiPredictionActivity::class.java))
        }*/

        val aiPredictionButton = findViewById<Button>(R.id.btnGoToAiPrediction)
        aiPredictionButton.setOnClickListener {
            // Intent to start AiPredictionActivity
            val intent = Intent(this, AiPredictionActivity::class.java)
            startActivity(intent)
        }

        val weatherButton = findViewById<Button>(R.id.btnGoToWeather)
        weatherButton.setOnClickListener {
            // Intent to start WeatherActivity
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnSyncCalendar).setOnClickListener {
            val account = GoogleSignIn.getLastSignedInAccount(this)
            if (account != null) {
                viewModel.syncWithCalendar(account)
            } else {
                Toast.makeText(this, "Please sign in first", Toast.LENGTH_SHORT).show()
            }


        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GoogleSignInHelper.REQUEST_CODE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            task.getResult(ApiException::class.java)?.let { account ->
                addEventToCalendar(account)
            }
        }
    }

    private fun addEventToCalendar(account: GoogleSignInAccount) {
        // TODO: Implement event creation logic here
        Log.d("GoogleSignIn", "User signed in: ${account.email}")

        // Example: Pass the account's access token to Google Calendar API
        val token = account.idToken
        if (token != null) {
            Log.d("GoogleSignIn", "Access Token: $token")
        }
    }


    @Composable
    fun MainNavHost() {
        val navController = rememberNavController()

        // The NavHost defines the navigation logic for the app
        NavHost(navController, startDestination = "signup") {
            composable("signup") {
                SignUpScreen {
                    navController.navigate("signin")
                }
            }
            composable("signin") {
                SignInScreen(onSignInSuccess = { token ->
                    // Save the token securely
                    syncWithGoogleCalendar(token)
                })
            }
        }
    }



}
