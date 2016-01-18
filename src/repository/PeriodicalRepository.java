package repository;
import java.util.HashMap;
import java.util.List;

import model.StorageType;
import model.Book;
import model.Pair;
import model.Periodical;
import dataaccess.DataAccess;;

public class PeriodicalRepository implements IBaseRepository<Periodical>{
	private static HashMap<Pair<String, Integer>,Periodical> periodicals;

	public Periodical search(String title, int issueNum) {
		HashMap<Pair<String, Integer>, Periodical> periodicalsMap =  getAll();
		return periodicalsMap.get(new Pair<String, Integer>(title, issueNum));
	}
	
	public Periodical search(String title) {
		HashMap<Pair<String, Integer>, Periodical> periodicalsMap =  getAll();
		return periodicalsMap.get(title);
	}
	
	public void save(Periodical periodical) {
		HashMap<Pair<String, Integer>, Periodical> periodMap = getAll();
		Pair<String, Integer> periodKey = new Pair(periodical.getTitle(), periodical.getIssueNumber());
		periodMap.put(periodKey, periodical);
		periodicals = periodMap;
		DataAccess.saveToStorage(StorageType.PERIODICALS, periodMap);	
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<Pair<String,Integer>, Periodical> getAll() {
		if(periodicals == null) {
			periodicals = (HashMap<Pair<String,Integer>, Periodical>) DataAccess.readFromStorage(
				StorageType.PERIODICALS);
		}
		return periodicals;
	}

	
	public void loadEntityMap(List<Periodical> periodicalList) {
		periodicals = new HashMap<Pair<String, Integer>, Periodical>();
		periodicalList.forEach(
			p -> periodicals.put(new Pair<String,Integer>(p.getTitle(), p.getIssueNumber()), p));
		DataAccess.saveToStorage(StorageType.PERIODICALS, periodicals);
	}
	
}
