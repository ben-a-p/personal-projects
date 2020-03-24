package com.techelevator.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;

public class SalesReport {

	private File salesFile;

	public String writeSalesReport(Map<String, VendingItem> vendingMap, BigDecimal totalSales) {
		String result = "";
		int fileCounter = 0;
		do {
			fileCounter++;
			salesFile = new File("salesReport-" + fileCounter + ".txt");
		} while (salesFile.exists());
		fileCounter++;
		try (PrintWriter writer = new PrintWriter(salesFile)) {
			for (String selector : vendingMap.keySet()) {
				writer.println(vendingMap.get(selector).getName() + "|" + vendingMap.get(selector).getNumOfSales());
			}

			writer.println("\n**TOTAL SALES** $" + totalSales);
			result = "Printing Sales Report";
		} catch (FileNotFoundException e) {
			result = "File could not be created.";
		}
		
		return result;
	}

	public void deleteAllSalesReports() {
		int deleteCounter = 100;
		while (deleteCounter > 0) {
			deleteCounter--;
			salesFile = new File("salesReport-" + deleteCounter + ".txt");
			if (salesFile.exists()) {
				salesFile.delete();
			}
		}
	}

	public String deleteSpecificSalesReport(int salesReport) {
		String result = "";
		salesFile = new File("salesReport-" + salesReport + ".txt");
		if (salesFile.exists()) {
			salesFile.delete();
			result = salesFile.getName() + "has been deleted";
		} else {
			result = salesFile.getName() + "does not exist";
		}
		return result;
	}

}
