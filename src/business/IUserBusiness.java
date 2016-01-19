package business;

import model.User;
import util.LibrarySystemException;

public interface IUserBusiness {	
	public User login(String userId, String password) throws LibrarySystemException;
	
}