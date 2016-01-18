package business;

import model.Address;
import model.LibraryMember;
import util.LibrarySystemException;

public interface IMemberBusiness {
	public void addNewMember(String memberId, String firstName, String lastName,
			String telNumber, Address addr) throws LibrarySystemException;
	public LibraryMember search(String memberId);
	public void updateMemberInfo(String memberId, String firstName, String lastName,
			String telNumber, Address addr) throws LibrarySystemException;
}