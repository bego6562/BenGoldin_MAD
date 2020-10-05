//
//  ViewController.swift
//  alad
//
//  Created by Ben Goldin on 10/4/20.
//  Copyright © 2020 Ben Goldin. All rights reserved.
//

import UIKit
//import SwiftUI

class ViewController: UIViewController {

    
    @IBOutlet weak var topNumber: UILabel!
    @IBOutlet weak var bottomNumber: UILabel!
    @IBOutlet weak var correctOrIncorrectLabel: UILabel!
    @IBOutlet weak var answerOutlet: UISegmentedControl!
    
    @IBOutlet weak var topSlider: UISlider!
    @IBOutlet weak var bottomSlider: UISlider!
    
    
    
    var fontSizeTopExternal: Float = 0.0
    var fontSizeBottomExternal: Float = 0.0
    
    var answerLocation = 0
    
    func populateRandom() { // should be called by nextQuestionButton
        
        // randomly generate two numbers
        
        let topNumberVariable = Int.random(in: 1..<400)
        topNumber.text = String(topNumberVariable)
        
        let bottomNumberVariable = Int.random(in: 1..<99)
        bottomNumber.text = "+ " + String(bottomNumberVariable)
        
        // generating the correct and incorrect answers
        
        let answer = topNumberVariable + bottomNumberVariable
        let randomAnswerOne = Int.random(in: answer-50..<answer+50) // have two incorrect options be +- 50 from the answer
        var randomAnswerTwo = Int.random(in: answer-50..<answer+50)
        
        while randomAnswerOne == randomAnswerTwo{ // make sure the two wrong answers aren't equal
            randomAnswerTwo = Int.random(in: answer-50..<answer+50)
        }
        
        // selecting where the different numbers will go (randomly)
        let randomSegmentOne = Int.random(in: 0...2)
        answerLocation = randomSegmentOne // we need to get the answers location out of this function
        var randomSegmentTwo = Int.random(in: 0...2)

        while randomSegmentOne == randomSegmentTwo {
            randomSegmentTwo = Int.random(in: 0...2)
        }
        
        let randomSegmentThree = 3 - randomSegmentOne - randomSegmentTwo

        // assigning the correct and incorrect values to the segments

        answerOutlet.setTitle(String(answer), forSegmentAt: randomSegmentOne)
        answerOutlet.setTitle(String(randomAnswerOne), forSegmentAt: randomSegmentTwo)
        answerOutlet.setTitle(String(randomAnswerTwo), forSegmentAt: randomSegmentThree)
        
        // randomly track out the two numbers
        
        /*
        let kerningOne = CGFloat.random(in: -50..<50)
        let kerningTwo = CGFloat.random(in: -50..<50)
        let topNumberString = String(topNumber.text!)
        let bottomNumberString = String(bottomNumber.text!)
        
        var randomlyTrackedOutTop = Text(topNumberString).tracking(kerningOne)
        var randomlyTrackedOutBottom = Text(bottomNumberString).tracking(kerningTwo)
        
        Text(topNumberString).tracking(kerningOne)
        */
        
        let fontSizeOne = CGFloat.random(in: 6..<40)
        let fontSizeTwo = CGFloat.random(in: 6..<40)
    
        topNumber.font = UIFont.systemFont(ofSize: fontSizeOne)
        bottomNumber.font = UIFont.systemFont(ofSize: fontSizeTwo)
        
       // var randomlyTrackedOutBottom = Text(bottomNumberString).tracking(kerningTwo)
//        topNumberString = NSAttributedString(string: topNumberString, attributes: [.kern: 3.12])
//
//        NSMutableAttributedString(string: topNumberString).kerning =  SwiftUI.kerning(kerningOne)
//
//        bottomNumberString!.tracking(kerningTwo)
//        let fontSizeCGFloat = CGFloat(fontSize)
//        yesVote.font = UIFont.systemFont(ofSize: fontSizeCGFloat)
        
        // randomly set the bounds of the sliders
        
        let fontSizeOneFloat = Float(fontSizeOne)
        
        let topSliderMin = fontSizeOneFloat
        let topSliderMax = Float.random(in: 40...80)
        
        topSlider.minimumValue = topSliderMin
        topSlider.maximumValue = topSliderMax
        topSlider.value = topSliderMin
        
        let fontSizeTwoFloat = Float(fontSizeTwo)
        
        let bottomSliderMin = fontSizeTwoFloat
        let bottomSliderMax = Float.random(in: 40...80)
        
        bottomSlider.minimumValue = bottomSliderMin
        bottomSlider.maximumValue = bottomSliderMax
        bottomSlider.value = bottomSliderMin
        
        answerOutlet.setEnabled(false, forSegmentAt: 0)
        answerOutlet.setEnabled(false, forSegmentAt: 1)
        answerOutlet.setEnabled(false, forSegmentAt: 2)
        
        correctOrIncorrectLabel.text = ""
    
    }
    
