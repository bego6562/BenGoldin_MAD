//
//  ViewController.swift
//  daVinci
//
//  Created by Ben Goldin on 9/1/20.
//  Copyright © 2020 Ben Goldin. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var artImage: UIImageView! //IB stands for interface builder
    
    @IBAction func chooseArt(_ sender: UIButton) {
        if sender.tag == 1 {
            artImage.image = UIImage(named: "MonaLisa")
        } else if sender.tag == 2 {
            artImage.image = UIImage(named: "Virtruvian")
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

