package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final public class CheckoutRecord implements Serializable {
	private static final long serialVersionUID = -3717821819154967735L;
	private ArrayList<CheckoutRecordEntry> entries = new ArrayList<>();
	
	public CheckoutRecord() {}	

	@SuppressWarnings("unchecked")
	public CheckoutRecord addEntry(CheckoutRecordEntry entry) {
		ArrayList<CheckoutRecordEntry> entriesCopy = (ArrayList<CheckoutRecordEntry>) entries.clone();
		entriesCopy.add(entry);
		return new CheckoutRecord(entriesCopy);
	}

	public CheckoutRecord(ArrayList<CheckoutRecordEntry> entries) {
		this.entries = entries;
	}

	public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
		return Collections.unmodifiableList(entries);
	}
}