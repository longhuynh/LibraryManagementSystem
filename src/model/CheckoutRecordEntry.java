package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import util.Constant;

final public class CheckoutRecordEntry implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private BookCopy copy;
	private LocalDate checkoutDate;
	private LocalDate dueDate;

	// this field is necessary in order to detect which items are overdue
	private boolean hasBeenReturned = false;

	public CheckoutRecordEntry(BookCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
		this.copy = copy;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}

	public static CheckoutRecordEntry createEntry(BookCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
		return new CheckoutRecordEntry(copy, checkoutDate, dueDate);
	}

	public BookCopy getCopy() {
		return copy;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public boolean isOverdue() {
		if (hasBeenReturned)
			return false;
		if (dueDate.isBefore(LocalDate.now()))
			return true;
		return false;
	}

	public CheckoutRecordEntry returnCopy() {
		if (hasBeenReturned) {
			return this;
		}
		CheckoutRecordEntry entry = clone();
		entry.hasBeenReturned = true;
		return entry;
	}

	@Override
	public CheckoutRecordEntry clone() {
		CheckoutRecordEntry entry = null;
		try {
			entry = (CheckoutRecordEntry) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalStateException("Cannot clone CheckoutRecordEntry");
		}
		return entry;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != getClass())
			return false;
		CheckoutRecordEntry e = (CheckoutRecordEntry) obj;
		return checkoutDate.equals(e.checkoutDate) && dueDate.equals(e.dueDate)
				&& copy.getBook().equals(e.copy.getBook())
				&& copy.getCopyNumuber() == e.copy.getCopyNumuber();
	}

	@Override
	public String toString() {
		return "[" + "checkoutdate:" + checkoutDate.format(DateTimeFormatter.ofPattern(Constant.DATE_PATTERN))
				+ ", dueDate: " + dueDate.format(DateTimeFormatter.ofPattern(Constant.DATE_PATTERN)) + ", publication: "
				+ copy + "]";
	}
}
