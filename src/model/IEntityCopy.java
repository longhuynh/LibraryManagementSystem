package model;

import java.io.Serializable;

public interface IEntityCopy extends Serializable {
	public boolean isAvailable();

	public int getCopyNumuber();

	public IEntity getEntity();

	public IEntityCopy changeAvailability();
}