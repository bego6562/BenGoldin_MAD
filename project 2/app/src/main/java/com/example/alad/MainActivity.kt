package com.example.alad

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // need to resetRandom() here ... not sure how tho
    }

    //make state save on rotation

    //XML stuff: add a theme, add font, add launch icon, make a landscape view

    //figure out why it's delayed to work

    var answerLocation = 0
    var answer = 0
    var fontSizeTopExternal = 0.toFloat()
    var fontSizeBottomExternal = 0.toFloat()

    fun resetRandom(view: View) {
        // randomly generate two numbers that will be added together

        val topNumberVariable = (100..400).random() //https://stackoverflow.com/questions/45685026/how-can-i-get-a-random-number-in-kotlin
        topNumber.setText(topNumberVariable.toString())

        val bottomNumberVariable = (10..99).random()
        bottomNumber.setText("+ $bottomNumberVariable")

        // generate correct and incorrect answers

        answer = topNumberVariable + bottomNumberVariable // correct answer
        var randomAnswerOne = (answer-50..answer+50).random() // random answer #1
        var randomAnswerTwo = (answer-50..answer+50).random() // random answer #2

        while (randomAnswerOne == answer || randomAnswerTwo == answer) { // make sure random answer #1 or #2 != answer
            randomAnswerOne = (answer-50..answer+50).random()
            randomAnswerTwo = (answer-50..answer+50).random()
        }
        while (randomAnswerOne == randomAnswerTwo) { // make sure random answer #1 an #2 aren't =
            randomAnswerTwo = (answer-50..answer+50).random()
        }

        // creating the randomly ordered indexes

        val randomSegmentOne = (0..2).random()
        answerLocation = randomSegmentOne
        var randomSegmentTwo = (0..2).random()

        while (randomSegmentOne == randomSegmentTwo){
            randomSegmentTwo = (0..2).random()
        }

        val randomSegmentThree = 3 - randomSegmentOne - randomSegmentTwo

        val randomList = listOf(answer, randomAnswerOne, randomAnswerTwo) // using indexes to randomly assign to radio buttons

        // assign correct and incorrect answers to radio buttons

        answerOne.setText(randomList[randomSegmentOne].toString())
        answerTwo.setText(randomList[randomSegmentTwo].toString())
        answerThree.setText(randomList[randomSegmentThree].toString())

        // random initial number sizes

        val fontSizeOne = (10..50).random().toFloat()
        val fontSizeTwo = (10..50).random().toFloat()

        topNumber.textSize = fontSizeOne
        bottomNumber.textSize = fontSizeTwo

        // randomly set the upper bound of the sliders, set the lower bound to font size generated above

        topSlider.valueFrom = fontSizeOne
        topSlider.valueTo = (50..65).random().toFloat()
        topSlider.value = fontSizeOne

        bottomSlider.valueFrom = fontSizeTwo
        bottomSlider.valueTo = (50..65).random().toFloat()
        bottomSlider.value = fontSizeTwo

        // disable radio button & remove label

        radioGroup.clearCheck()
        answerOne.isClickable = false //https://stackoverflow.com/questions/9376966/how-to-disable-a-radiogroup-until-checkbox-is-checked/9377769
        answerTwo.isClickable = false
        answerThree.isClickable = false

        answerOne.setTextColor(Color.parseColor("#c2c2c2")); //https://stackoverflow.com/questions/4602902/how-to-set-the-text-color-of-textview-in-code
        answerTwo.setTextColor(Color.parseColor("#c2c2c2"));
        answerThree.setTextColor(Color.parseColor("#c2c2c2"));

        answerTextView.setText("")

    }

    fun topSliderChange(view: View) {
/*
        topSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being started
            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being stopped
            }
        })
*/
        topSlider.addOnChangeListener { slider, value, fromUser ->
            // Respond to change in slider's value
            fontSizeTopExternal = value
            topNumber.textSize = fontSizeTopExternal
            //bottomNumber.textSize = fontSizeTopExternal
            Log.i("Top", value.toString())

            controllerEqual()
        }

        bottomSlider.addOnChangeListener { slider, value, fromUser ->
            // Respond to change in slider's value
            fontSizeBottomExternal = value
            bottomNumber.textSize = fontSizeBottomExternal
            Log.i("Bottom", value.toString())

            controllerEqual()
        }

    }

    private fun controllerEqual() {
        if (fontSizeBottomExternal - fontSizeTopExternal <= 2 && fontSizeBottomExternal - fontSizeTopExternal >= -2 ) {
            answerOne.isClickable = true
            answerTwo.isClickable = true
            answerThree.isClickable = true

            answerOne.setTextColor(Color.parseColor("#000000"));
            answerTwo.setTextColor(Color.parseColor("#000000"));
            answerThree.setTextColor(Color.parseColor("#000000"));
        }
    }

    fun answerChecker(view: View) {
        val radioId = radioGroup.checkedRadioButtonId
        if (findViewById<RadioButton>(radioId) != null) {
            if (findViewById<RadioButton>(radioId).text == answer.toString()) {
                answerTextView.text = "Correct. Great Job!"
            } else {
                answerTextView.text = "Incorrect. Try Again!"
            }
        }
    }




}
