package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookTableEntry {
	private final SimpleStringProperty isbn = new SimpleStringProperty("");
	private final SimpleStringProperty title = new SimpleStringProperty("");
	private final SimpleStringProperty copyNumber = new SimpleStringProperty("");
	private final SimpleStringProperty maxCheckoutLength = new SimpleStringProperty("");
	private final SimpleStringProperty author = new SimpleStringProperty("");
	
	public BookTableEntry(Book entry) {
		this.isbn.set(entry.getIsbn());
		this.title.set(entry.getTitle());
		this.copyNumber.set(String.valueOf(entry.getCopyCount()).toString());
		this.maxCheckoutLength.set("" + entry.getMaxCheckoutLength());
		this.author.set(getAuthorName(entry));
	}

	private String getAuthorName(Book entry) {
		String result = "";
		for (Author author : entry.getAuthors()) {
			result += author.getFullName() + ", ";
		}
		if(result.length() > 0)
			result = result.substring(0, result.length()-2);
		return result;
	}

	public String getIsbn() {
		return this.isbn.get();
	}

	public String getTitle() {
		return this.title.get();
	}

	public String getCopyNumber() {
		return this.copyNumber.get();
	}

	public String getMaxCheckoutLength() {
		return this.maxCheckoutLength.get();
	}

	public String getAuthor() {
		return this.author.get();
	}

	public StringProperty isbnProperty() {
		return this.isbn;
	}

	public StringProperty titlePropery() {
		return this.title;
	}

	public StringProperty copyNumber() {
		return this.copyNumber;
	}

	public StringProperty maxCheckoutLengthPropery() {
		return this.maxCheckoutLength;
	}

	public StringProperty authorPropery() {
		return this.author;
	}
}
