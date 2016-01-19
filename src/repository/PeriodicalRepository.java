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

	public Periodical findBy(String title, int issueNum) {
		HashMap<Pair<String, Integer>, Periodical> allPeriodicals =  getAll();
		return allPeriodicals.get(new Pair<String, Integer>(title, issueNum));
	}
	
	public Periodical findBy(String title) {
		HashMap<Pair<String, Integer>, Periodical> periodicalsMap =  getAll();
		return periodicalsMap.get(title);
	}
	
	public void save(Periodical periodical) {
		HashMap<Pair<String, Integer>, Periodical> allPeriodicals = getAll();
		Pair<String, Integer> periodKey = new Pair(periodical.getTitle(), periodical.getIssueNumber());
		allPeriodicals.put(periodKey, periodical);
		periodicals = allPeriodicals;
		DataAccess.saveToStorage(StorageType.PERIODICALS, allPeriodicals);	
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<Pair<String,Integer>, Periodical> getAll() {
		if(periodicals == null) {
			periodicals = (HashMap<Pair<String,Integer>, Periodical>) DataAccess.readFromStorage(
				StorageType.PERIODICALS);
		}
		return periodicals;
	}

	
	public void loadEntityMap(List<Periodical> list) {
		periodicals = new HashMap<Pair<String, Integer>, Periodical>();
		list.forEach(
			p -> periodicals.put(new Pair<String,Integer>(p.getTitle(), p.getIssueNumber()), p));
		DataAccess.saveToStorage(StorageType.PERIODICALS, periodicals);
	}
}
