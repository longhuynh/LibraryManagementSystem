package model;

public enum Role {
	LIBRARIAN(1), ADMIN(2), BOTH(3);

	private double value;

	private Role(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
}