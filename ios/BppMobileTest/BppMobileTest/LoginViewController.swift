//
//  ViewController.swift
//  BppMobileTest
//
//  Created by Leonam de Paula on 23/04/18.
//  Copyright © 2018 Leonam de Paula. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON
class LoginViewController: UIViewController {
	
	@IBOutlet weak var emailTextField: UITextField!
	@IBOutlet weak var passwordTextField: UITextField!
	@IBOutlet weak var activityIndicator: UIActivityIndicatorView!
	
	override func viewDidLoad() {
		super.viewDidLoad()
	}
	
	@IBAction func login(_ sender: Any) {
		activityIndicator.isHidden = false
		
		let urlString = "http://test-mobile.dev-bpp.com.br/login"
		let pass = Data(passwordTextField.text!.utf8).base64EncodedString()
		
		let url = URL(string: urlString)!
		var request = URLRequest(url: url)
		request.setValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
		request.httpMethod = "POST"
		let postString = "email=\(emailTextField.text!)&password=\(pass)"
		request.httpBody = postString.data(using: .utf8)
		let task = URLSession.shared.dataTask(with: request) { data, response, error in
			guard let data = data, error == nil else {
				// check for fundamental networking error
				print("error=\(String(describing: error))")
				return
			}
			DispatchQueue.main.async {
				self.activityIndicator.isHidden = true
			}
			
			let json = JSON(data)
			if json["code"].intValue == 200{
				DispatchQueue.main.async {
					self.dismiss(animated: false, completion: nil)
					self.performSegue(withIdentifier: "goToMain", sender: nil)
				}
			}
			else{
				DispatchQueue.main.async {
					self.showErrorDialog(message: json["message"].stringValue)
				}
			}
			
			
		}
		task.resume()
		
		//		Alamofire.request(urlString, method: .post, parameters: param, encoding: JSONEncoding.default, headers: nil).responseJSON {
		//			response in
		//			switch response.result {
		//
		//			case .success:
		//				print(response)
		//				self.activityIndicator.isHidden = true
		//				break
		//
		//			case .failure(let error):
		//				print(error)
		//				self.activityIndicator.isHidden = true
		//			}
		//		}
	}
	
	func showErrorDialog(message:String){
		
		let alert = UIAlertController(title: "Aviso", message: message, preferredStyle: .alert)
		alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
		present(alert, animated: true, completion: nil)
	}
}

