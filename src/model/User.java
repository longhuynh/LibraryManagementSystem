package model;

import java.io.Serializable;

final public class User implements Serializable {
	private static final long serialVersionUID = 5147265048973262104L;
	private String id;
	private String userName;
	private String password;
	private Role role;
	
	public User(String id, String userName, String password, Role role) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	public String getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}	
	
}
