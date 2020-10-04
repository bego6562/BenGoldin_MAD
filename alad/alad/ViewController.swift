//
//  ViewController.swift
//  alad
//
//  Created by Ben Goldin on 10/4/20.
//  Copyright © 2020 Ben Goldin. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var topNumber: UILabel!
    @IBOutlet weak var bottomNumber: UILabel!
    @IBOutlet weak var correctOrIncorrectLabel: UILabel!
    @IBOutlet weak var answerOutlet: UISegmentedControl!
    
    
    
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
        
        
        
        // randomly set the bounds of the sliders
    }
    
    
    
    
    @IBAction func topNumberController(_ sender: UISlider) {
        // change the tracking of topNumber
    }
    
    @IBAction func bottomNumberController(_ sender: UISlider) {
        // change the tracking of bottomNumber
    }
    
    @IBAction func answerController(_ sender: UISegmentedControl) {
        // become active if tracking is equal
        
        
        // does the user select the correct answer?
            // yes: set correctOrIncorrectLabel to correct
            // no: set correctOrIncorrectLabel to false
        if sender.selectedSegmentIndex == 0 {
            // if first button is selected
        }
    }
    
    @IBAction func nextQuestionButton(_ sender: UIButton) {
        // call populate random
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

