package business;

import model.Periodical;
import util.LibrarySystemException;

public interface IPerodicalBusiness {	
	public Periodical findBy(String title, int issueNumber);
	
	public void checkoutPeriodical(String memberId, String title, int issueNum) throws LibrarySystemException;

	public boolean addPeriodical(int issueNumber, String title, int maxCheckoutLength)
		throws LibrarySystemException;

	public boolean addPeriodicalCopy(String title, int issueNum) throws LibrarySystemException;
	
}