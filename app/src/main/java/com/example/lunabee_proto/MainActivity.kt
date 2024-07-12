package com.example.lunabee_proto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val loginButton: Button = findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            authenticateUser()
        }
    }

    private fun authenticateUser() {
        val usernameEditText: TextView = findViewById(R.id.edit_text_username)
        val passwordEditText: TextView = findViewById(R.id.edit_text_password)
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()
        if (username.isEmpty() or password.isEmpty()) {
            Log.d("MainActivity", "Username or password is empty")
        }
        else {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}