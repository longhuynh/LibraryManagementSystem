package model;

import java.io.Serializable;

final public class BookCopy implements Serializable, Cloneable {
	private static final long serialVersionUID = -6750443883511535912L;
	private Book book;
	private int copyNumuber;
	private boolean isAvailable;

	public BookCopy(Book book, int copyNumuber, boolean isAvailable) {
		this.book = book;
		this.copyNumuber = copyNumuber;
		this.isAvailable = isAvailable;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public int getCopyNumuber() {
		return copyNumuber;
	}

	public Book getBook() {
		return book;
	}
	
	public BookCopy changeAvailability() {
		BookCopy copy = clone();
		copy.isAvailable = !isAvailable;
		book.updateBookCopyArray(copy);
		return copy;
	}

	@Override
	public BookCopy clone() {
		BookCopy bookCopy;
		try {
			bookCopy = (BookCopy) super.clone();
			Book b = (Book) bookCopy.getBook();
			bookCopy.book = b.clone();

		} catch (CloneNotSupportedException e) {
			throw new IllegalStateException("Unable to clone Book");
		}
		return bookCopy;
	}
}
