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
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}