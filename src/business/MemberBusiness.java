package business;
import java.util.ArrayList;
import java.util.List;

import model.Address;
import model.LibraryMember;
import repository.MemberRepository;
import util.LibrarySystemException;

public class MemberBusiness {

	public List<LibraryMember> getAll(){
		MemberRepository repositoty = new MemberRepository();
		List<LibraryMember> list = new ArrayList<LibraryMember>(repositoty.getAll().values());
		return list;
	}	
	
	public List<LibraryMember> search(String searchString){
		MemberRepository repositoty = new MemberRepository();
		List<LibraryMember> list = new ArrayList<LibraryMember>();
		List<LibraryMember> members = new ArrayList<LibraryMember>(repositoty.getAll().values());
		for(LibraryMember member : members){
			if(member.getFirstName().toLowerCase().contains(searchString.toLowerCase())
				||member.getLastName().toLowerCase().contains(searchString.toLowerCase())){
				list.add(member);
			}
		}
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
