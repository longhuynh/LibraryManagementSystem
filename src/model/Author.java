package model;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
	private static final long serialVersionUID = -7721389047367743750L;
	private String bio;
	private String credentials;

	public String getBio() {
		return bio;
	}

	public String getCredentials() {
		return credentials;
	}

	public Author(String firstName, String lastName, String telephone, Address address, String bio) {
		super(firstName, lastName, telephone, address);
		this.bio = bio;
		this.credentials = "";
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    final Person other = (Person) obj;
	    if ((this.getFullName() == null) ? (other.getFullName() != null) : !this.getFullName().equals(other.getFullName())) {
	        return false;
	    }
	    return true;
	}
}