package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CheckoutRecordTableEntry {
	private final SimpleStringProperty isbn = new SimpleStringProperty("");
	private final SimpleStringProperty title = new SimpleStringProperty("");
	private final SimpleStringProperty copyNumber = new SimpleStringProperty("");
	private final SimpleStringProperty checkoutDate = new SimpleStringProperty("");
	private final SimpleStringProperty dueDate = new SimpleStringProperty("");
	private final SimpleStringProperty memberId = new SimpleStringProperty("");	
	
	public CheckoutRecordTableEntry(String isbn, String title, String copyNumber, String memberId, String dueDate) {
		this.isbn.set(isbn);
		this.memberId.set(memberId);
		this.title.set(title);
		this.dueDate.set(dueDate);
		this.copyNumber.set(copyNumber);
	}

	public CheckoutRecordTableEntry(CheckoutRecordEntry entry) {
		isbn.set(entry.getCopy().getBook().getIsbn());
		title.set(entry.getCopy().getBook().getTitle());
		copyNumber.set(String.valueOf(entry.getCopy().getBook().getCopyCount()).toString());
		checkoutDate.set(entry.getCheckoutDate().toString());
		dueDate.set(entry.getDueDate().toString());
	}

	public String getISBN() {
		return this.isbn.get();
	}

	public String getTitle() {
		return this.title.get();
	}

	public String getCopyNum() {
		return this.copyNumber.get();
	}

	public String getCheckoutDate() {
		return this.checkoutDate.get();
	}

	public String getDueDate() {
		return this.dueDate.get();
	}

	public StringProperty isbnProperty() {
		return this.isbn;
	}

	public StringProperty titlePropery() {
		return this.title;
	}

	public StringProperty copyNumPropery() {
		return this.copyNumber;
	}

	public StringProperty checkoutDatePropery() {
		return this.checkoutDate;
	}

	public StringProperty dueDatePropery() {
		return this.dueDate;
	}

	public StringProperty memberIdPropery() {
		return this.memberId;
	}
}
