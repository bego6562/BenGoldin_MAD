package com.example.ivoted

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun sayIVoted(view: View) {
        val votedText = findViewById<TextView>(R.id.textMessage)

        val editName = findViewById<EditText>(R.id.editTextName)
        val name = editName.text

        votedText.setText(name)

        val imageVoted = findViewById<ImageView>(R.id.imageView)
        imageVoted.setImageResource(R.drawable.voted)

    }
}