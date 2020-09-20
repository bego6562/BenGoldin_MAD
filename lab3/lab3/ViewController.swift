//
//  ViewController.swift
//  lab3
//
//  Created by Ben Goldin on 9/17/20.
//  Copyright © 2020 Ben Goldin. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var citizenship: UIImageView!
    
    @IBOutlet weak var yesVote: UILabel!
    
    @IBOutlet weak var fontSizeLabel: UILabel!
    
    @IBOutlet weak var capitalSwitch: UISwitch!
    
    @IBOutlet weak var fontSizeSlider: UISlider!
    
    @IBOutlet weak var contrastControl: UISegmentedControl!
    
    @IBOutlet weak var imageControl: UISegmentedControl!
    
    func updateImage() {
        if imageControl.selectedSegmentIndex == 0 {
            citizenship.image = UIImage(named: "citizenship")
            yesVote.text = "A 'yes' vote for Amend. 76 means..."
        } else if imageControl.selectedSegmentIndex == 1 {
            citizenship.image = UIImage(named: "abortion")
            yesVote.text = "A 'yes' vote for Prop. 115 means..."
        } else if imageControl.selectedSegmentIndex == 2{
            citizenship.image = UIImage(named: "familyLeave")
            yesVote.text = "A 'yes' vote for Prop. 118 means..."
        }
    }
    
    func updateCaps() {
        if capitalSwitch.isOn {
            yesVote.text = yesVote.text?.uppercased()
        } else {
            yesVote.text = yesVote.text?.lowercased()
        }
    }
    
    @IBAction func changeInfo(_ sender: UISegmentedControl) {
        updateImage()
        updateCaps()
        
//        if sender.selectedSegmentIndex == 0{
//            citizenship.image = UIImage(named: "citizenship")
//            yesVote.text = "A 'yes' vote for Amend. 76 means..."
//        } else if sender.selectedSegmentIndex == 1{
//            citizenship.image = UIImage(named: "abortion")
//            yesVote.text = "A 'yes' vote for Prop. 115 means..."
//        } else if sender.selectedSegmentIndex == 2{
//            citizenship.image = UIImage(named: "familyLeave")
//            yesVote.text = "A 'yes' vote for Prop. 118 means..."
//        }
    }
    
    @IBAction func updateFont(_ sender: UISwitch) {
        updateCaps()
        
//        if sender.isOn {
//            yesVote.text = yesVote.text?.uppercased()
//        } else {
//            yesVote.text = yesVote.text?.lowercased()
//        }
    }
    
    @IBAction func changeFontSize(_ sender: UISlider) {
        //get new value
        let fontSize = sender.value
        
        //update slider label text
        fontSizeLabel.text = String(format: "%.0f", fontSize)
        
        //change label text size
        let fontSizeCGFloat = CGFloat(fontSize)
        yesVote.font = UIFont.systemFont(ofSize: fontSizeCGFloat)
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    @IBAction func colorContrast(_ sender: UISegmentedControl) {
        
        if sender.selectedSegmentIndex == 0 {
            yesVote.textColor = UIColor(displayP3Red: 0.5, green: 0.5, blue: 0.5, alpha: 1.0)
        } else if sender.selectedSegmentIndex == 1 {
            yesVote.textColor = UIColor(displayP3Red: 0.0, green: 0.2, blue: 0.0, alpha: 1.0)
        }
    }
    


}

