package com.techelevator.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditWriter {

	private File auditFile = new File("audit.txt");
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");

	public String writeFeedInMoney(BigDecimal moneyAdded, BigDecimal balance) {
		LocalDateTime a = LocalDateTime.now();
		return appendToAudit(a.format(formatter) + " FEED MONEY: $" + moneyAdded + " $" + balance);
	}

	public String writeItemPurchased(String name, String key, BigDecimal itemPrice, BigDecimal balance) {
		LocalDateTime a = LocalDateTime.now();
		return appendToAudit(a.format(formatter) + " " + name + " " + key + " $" + itemPrice + " $" + balance);
	}

	public String writeCompletedTransaction(BigDecimal change, BigDecimal balance) {
		LocalDateTime a = LocalDateTime.now();
		return appendToAudit(a.format(formatter) + " GIVE CHANGE: $" + change + " $" + balance);
	}

	private String appendToAudit(String string) {
		String result = "";
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(auditFile, true))) {
			writer.println(string);
		} catch (FileNotFoundException e) {
			result = "\nExisting audit file was not found.";
		}
		return result;
	}

}
