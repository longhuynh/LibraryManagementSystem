package repository;

import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccess;
import model.User;
import model.StorageType;

public class UserRepository implements IBaseRepository<User> {
	private static HashMap<String, User> users;

	@Override
	public User findBy(String memberId) {
		HashMap<String, User> allMembers = getAll();
		if (allMembers.containsKey(memberId)) {
			return allMembers.get(memberId);
		}
		return null;
	}

	@Override
	public void save(User user) {
		HashMap<String, User> allMembers = getAll();
		String memberId = user.getId();
		allMembers.put(memberId, user);
		users = allMembers;
		DataAccess.saveToStorage(StorageType.USERS, allMembers);
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, User> getAll() {
		if (users == null) {
			users = (HashMap<String, User>) DataAccess.readFromStorage(StorageType.USERS);
		}
		return users;
	}

	@Override
	public void loadEntityMap(List<User> list) {
		users = new HashMap<String, User>();
		list.forEach(user -> users.put(user.getId(), user));
		DataAccess.saveToStorage(StorageType.USERS, users);
	}	
	
	public User login(String userId, String password) {
		User user = findBy(userId);
		if(user != null && user.getPassword().equals(password)) 
			return user;
		return null;
	}	
}
