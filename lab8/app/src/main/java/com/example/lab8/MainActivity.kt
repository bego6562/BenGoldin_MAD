package com.example.lab8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var message: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createPreview(view: View) {
        var payPeriod:CharSequence = ""
        var careerFieldList = ""
        var anonymousText = ""

        val payPeriodId = radioGroup.checkedRadioButtonId

        //Pay Period
        if (payPeriodId == -1){
            val paySnackbar = Snackbar.make(root_layout, "Please select a pay period!", Snackbar.LENGTH_SHORT)
            paySnackbar.show()
        } else {
            payPeriod = findViewById<RadioButton>(payPeriodId).text

            // Career Field
            if (checkBox1.isChecked) {
                careerFieldList += " " + checkBox1.text
            }
            if (checkBox2.isChecked) {
                if (checkBox1.isChecked){
                    careerFieldList += " and " + checkBox2.text
                } else {
                    careerFieldList += " " + checkBox2.text
                }
            }
            if (careerFieldList.isNotEmpty()) {
                careerFieldList = " in the field(s) of" + careerFieldList
            }

            // Career Salary
            val salary = spinner.selectedItem

            // Keep Information Anonymous?
            if (anonymousSwitch.isChecked){
                anonymousText = " Your data will be kept anonymous."
            } else {
                anonymousText = " Your data will NOT be kept anonymous."
            }

            message = "Your pay$careerFieldList is $payPeriod" + ". You make $salary" +  "." + anonymousText

            updateUI()
        }

    }

    fun updateUI() {
        messageTextView.text = message
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("message", message)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        message = savedInstanceState.getString("message", "")
        updateUI()
    }

}