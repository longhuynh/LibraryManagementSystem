package business;

import model.Periodical;
import util.LibrarySystemException;

public interface IPerodicalBusiness {	
	public Periodical searchPeriodical(String title, int issueNum);
	
	public void checkoutPeriodical(String memberId, String title, int issueNum) throws LibrarySystemException;

	public boolean addPeriodical(int issueNumber, String title, int maxCheckoutLength)
		throws LibrarySystemException;

	public boolean addPeriodicalCopy(String title, int issueNum) throws LibrarySystemException;
	
}