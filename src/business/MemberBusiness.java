package business;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Address;
import model.LibraryMember;
import model.Role;
import repository.MemberRepository;
import util.LibrarySystemException;

public class MemberBusiness implements IMemberBusiness {

	public List<LibraryMember> getAll(){
		MemberRepository repositoty = new MemberRepository();
		List<LibraryMember> list = new ArrayList<LibraryMember>(repositoty.getAll().values());
		return list;
	}
	
	
	public void addNewMember(String memberId, String firstName, String lastName,
			String telephone, Address address) throws LibrarySystemException {
		LibraryMember member = findBy(memberId);
		if(member != null) {
			throw new LibrarySystemException("A library member with memberId = " + memberId + " already exists!"); 
		}
		member  = new LibraryMember(memberId, firstName, lastName, telephone, address);
		MemberRepository repositoty = new MemberRepository();
		repositoty.save(member);
	}

	public LibraryMember findBy(String memberId) {
		MemberRepository repositoty = new MemberRepository();
		return repositoty.findBy(memberId);
	}

	public void updateMemberInfo(String memberId, String firstName, String lastName,
		String telephone, Address address) throws LibrarySystemException {
		LibraryMember member = findBy(memberId);
		if(member == null) {
			throw new LibrarySystemException("No library member with memberId = " + memberId + " found!"); 
		}
		member = new LibraryMember(memberId, firstName, lastName, telephone, address);
		MemberRepository repositoty = new MemberRepository();
		repositoty.save(member);
	}	
}
