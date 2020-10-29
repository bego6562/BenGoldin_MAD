package com.example.halloween

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sayBoo(view: View) {
        //Text View
        val booText = findViewById<TextView>(R.id.textMessage) //equivalent of output variables
        //Edit Name
        val editName = findViewById<EditText>(R.id.editTextName)
        val name = editName.text

        //Message
        booText.setText("Happy Halloween " + name + "!")

        // Image
        val imageGhost = findViewById<ImageView>(R.id.imageView)
        imageGhost.setImageResource(R.drawable.ghost)

    }
}