package model;

import java.io.Serializable;

final public class CopyStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	private BookCopy copy;
	private LibraryMember borrowingMember;
	private boolean isOverdue = false;

	public CopyStatus(BookCopy copy, LibraryMember member, boolean isOverdue) {
		this.copy = copy;
		this.borrowingMember = member;
		this.isOverdue = isOverdue;
	}

	public BookCopy getCopy() {
		return copy;
	}

	public boolean isCheckedOut() {
		return copy.isAvailable();
	}

	public LibraryMember getBorrowingMember() {
		return borrowingMember;
	}

	public boolean isOverdue() {
		return isOverdue;
	}

	@Override
	public String toString() {
		return "Status: " + copy.getCopyNumuber() + ": " + copy.getBook() + ", " + "member id: "
				+ borrowingMember.getMemberId() + " is overdue? " + isOverdue;
	}
}
