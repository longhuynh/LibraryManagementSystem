package util;

import java.io.Serializable;

public class LibrarySystemException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public LibrarySystemException() {
		super();
	}

	public LibrarySystemException(String msg) {
		super(msg);
	}

	public LibrarySystemException(Throwable t) {
		super(t);
	}
}
