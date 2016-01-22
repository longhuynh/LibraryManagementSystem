package model;

import java.io.Serializable;
import java.time.LocalDate;

public class LibraryMember extends Person implements Serializable {

	private static final long serialVersionUID = 258745813979930997L;
	private String memberId;	
	private CheckoutRecord record = new CheckoutRecord();

	public LibraryMember(String memberId, String firstName, String lastName, String telephone, Address address) {
		super(firstName, lastName, telephone, address);
		this.memberId = memberId;	
	}

	public void checkout(BookCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
		copy = copy.changeAvailability();
		CheckoutRecordEntry entry = CheckoutRecordEntry.createEntry(copy, checkoutDate, dueDate);
		record = record.addEntry(entry);
	}

	public void addCheckoutEntry(CheckoutRecordEntry entry) {
		record = record.addEntry(entry);
	}

	public String getMemberId() {
		return memberId;
	}

	public CheckoutRecord getCheckoutRecord() {
		return record;
	}

	public String formattedCheckoutRecord() {
		StringBuilder sb = new StringBuilder();
		for (CheckoutRecordEntry e : record.getCheckoutRecordEntries()) {
			sb.append(e.toString() + "\n");
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + ", "
				+ getTelephone() + " " + getAddress();
	}
}
