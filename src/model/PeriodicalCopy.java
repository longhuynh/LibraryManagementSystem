package model;

public class PeriodicalCopy implements IEntityCopy {

	private static final long serialVersionUID = 1L;
	private Periodical periodical;
	private int copyNumber;
	private boolean isAvailable;

	public PeriodicalCopy(Periodical periodical, int copyNumber, boolean isAvailable) {
		this.periodical = periodical;
		this.copyNumber = copyNumber;
		this.isAvailable = isAvailable;
	}

	@Override
	public boolean isAvailable() {
		return isAvailable;
	}

	@Override
	public int getCopyNumuber() {
		return copyNumber;
	}

	@Override
	public IEntity getEntity() {
		return periodical;
	}

	@Override
	public IEntityCopy changeAvailability() {
		PeriodicalCopy copy = clone();
		copy.isAvailable = !isAvailable;
		periodical.updatePeriodicalCopyArray(copy);
		return copy;
	}

	@Override
	public PeriodicalCopy clone() {
		PeriodicalCopy copy;
		try {
			copy = (PeriodicalCopy) super.clone();
			Periodical p = (Periodical) copy.getEntity();
			copy.periodical = p.clone();

		} catch (CloneNotSupportedException e) {
			throw new IllegalStateException("Unable to clone Periodical");
		}
		return copy;
	}
}
