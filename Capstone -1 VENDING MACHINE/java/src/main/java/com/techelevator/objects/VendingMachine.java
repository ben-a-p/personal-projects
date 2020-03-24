package com.techelevator.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

	private BigDecimal balance = new BigDecimal("0.00");
	private Map<String, VendingItem> vendingMap = new LinkedHashMap<String, VendingItem>();
	private AuditWriter auditWriter = new AuditWriter();
	private BigDecimal totalSales = new BigDecimal("0.00");
	private SalesReport salesReport = new SalesReport();

	public VendingMachine(String location) {
		File input = new File(location);
		try (Scanner inventoryScanner = new Scanner(input)) {
			while (inventoryScanner.hasNextLine()) {
				String currentLine = inventoryScanner.nextLine();
				String[] currentLineArray = currentLine.split("\\|");
				BigDecimal price = new BigDecimal(currentLineArray[2]);
				VendingItem currentItem;
				if (currentLineArray[3].equals("Chip")) {
					currentItem = new Chip(currentLineArray[1], price);
				} else if (currentLineArray[3].equals("Candy")) {
					currentItem = new Candy(currentLineArray[1], price);
				} else if (currentLineArray[3].equals("Drink")) {
					currentItem = new Drink(currentLineArray[1], price);
				} else {
					currentItem = new Gum(currentLineArray[1], price);
				}
				addInventory(currentLineArray[0], currentItem);
			}
		} catch (FileNotFoundException e) {

		}
	}

	public void addInventory(String place, VendingItem item) {
		vendingMap.put(place, item);
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public String getChange() {
		String change = "";
		int quartersCount = 0;
		int dimesCount = 0;
		int nickelsCount = 0;
		BigDecimal totalChange = balance;

			while (balance.compareTo(new BigDecimal("0.25")) >= 0) {
				balance = balance.subtract(new BigDecimal("0.25"));
				quartersCount++;
			}
			if (quartersCount > 0) {
				change += quartersCount + " quarters ";
			}
			while (balance.compareTo(new BigDecimal("0.10")) >= 0) {
				balance = balance.subtract(new BigDecimal("0.10"));
				dimesCount++;
			}
			if (dimesCount > 0) {
				change += dimesCount + " dimes ";
			}
			while (balance.compareTo(new BigDecimal("0.05")) >= 0) {
				balance = balance.subtract(new BigDecimal("0.05"));
				nickelsCount++;
			}
			if (nickelsCount > 0) {
				change += nickelsCount + " nickels ";
			}
		if (!change.equals("")) {
			change = "Your change is " + change;
			change += auditWriter.writeCompletedTransaction(totalChange, balance);
		} else {
			change = "No change.";
		}
		return change;
	}

	public String processPurchase(String selector) {
		String result = "";
		for (String key : vendingMap.keySet()) {
			if (key.equals(selector)) {
				if (getItemPrice(selector).compareTo(balance) <= 0 && hasItem(selector) == true) {
					balance = balance.subtract(getItemPrice(selector));
					purchase(selector);
					result += auditWriter.writeItemPurchased(vendingMap.get(selector).getName(), selector,
							getItemPrice(selector), balance);
					result = getItemMessage(selector) + "\nCurrent balance is $" + balance;
					totalSales = totalSales.add(getItemPrice(selector));
					int sales = vendingMap.get(selector).getNumOfSales();
					sales++;
					vendingMap.get(selector).setNumOfSales(sales);
					break;
				} else if (getItemPrice(selector).compareTo(balance) > 0) {
					result = "ERROR, please insert more currency.";
					break;
				} else if (hasItem(selector) != false) {
					result = "ERROR, item out of stock";
					break;
				}
			} else
				result = "ERROR, not a valid input.";

		}
		return result;
	}

	public String getMoney(String money) {
		String moneyReturn = "";
		if (money.endsWith(".00")) {
			money = money.substring(0, money.length()-3);
		}
		try {
			int moneyEntered = Integer.parseInt(money);
			if (moneyEntered < 1) {
				throw new NumberFormatException();
			} else {
				BigDecimal moneyAdded = new BigDecimal(moneyEntered + ".00");
				balance = balance.add(moneyAdded);
				moneyReturn = "Current balance is $" + balance;
				moneyReturn += auditWriter.writeFeedInMoney(moneyAdded, balance);
			}
		
		} catch (NumberFormatException e) {
			moneyReturn = money + " is not a valid input.";
		}
		return moneyReturn;
	}

	public String getInventory() { //format differently
		String result = "";
		for (String place : vendingMap.keySet()) {
			String name = vendingMap.get(place).getName();
			BigDecimal price = vendingMap.get(place).getPrice();
			if (vendingMap.get(place).getNumOfItem() > 0) {
				result += String.format("%-20s %5s %5s%.2f \n", name, place, "$", price);
			} else {
				result += String.format("%-20s %5s %5s%.2f %s\n", name, place, "$", price, "SOLD OUT");
			}
		}
		return result;
	}

	public void purchase(String key) {
		int num = vendingMap.get(key).getNumOfItem();
		num--;
		vendingMap.get(key).setNumOfItem(num);
	}

	public BigDecimal getItemPrice(String key) {
		BigDecimal price = vendingMap.get(key).getPrice();
		return price;
	}

	public String getItemMessage(String key) {
		return vendingMap.get(key).getNoise();
	}

	public boolean hasItem(String key) {
		boolean hasItem = false;
		if (vendingMap.get(key).getNumOfItem() > 0) {
			hasItem = true;
		}
		return hasItem;
	}

	public String writeSalesReport() {
		return salesReport.writeSalesReport(vendingMap, totalSales);
	}

	public void deleteAllSalesReports() {
		salesReport.deleteAllSalesReports();
	}

	public String deleteSpecificSalesReport(String salesReportNumString) {
		String result = "";
		try {
			int salesReportNum = Integer.parseInt(salesReportNumString.replace("-", "a"));
			result = salesReport.deleteSpecificSalesReport(salesReportNum);
		} catch (NumberFormatException e) {
			result = "Invalid input";
		}
		return result;
	}

}
