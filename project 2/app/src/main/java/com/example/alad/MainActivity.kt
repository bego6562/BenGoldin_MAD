package com.example.alad

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // generate random numbers on launch and update UI
        generateRandom()
        updateUI()

    }

    //figure out why it's delayed to work

    //make landscape view: Android Device Environments, Project 2 small group critiques (37:43)

    // finding correct answer
    var answerLocation = 0
    var answer = 0

    // class level to know when they're equal in controllerEqual
    var fontSizeTopExternal = 0.toFloat()
    var fontSizeBottomExternal = 0.toFloat()

    // class level for save on rotation
    var topNumberVariable = 0
    var bottomNumberVariable = 0
    var randomAnswerOne = 0
    var randomAnswerTwo = 0
    var randomSegmentOne = 0
    var randomSegmentTwo = 0
    var randomSegmentThree = 0
    var randomList = listOf(0)
    var fontSizeOne = 0.toFloat()
    var fontSizeTwo = 0.toFloat()
    var valueToTop = 0.toFloat()
    var valueToBottom = 0.toFloat()

    // where the slider currently is/ where it starts at
    var topSliderValue = 0.toFloat()
    var bottomSliderValue = 0.toFloat()

    var externalTextViewAnswer = ""


    fun resetRandom(view: View) {

        generateRandom()
        updateUI()

    }

    fun generateRandom(){
        externalTextViewAnswer = ""

        // randomly generate two numbers that will be added together

        topNumberVariable = (100..400).random() //https://stackoverflow.com/questions/45685026/how-can-i-get-a-random-number-in-kotlin
        bottomNumberVariable = (10..99).random()

        // generate correct and incorrect answers

        answer = topNumberVariable + bottomNumberVariable // correct answer
        randomAnswerOne = (answer-50..answer+50).random() // random answer #1
        randomAnswerTwo = (answer-50..answer+50).random() // random answer #2

        while (randomAnswerOne == answer || randomAnswerTwo == answer) { // make sure random answer #1 or #2 != answer
            randomAnswerOne = (answer-50..answer+50).random()
            randomAnswerTwo = (answer-50..answer+50).random()
        }

        while (randomAnswerOne == randomAnswerTwo) { // make sure random answer #1 an #2 aren't =
            randomAnswerTwo = randomAnswerOne + 5
        }

        // creating the randomly ordered indexes

        randomSegmentOne = (0..2).random()
        answerLocation = randomSegmentOne
        randomSegmentTwo = (0..2).random()

        while (randomSegmentOne == randomSegmentTwo){
            randomSegmentTwo = (0..2).random()
        }

        randomSegmentThree = 3 - randomSegmentOne - randomSegmentTwo

        // random initial number sizes

        fontSizeOne = (10..50).random().toFloat()
        fontSizeTwo = (10..50).random().toFloat()

        // randomly generate the upper bound of the slider

        valueToTop = (50..65).random().toFloat()
        valueToBottom = (50..65).random().toFloat()


        topSliderValue = fontSizeOne
        bottomSliderValue = fontSizeTwo

        // need to set external value to bottom end of slider or the app will crash on rotation since these values otherwise aren't set until topSliderChange()

        fontSizeTopExternal = fontSizeOne
        fontSizeBottomExternal = fontSizeTwo

        // disable radio button & remove label

        radioGroup.clearCheck()
        answerOne.isClickable = false //https://stackoverflow.com/questions/9376966/how-to-disable-a-radiogroup-until-checkbox-is-checked/9377769
        answerTwo.isClickable = false
        answerThree.isClickable = false

        // change color of radio buttons to reflect that they're disabled

        answerOne.setTextColor(Color.parseColor("#c2c2c2")); //https://stackoverflow.com/questions/4602902/how-to-set-the-text-color-of-textview-in-code
        answerTwo.setTextColor(Color.parseColor("#c2c2c2"));
        answerThree.setTextColor(Color.parseColor("#c2c2c2"));

        // remove any text

        answerTextView.setText("")
    }

    fun updateUI(){

        // set numbers

        topNumber.setText(topNumberVariable.toString())
        bottomNumber.setText("+ $bottomNumberVariable")

        randomList = listOf(answer, randomAnswerOne, randomAnswerTwo) // using indexes to randomly assign to radio buttons

        // assign correct and incorrect answers to radio buttons

        answerOne.setText(randomList[randomSegmentOne].toString())
        answerTwo.setText(randomList[randomSegmentTwo].toString())
        answerThree.setText(randomList[randomSegmentThree].toString())

        // set random initial number sizes

        topNumber.textSize = topSliderValue
        bottomNumber.textSize = topSliderValue

        // set bounds on sliders

        topSlider.valueFrom = fontSizeOne
        topSlider.valueTo = valueToTop
        topSlider.value = topSliderValue

        bottomSlider.valueFrom = fontSizeTwo
        bottomSlider.valueTo = valueToBottom
        bottomSlider.value = bottomSliderValue

        answerTextView.text = externalTextViewAnswer

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
                answerTextView.text = getString(R.string.correct)
                externalTextViewAnswer = getString(R.string.correct)
            } else {
                answerTextView.text = getString(R.string.incorrect)
                externalTextViewAnswer = getString(R.string.incorrect)
            }
        } else {
            //snack bar
            val alignSnackbar = Snackbar.make(root_layout, getString(R.string.snackAlert), Snackbar.LENGTH_SHORT)
            alignSnackbar.show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("answerLocation", answerLocation)
        outState.putInt("answer", answer)
        outState.putFloat("fontSizeTopExternal", fontSizeTopExternal)
        outState.putFloat("fontSizeBottomExternal", fontSizeBottomExternal)

        outState.putInt("topNumberVariable", topNumberVariable)
        outState.putInt("bottomNumberVariable", bottomNumberVariable)
        outState.putInt("randomAnswerOne", randomAnswerOne)
        outState.putInt("randomAnswerTwo", randomAnswerTwo)
        outState.putInt("randomSegmentOne", randomSegmentOne)
        outState.putInt("randomSegmentTwo", randomSegmentTwo)
        outState.putInt("randomSegmentThree", randomSegmentThree)

        outState.putFloat("fontSizeOne", fontSizeOne)
        outState.putFloat("fontSizeTwo", fontSizeTwo)
        outState.putFloat("valueToTop", valueToTop)
        outState.putFloat("valueToBottom", valueToBottom)

        outState.putFloat("topSliderValue", topSliderValue)
        outState.putFloat("bottomSliderValue", bottomSliderValue)

        outState.putString("externalTextViewAnswer", externalTextViewAnswer)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        answerLocation = savedInstanceState.getInt("answerLocation")
        answer = savedInstanceState.getInt("answer")
        fontSizeTopExternal = savedInstanceState.getFloat("fontSizeTopExternal")
        fontSizeBottomExternal = savedInstanceState.getFloat("fontSizeBottomExternal")

        topNumberVariable = savedInstanceState.getInt("topNumberVariable")
        bottomNumberVariable = savedInstanceState.getInt("bottomNumberVariable")

        randomAnswerOne = savedInstanceState.getInt("randomAnswerOne")
        randomAnswerTwo = savedInstanceState.getInt("randomAnswerTwo")
        randomSegmentOne = savedInstanceState.getInt("randomSegmentOne")
        randomSegmentTwo = savedInstanceState.getInt("randomSegmentTwo")
        randomSegmentThree = savedInstanceState.getInt("randomSegmentThree")

        fontSizeOne = savedInstanceState.getFloat("fontSizeOne")
        fontSizeTwo = savedInstanceState.getFloat("fontSizeTwo")

        valueToTop = savedInstanceState.getFloat("valueToTop")
        valueToBottom = savedInstanceState.getFloat("valueToBottom")

        topSliderValue = savedInstanceState.getFloat("fontSizeTopExternal")
        bottomSliderValue = savedInstanceState.getFloat("fontSizeBottomExternal")

        externalTextViewAnswer = savedInstanceState.getString("externalTextViewAnswer").toString()

        updateUI()
    }
}
