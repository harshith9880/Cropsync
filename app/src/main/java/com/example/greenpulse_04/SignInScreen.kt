package com.example.greenpulse_04

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.Serializable
import com.google.api.services.calendar.CalendarScopes
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.calendar.Calendar

// Define constants for RC_SIGN_IN and the Google Sign-In options (gso)
const val RC_SIGN_IN = 9001

// Initialize gso with your provided Web Client ID
val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestIdToken("486480935897-qnd6r3b9feii9ncmg4b3cb0spr640f2e.apps.googleusercontent.com") // Replace with your Web Client ID
    .requestEmail()
    .build()

class SignInScreen : AppCompatActivity() {

    private lateinit var onSignInSuccess: (String) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignInScreen { idToken ->
                onSignInSuccess(idToken)
            }
        }

        // Retrieve the callback from the intent
        onSignInSuccess = intent.getSerializableExtra("onSignInSuccess") as (String) -> Unit
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!, this, onSignInSuccess)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun SignInScreen(onSignInSuccess: @Composable (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.padding(16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Handle Done action if needed */ }
            )
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.padding(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Handle Done action if needed */ }
            )
        )

        /*Button(
            onClick = {
                signIn(email, password, context, onSignInSuccess)
            }
        ) {
            Text("Sign In with Email")
        }*/

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val intent = Intent(context, SignInActivity::class.java).apply {
                    putExtra("onSignInSuccess", onSignInSuccess as Serializable)
                }
                (context as Activity).startActivityForResult(intent, RC_SIGN_IN)
            }
        ) {
            Text("Sign In with Google")
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }
    }
}

private fun firebaseAuthWithGoogle(idToken: String, activity: Activity, onSignInSuccess: (String) -> Unit) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    FirebaseAuth.getInstance().signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                user?.getIdToken(true)?.result?.token?.let {
                    onSignInSuccess(it)
                }
            } else {
                // Handle failure
                Toast.makeText(activity, "Google sign-in failed", Toast.LENGTH_SHORT).show()
            }
        }
}

@Composable
fun syncWithGoogleCalendar(token: String) {
    val context = LocalContext.current // Get the current context
    val credential = GoogleAccountCredential.usingOAuth2(
        context, listOf(CalendarScopes.CALENDAR)
    ).apply {
        selectedAccount = GoogleSignIn.getLastSignedInAccount(context)?.account
    }

    val service = Calendar.Builder(
        AndroidHttp.newCompatibleTransport(),
        GsonFactory.getDefaultInstance(), // Correct use of GsonFactory
        credential
    ).setApplicationName("MyApp")
        .build()

    // Use the `service` to interact with Google Calendar API
    // Example: Retrieve the user's calendar events
    val events = service.events().list("primary").execute()
    events.items.forEach {
        Log.d("Google Calendar", "Event: ${it.summary}")
    }
}

private fun signIn(email: String, password: String, context: Context, onSignInSuccess: (String) -> Unit) {
    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                user?.getIdToken(true)?.result?.token?.let {
                    onSignInSuccess(it)
                }
            } else {
                // Handle sign-in failure
                Toast.makeText(context, "Email sign-in failed", Toast.LENGTH_SHORT).show()
            }
        }
}
