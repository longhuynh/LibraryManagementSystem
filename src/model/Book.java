package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

final public class Book implements Serializable, Cloneable {
	private static final long serialVersionUID = -7264854361540907943L;
	private BookCopy[] copies;
	private List<Author> authors;
	private String isbn;
	private String title;
	private int maxCheckoutLength;

	public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.authors = Collections.unmodifiableList(authors);
		this.copies = new BookCopy[] { new BookCopy(this, 1, true) };
	}

	public void updateBookCopyArray(BookCopy copy) {
		for (int i = 0; i < copies.length; ++i) {
			if (copies[i].getCopyNumuber() == copy.getCopyNumuber()) {
				copies[i] = copy;
				break;
			}
		}
	}

	public void addCopy() {
		BookCopy[] newBooks = new BookCopy[copies.length + 1];
		System.arraycopy(copies, 0, newBooks, 0, copies.length);
		newBooks[copies.length] = new BookCopy(this, copies.length + 1, true);
		copies = newBooks;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != getClass())
			return false;
		Book b = (Book) obj;
		return b.isbn.equals(isbn);
	}

	@Override
	public Book clone() {
		Book book = null;
		try {
			book = (Book) super.clone();
			BookCopy[] newCopies = new BookCopy[copies.length];
			for (int i = 0; i < copies.length; ++i) {
				newCopies[i] = new BookCopy(new Book(isbn, title, maxCheckoutLength, authors),
						copies[i].getCopyNumuber(), copies[i].isAvailable());
			}
			for (int i = 0; i < copies.length; ++i) {
				((Book) newCopies[i].getBook()).copies = newCopies;
			}
			book.copies = newCopies;
		} catch (CloneNotSupportedException e) {
			throw new IllegalStateException("Cannot clone Book");
		}
		return book;
	}

	public boolean isAvailable() {
		if (copies == null) {
			return false;
		}
		return Arrays.stream(copies).map(l -> l.isAvailable()).reduce(false, (x, y) -> x || y);
	}

	@Override
	public String toString() {
		return "isbn: " + isbn + ", available: " + isAvailable();
	}

	public int getCopyCount() {
		return copies.length;
	}

	public String getTitle() {
		return title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public String getIsbn() {
		return isbn;
	}
	
	public BookCopy[] getBookCopies() {
		return copies;
	}


	public BookCopy getNextAvailableCopy() {
		Optional<BookCopy> optional = Arrays.stream(copies).filter(x -> x.isAvailable()).findFirst();
		return optional.isPresent() ? optional.get() : null;
	}


	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}
}
