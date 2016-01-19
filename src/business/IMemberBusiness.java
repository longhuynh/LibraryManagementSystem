package business;

import model.Address;
import model.LibraryMember;
import model.Role;
import util.LibrarySystemException;

public interface IMemberBusiness {
	public LibraryMember login(String memberId,String password) throws LibrarySystemException;
	
	public void addNewMember(String memberId, String firstName, String lastName,
			String telNumber, Address address, Role role) throws LibrarySystemException;
	
	public LibraryMember findBy(String memberId);
	
	public void updateMemberInfo(String memberId, String firstName, String lastName,
			String telNumber, Address address, Role role) throws LibrarySystemException;
}