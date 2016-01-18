package model;

final public class BookCopy implements IEntityCopy, Cloneable {
	private static final long serialVersionUID = 1L;

	private Book book;
	private int copyNumuber;
	private boolean isAvailable;

	public BookCopy(Book book, int copyNumuber, boolean isAvailable) {
		this.book = book;
		this.copyNumuber = copyNumuber;
		this.isAvailable = isAvailable;
	}

	@Override
	public boolean isAvailable() {
		return isAvailable;
	}

	@Override
	public int getCopyNumuber() {
		return copyNumuber;
	}

	@Override
	public IEntity getEntity() {
		return book;
	}

	@Override
	public IEntityCopy changeAvailability() {
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
			Book b = (Book) bookCopy.getEntity();
			bookCopy.book = b.clone();

		} catch (CloneNotSupportedException e) {
			throw new IllegalStateException("Unable to clone Book");
		}
		return bookCopy;
	}
}
