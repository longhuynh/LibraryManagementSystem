package business;

import java.util.ArrayList;
import java.util.List;

import model.User;
import repository.UserRepository;
import util.LibrarySystemException;

public class UserBusineess {
	public User login(String userId, String password) throws LibrarySystemException {
		UserRepository repository = new UserRepository();		
		return repository.login(userId, password);
	}
	
	public List<User> getAll(){
		UserRepository repositoty = new UserRepository();
		List<User> list = new ArrayList<User>(repositoty.getAll().values());
		return list;
	}

}
