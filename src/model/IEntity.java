package model;

import java.io.Serializable;

public interface IEntity extends Serializable {
	int getCopyCount();

	String getTitle();

	IEntityCopy getNextAvailableCopy();

	int getMaxCheckoutLength();

	boolean isAvailable();

	void addCopy();
}
