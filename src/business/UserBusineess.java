package business;

import model.User;
import repository.UserRepository;
import util.LibrarySystemException;

public class UserBusineess {
	public User login(String userId, String password) throws LibrarySystemException {
		UserRepository repository = new UserRepository();		
		return repository.login(userId, password);
	}
	

}
