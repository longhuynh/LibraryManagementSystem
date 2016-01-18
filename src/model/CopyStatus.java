package model;

import java.io.Serializable;

final public class CopyStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	private IEntityCopy copy;
	private LibraryMember borrowingMember;
	private boolean isOverdue = false;

	public CopyStatus(IEntityCopy copy, LibraryMember member, boolean isOverdue) {
		this.copy = copy;
		this.borrowingMember = member;
		this.isOverdue = isOverdue;
	}

	public IEntityCopy getCopy() {
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
		return "Status: " + copy.getCopyNumuber() + ": " + copy.getEntity() + ", " + "member id: "
				+ borrowingMember.getMemberId() + " is overdue? " + isOverdue;
	}
}
