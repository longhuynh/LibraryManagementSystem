package business;
import model.Address;
import model.LibraryMember;
import model.Role;
import repository.MemberRepository;
import util.LibrarySystemException;

public class MemberBusiness implements IMemberBusiness {

	public void addNewMember(String memberId, String firstName, String lastName,
			String telephone, Address address, Role role) throws LibrarySystemException {
		LibraryMember member = findBy(memberId);
		if(member != null) {
			throw new LibrarySystemException("A library member with memberId = " + memberId + " already exists!"); 
		}
		member  = new LibraryMember(memberId, firstName, lastName, telephone, address, role);
		MemberRepository repositoty = new MemberRepository();
		repositoty.save(member);
	}

	public LibraryMember findBy(String memberId) {
		MemberRepository repositoty = new MemberRepository();
		return repositoty.findBy(memberId);
	}

	public void updateMemberInfo(String memberId, String firstName, String lastName,
		String telephone, Address address, Role role) throws LibrarySystemException {
		LibraryMember member = findBy(memberId);
		if(member == null) {
			throw new LibrarySystemException("No library member with memberId = " + memberId + " found!"); 
		}
		member = new LibraryMember(memberId, firstName, lastName, telephone, address, role);
		MemberRepository repositoty = new MemberRepository();
		repositoty.save(member);
	}

	@Override
	public LibraryMember login(String memberId, String password) throws LibrarySystemException {
		MemberRepository repository = new MemberRepository();		
		return repository.login(memberId, password);
	}
}
