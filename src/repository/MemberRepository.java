package repository;

import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccess;
import model.StorageType;
import model.LibraryMember;

public class MemberRepository implements IBaseRepository<LibraryMember> {
	private static HashMap<String, LibraryMember> members;

	@Override
	public LibraryMember search(String memberId) {
		HashMap<String, LibraryMember> mems = getAll();
		if (mems.containsKey(memberId)) {
			return mems.get(memberId);
		}
		return null;
	}

	@Override
	public void save(LibraryMember member) {
		HashMap<String, LibraryMember> mems = getAll();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		members = mems;
		DataAccess.saveToStorage(StorageType.MEMBERS, mems);
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
	public void loadEntityMap(List<LibraryMember> memberList) {
		members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		DataAccess.saveToStorage(StorageType.MEMBERS, members);
	}
}