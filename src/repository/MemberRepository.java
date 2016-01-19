package repository;

import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccess;
import model.StorageType;
import model.LibraryMember;

public class MemberRepository implements IBaseRepository<LibraryMember> {
	private static HashMap<String, LibraryMember> members;

	@Override
	public LibraryMember findBy(String memberId) {
		HashMap<String, LibraryMember> allMembers = getAll();
		if (allMembers.containsKey(memberId)) {
			return allMembers.get(memberId);
		}
		return null;
	}

	@Override
	public void save(LibraryMember member) {
		HashMap<String, LibraryMember> allMembers = getAll();
		String memberId = member.getMemberId();
		allMembers.put(memberId, member);
		members = allMembers;
		DataAccess.saveToStorage(StorageType.MEMBERS, allMembers);
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> getAll() {
		if (members == null) {
			members = (HashMap<String, LibraryMember>) DataAccess.readFromStorage(StorageType.MEMBERS);
		}
		return members;
	}

	@Override
	public void loadEntityMap(List<LibraryMember> list) {
		members = new HashMap<String, LibraryMember>();
		list.forEach(member -> members.put(member.getMemberId(), member));
		DataAccess.saveToStorage(StorageType.MEMBERS, members);
	}	
}