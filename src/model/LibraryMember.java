package model;

import java.io.Serializable;
import java.time.LocalDate;

public class LibraryMember extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULTPASSWORD = "123456";
	private String memberId;
	private String password;
	private Role role;
	private CheckoutRecord record = new CheckoutRecord();

	public LibraryMember(String memberId, String firstName, String lastName, String telephone, Address address, Role role) {
		super(firstName, lastName, telephone, address);
		this.memberId = memberId;
		this.role = role;
		this.setPassword(DEFAULTPASSWORD);
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

	public CheckoutRecord getRecord() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;			
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
