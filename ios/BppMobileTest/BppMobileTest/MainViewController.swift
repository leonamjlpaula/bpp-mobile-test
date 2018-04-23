//
//  MainViewController.swift
//  BppMobileTest
//
//  Created by Leonam de Paula on 23/04/18.
//  Copyright © 2018 Leonam de Paula. All rights reserved.
//

import UIKit
import Alamofire

class MainViewController: UITableViewController {
	
	struct Transacion : Decodable{
		let transactionId: String
		let transactionDate: String
		let transactionFormattedDate: String
		let transactionCurrency: String
		let transactionAmount: Double
		let billingCurrency: String
		let billingAmount: Double
		let transactionStatus: String
		let transactionName: String
		let merchantName: String
		let mccCode: String
		let mccDescription: String
	}

	var transactions = [Transacion]()
	
	override func viewDidLoad() {
		super.viewDidLoad()
		self.tableView.separatorStyle = .singleLine
		getTransactionData()
	}
	
	func getTransactionData(){
		Alamofire.request("http://test-mobile.dev-bpp.com.br/invoice", method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (data) in
			
			if data.error != nil {
				print("error")
				return
			}
			print(data.debugDescription)
			
			do{
				self.transactions = try JSONDecoder().decode([Transacion].self, from: data.data!)
				DispatchQueue.main.async {
					self.tableView.reloadData()
				}
			}
			catch let jsonErr{
				print(jsonErr.localizedDescription)
			}
		}
	}
	
	override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return transactions.count
	}
	
	override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		
		let transaction = transactions[indexPath.row]
		let cell = tableView.dequeueReusableCell(withIdentifier: "transactionCell") as! TransactionCell
		
		cell.merchantName.text = transaction.merchantName
		
		cell.transactionValue.text = "$\(transaction.billingAmount) \(transaction.billingCurrency)"
	
		cell.transactionDate.text = transaction.transactionDate
		
		cell.merchantDescription.text = transaction.mccDescription
		
		return cell
	}
	
	override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
		return 120
	}
}

