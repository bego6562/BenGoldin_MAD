//
//  ViewController.swift
//  mobileAppleDevelopement
//
//  Created by Ben Goldin on 9/1/20.
//  Copyright © 2020 Ben Goldin. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var messageText: UILabel!
    
    @IBOutlet weak var appleImage: UIImageView!
    
    @IBAction func builder(_ sender: UIButton) {
        if sender.tag == 1 {
            appleImage.image = UIImage(named: "BuggyApple")
            messageText.text = "Error: Please Debug"
        } else if sender.tag == 2 {
            appleImage.image = UIImage(named: "CompletedApple")
            messageText.text = "Success: Enjoy!"
        }

        
        
    }
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

