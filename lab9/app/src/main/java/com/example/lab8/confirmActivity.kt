package com.example.lab8

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_confirm.*

data class confirmActivity(val name:String = "") : AppCompatActivity() {

    private var confirmName:String? = null
    private var confirmUrl:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)
        setSupportActionBar(findViewById(R.id.toolbar))

        confirmName = intent.getStringExtra("confirmName")
        confirmUrl = intent.getStringExtra("confirmUrl")

        confirmName?.let { Log.i("name received", it)};
        confirmUrl?.let { Log.i("url received", it)};

        confirmName?.let {feedbackTextView.text = getString(R.string.youIndicatedYouMake) + " " + confirmName + getString(R.string.pleaseConfirm)}

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            loadWebSite()
        }
    }

    private fun loadWebSite(){
        var intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = confirmUrl?.let { Uri.parse(confirmUrl)}


        startActivity(intent)
    }

    override fun onBackPressed() {
        val data = Intent()
        var date = editTextDate.text.toString()
        var name = editTextPersonName.text.toString()
        var dateAndName = "$name $date"
        data.putExtra("date and name verification", dateAndName)
        setResult(Activity.RESULT_OK, data)
        super.onBackPressed()
        finish()

    }
}