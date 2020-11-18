package com.example.lab8

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mConfirm = FormShare();
    private var selectedPosition = 0
    private val REQUEST_CODE = 1

    var message: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton.setOnClickListener {
            selectedPosition = spinner.selectedItemPosition
            mConfirm.suggestSalary(selectedPosition)

            Log.i("salary suggested", mConfirm.name)
            Log.i("url suggested", mConfirm.url)

            val intent = Intent(this, confirmActivity::class.java)
            intent.putExtra("confirmName", mConfirm.name)
            intent.putExtra("confirmUrl", mConfirm.url)

            startActivityForResult(intent, REQUEST_CODE)

        }

    }

    fun createPreview(view: View) {
        var payPeriod:CharSequence = ""
        var careerFieldList = ""
        var anonymousText = ""

        val payPeriodId = radioGroup.checkedRadioButtonId

        //Pay Period
        if (payPeriodId == -1){
            val paySnackbar = Snackbar.make(root_layout, getString(R.string.snackAlert), Snackbar.LENGTH_SHORT)
            paySnackbar.show()
        } else {
            payPeriod = findViewById<RadioButton>(payPeriodId).text

            // Career Field
            if (checkBox1.isChecked) {
                careerFieldList += " " + checkBox1.text
            }
            if (checkBox2.isChecked) {
                if (checkBox1.isChecked){
                    careerFieldList += getString(R.string.and) + checkBox2.text
                } else {
                    careerFieldList += " " + checkBox2.text
                }
            }
            if (careerFieldList.isNotEmpty()) {
                careerFieldList = getString(R.string.fieldList) + careerFieldList
            }

            // Career Salary
            val salary = spinner.selectedItem

            // Keep Information Anonymous?
            if (anonymousSwitch.isChecked){
                anonymousText = getString(R.string.anonymousData)
            } else {
                anonymousText = getString(R.string.notAnonymous)
            }

            message = getString(R.string.yourPay) + " " + careerFieldList + " " + getString(R.string.`is`) + " " + payPeriod + getString(R.string.youMake) + " " + salary +  ". " + anonymousText

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)) {
            confirmationTextView.setText(data?.let { data.getStringExtra("date and name verification") })
        }
    }

}