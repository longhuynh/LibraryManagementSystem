package business;
import model.Address;
import model.LibraryMember;
import repository.MemberRepository;
import util.LibrarySystemException;

public class MemberBusiness implements IMemberBusiness {

	public void addNewMember(String memberId, String firstName, String lastName,
			String telephone, Address address) throws LibrarySystemException {
		LibraryMember member = search(memberId);
		if(member != null) {
			throw new LibrarySystemException("A library member with memberId = " + memberId + " already exists!"); 
		}
		member  = new LibraryMember(memberId, firstName, lastName, telephone, address);
		MemberRepository repositoty = new MemberRepository();
		repositoty.save(member);
	}
	
	/**
	 * Reads data store for a library member with specified id.
	 * Ids begin at 1001...
	 * Returns a LibraryMember if found, null otherwise
	 * 
	 */
	public LibraryMember search(String memberId) {
		MemberRepository repositoty = new MemberRepository();
		return repositoty.search(memberId);
	}

	public void updateMemberInfo(String memberId, String firstName, String lastName,
		String telephone, Address address) throws LibrarySystemException {
		LibraryMember member = search(memberId);
		if(member == null) {
			throw new LibrarySystemException("No library member with memberId = " + memberId + " found!"); 
		}
		member = new LibraryMember(memberId, firstName, lastName, telephone, address);
		MemberRepository repositoty = new MemberRepository();
		repositoty.save(member);
	}
}