    /*
     @IBOutlet var Slider1Slider: UISlider!

     @IBOutlet var Slider2Slider: UISlider!

     func configureDefaultSlider2() {
         Slider2Slider.minimumValue = 0
         Slider2Slider.maximumValue = (30 - slider1)
         Slider2Slider.value = 0
         Slider2Slider.continuous = true
     }
     func configureDefaultSlider() {
         Slider1Slider.minimumValue = 0
         Slider1Slider.maximumValue = (30 - slider2)
         Slider1Slider.value = 0
         Slider1Slider.continuous = true
     }
     */

    @IBAction func topNumberController(_ sender: UISlider) {
        // change the tracking of topNumber
        let fontSizeTop = sender.value
        fontSizeTopExternal = fontSizeTop
        // change lable text size
        let fontSizeCG = CGFloat(fontSizeTop)
        topNumber.font = UIFont.systemFont(ofSize: fontSizeCG)
        
        controllerEqual()
    }
    
    @IBAction func bottomNumberController(_ sender: UISlider) {
        // change the tracking of bottomNumber
        let fontSizeBottom = sender.value
        fontSizeBottomExternal = fontSizeBottom
        // change lable text size
        let fontSizeCG = CGFloat(fontSizeBottom)
        bottomNumber.font = UIFont.systemFont(ofSize: fontSizeCG)
        
        controllerEqual()
        
    }
    
    func controllerEqual() {
        if fontSizeTopExternal - fontSizeBottomExternal <= 2 && fontSizeTopExternal - fontSizeBottomExternal >= -2 {
            //answerOutlet.setEnabled(true, forSegmentAt: Int)
//            func setEnabled(_ enabled: Bool,
//            forSegmentAt segment: Int)
            answerOutlet.setEnabled(true, forSegmentAt: 0)
            answerOutlet.setEnabled(true, forSegmentAt: 1)
            answerOutlet.setEnabled(true, forSegmentAt: 2)
        } else {
            answerOutlet.setEnabled(false, forSegmentAt: 0)
            answerOutlet.setEnabled(false, forSegmentAt: 1)
            answerOutlet.setEnabled(false, forSegmentAt: 2)
        }
    }
    
    @IBAction func answerController(_ sender: UISegmentedControl) {
        // become active if tracking is equal
        
        // does the user select the correct answer?
            // yes: set correctOrIncorrectLabel to correct
            // no: set correctOrIncorrectLabel to false
        if sender.selectedSegmentIndex == answerLocation {
            correctOrIncorrectLabel.text = "Correct. Great Job!"
            // if first button is selected
        } else {
            correctOrIncorrectLabel.text = "Incorrect. Try Again!"
        }
    }
    
    @IBAction func nextQuestionButton(_ sender: UIButton) {
        populateRandom()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        populateRandom()
    }


}

