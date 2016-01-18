package model;

import java.util.Arrays;
import java.util.Optional;

public class Periodical implements IEntity, Cloneable {

	private static final long serialVersionUID = 1L;
	private PeriodicalCopy[] copies;	
	private int issueNumber;
	private String title;
	private int maxCheckoutLength;

	public Periodical(int issueNumber, String title, int maxCheckoutLength) {
		this.issueNumber = issueNumber;
		this.title = title;
		copies = new PeriodicalCopy[] { new PeriodicalCopy(this, 1, true) };
	}

	@Override
	public void addCopy() {
		PeriodicalCopy[] newArr = new PeriodicalCopy[copies.length + 1];
		System.arraycopy(copies, 0, newArr, 0, copies.length);
		newArr[copies.length] = new PeriodicalCopy(this, copies.length + 1, true);
		copies = newArr;
	}

	public void updatePeriodicalCopyArray(PeriodicalCopy copy) {
		for (int i = 0; i < copies.length; ++i) {
			if (copies[i].getCopyNumuber() == copy.getCopyNumuber()) {
				copies[i] = copy;
				break;
			}
		}
	}

	@Override
	public Periodical clone() {
		Periodical periodical = null;
		try {
			periodical = (Periodical) super.clone();
			PeriodicalCopy[] newCopies = new PeriodicalCopy[copies.length];
			for (int i = 0; i < copies.length; ++i) {
				newCopies[i] = new PeriodicalCopy(new Periodical(issueNumber, title, maxCheckoutLength),
						copies[i].getCopyNumuber(), copies[i].isAvailable());

			}
			for (int i = 0; i < copies.length; ++i) {
				((Periodical) newCopies[i].getEntity()).copies = newCopies;
			}
			periodical.copies = newCopies;
		} catch (CloneNotSupportedException e) {
			throw new IllegalStateException("Cannot clone Book");
		}
		return periodical;
	}

	public boolean isAvailable() {
		if (copies == null) {
			return false;
		}
		return Arrays.stream(copies).map(l -> l.isAvailable()).reduce(false, (x, y) -> x || y);
	}

	@Override
	public String toString() {
		return "title: " + title + ", issueNum: " + issueNumber + ", available: " + isAvailable();
	}

	@Override
	public int getCopyCount() {
		return copies.length;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public IEntityCopy getNextAvailableCopy() {
		Optional<PeriodicalCopy> optional = Arrays.stream(copies).filter(x -> x.isAvailable()).findFirst();
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	public int getIssueNumber() {
		return issueNumber;
	}

	@Override
	public boolean equals(Object ob) {
		if (ob == null)
			return false;
		if (ob.getClass() != getClass())
			return false;
		Periodical p = (Periodical) ob;
		return p.title.equals(title) && p.issueNumber == issueNumber;
	}

}
