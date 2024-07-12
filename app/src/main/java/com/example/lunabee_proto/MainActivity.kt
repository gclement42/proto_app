package com.example.lunabee_proto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val myBtn: Button = findViewById(R.id.my_button)
        val myTextView: TextView = findViewById(R.id.my_textview)

        var name = "Clement"
        var greeting = getString(R.string.greeting, name)
        myTextView.text = greeting
        myBtn.setOnClickListener {
            name = "Salome"
            greeting = getString(R.string.greeting, name)
            myTextView.text = greeting
        }
    }
}