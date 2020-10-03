//
//  ViewController.swift
//  lab4
//
//  Created by Ben Goldin on 10/1/20.
//  Copyright © 2020 Ben Goldin. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var incomeAmount: UITextField!
    @IBOutlet weak var incomeTax: UILabel!
    
    @IBOutlet weak var impeachLabel: UILabel!
    @IBOutlet weak var impeachStepper: UIStepper!
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    @IBAction func updateImpeach(_ sender: UIStepper) {
        if impeachStepper.value == 1 {
            impeachLabel.text = "1 Time Impeached"
        }
        else {
            impeachLabel.text = String(format: "%.0f", impeachStepper.value) + " Times Impeached"
        }
        updateTaxAmount()
    }

    func updateTaxAmount() {
        var amount:Float
        var taxesDue: Float
        let impeachAmount = impeachStepper.value
        
        if incomeAmount.text!.isEmpty {
            amount = 0.0
        } else {
            amount = Float(incomeAmount.text!)!
        }
        
        if impeachAmount > 0 {
            
            taxesDue = Float(750)
            
        } else {

            if amount >= 0 && amount <= 19751 {
                taxesDue = 0.1 * amount
                print(taxesDue)
            } else if amount > 19751 && amount <= 80250 {
                taxesDue = Float(0.1 * 19750) + Float(0.12 * (amount - 19750))
                print(taxesDue)
                
            } else if amount > 80250 && amount <= 171050 {
                taxesDue = Float(0.1 * 19750) + Float(0.12 * (80250 - 19751)) + Float(0.22 * (amount - 80250))
                print(taxesDue)
                
            } else if amount > 171050 && amount <= 326600 {
                taxesDue = Float(0.1 * 19750) + Float(0.12 * (80250 - 19751)) + Float(0.22 * (326600 - 80250)) + Float(0.24 * (amount - 171050))
                print(taxesDue)
                
            } else if amount > 326600 && amount <= 414700 {
                taxesDue = Float(0.1 * 19750) + Float(0.12 * (80250 - 19751)) + Float(0.22 * (326600 - 80250)) + Float(0.24 * (326600 - 171050)) + Float(0.32 * (amount - 326600))
                print(taxesDue)
                
            } else if amount > 414700 && amount <= 622050 {
                taxesDue = Float(0.1 * 19750) + Float(0.12 * (80250 - 19751)) + Float(0.22 * (326600 - 80250)) + Float(0.24 * (326600 - 171050)) + Float(0.32 * (414700 - 326600)) + Float(0.35 * (amount - 414700))
                print(taxesDue)
                
            } else {
                taxesDue = Float(0.1 * 19750) + Float(0.12 * (80250 - 19751)) + Float(0.22 * (326600 - 80250)) + Float(0.24 * (326600 - 171050)) + Float(0.32 * (414700 - 326600)) + Float(0.35 * (622050 - 414700)) + Float(0.37 * (amount - 622050))
                print(taxesDue)
            }
            
            // brackets:
            // 0 to 19750 is 10%
            // 19751 to 80250 is 12%
            // 80251 to 171050 is 22%
            // 171051 to 326600 is 24%
            // 326601 to 414700 is 32%
            // 414701 to 622050 is 35%
            // 622051 and up is 37%
        }
        
        let impeachNumber = impeachStepper.value
        
        var impeachTotal : Float = 0.0
        if impeachNumber < 2 {
            // pass
        }else {

            let alert=UIAlertController(title: "Warning", message: "No U.S. president has been impeached more than once", preferredStyle: UIAlertController.Style.alert)

            let cancelAction=UIAlertAction(title: "Cancel", style:UIAlertAction.Style.cancel, handler: nil)
            alert.addAction(cancelAction)
            let okAction=UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: {action in
                self.impeachStepper.value = 1
                self.impeachLabel.text? = "1 person"
                self.updateTaxAmount()
            })
            alert.addAction(okAction)
            present(alert, animated: true, completion: nil)
            } //end else
        
        
        
        let currencyFormatter = NumberFormatter()
        currencyFormatter.numberStyle=NumberFormatter.Style.currency
        incomeTax.text=currencyFormatter.string(from: NSNumber(value: taxesDue))

    }
    

    

    
    func textFieldDidEndEditing(_ textField: UITextField) {
        updateTaxAmount()
    }

    override func viewDidLoad() {
        
        incomeAmount.delegate=self
        super.viewDidLoad()
    
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(self.dismissKeyboard))
        view.addGestureRecognizer(tap)
    }

    @objc func dismissKeyboard() {
        view.endEditing(true)
    }

}
