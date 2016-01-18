package model;

public enum Permission {
	Libraryan(1), Admin(2), Both(3);

	private double value;

	private Permission(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
}