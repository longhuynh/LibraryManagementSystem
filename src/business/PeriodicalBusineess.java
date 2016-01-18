package business;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import model.LibraryMember;
import model.Periodical;
import repository.MemberRepository;
import repository.PeriodicalRepository;
import util.LibrarySystemException;

public class PeriodicalBusineess implements IPerodicalBusiness {
	
	@Override
	public Periodical searchPeriodical(String title, int issueNum) {
		PeriodicalRepository da = new PeriodicalRepository();
		return da.search(title, issueNum);
	}
	
	/**
	 * Looks up Periodical by title and issue number, from data store. 
	 * If not found, return value is false.
	 * If no copies are available for checkout, returns false.
	 * If found and a copy is available, member's checkout record is
	 * updated and copy of this publication is set to "not available"
	 */
	@Override
	public void checkoutPeriodical(String memberId, String title, int issueNum)
			throws LibrarySystemException {
		MemberBusiness memberBusiness = new MemberBusiness();
		LibraryMember member = memberBusiness.search(memberId);
		
		if(member == null) throw new LibrarySystemException("Library member with ID " + memberId + " not found!");
		Periodical periodical = searchPeriodical(title, issueNum);
		if(periodical == null || !periodical.isAvailable()) throw new LibrarySystemException("Periodical = " + title + ", issue number " + issueNum 
				+ " is not available for checkout!");
		
		member.checkout(periodical.getNextAvailableCopy(), 
				LocalDate.now(), 
				LocalDate.now().plus(periodical.getMaxCheckoutLength(), ChronoUnit.DAYS));
		MemberRepository repositoty = new MemberRepository();
		repositoty.save(member);
	}
	
	@Override
	public boolean addPeriodical(int issueNumber, String title, int maxCheckoutLength) 
			throws LibrarySystemException{
		Periodical test = searchPeriodical(title, issueNumber);
		if(test != null) throw new LibrarySystemException("Periodical " + title 
			+ ", issue number " + issueNumber + ", is already in the library collection!");
		PeriodicalRepository repository = new PeriodicalRepository();
		repository.save(new Periodical(issueNumber, title, maxCheckoutLength));
		return true;
	}
	
	@Override
	public boolean addPeriodicalCopy(String title, int issueNum) 
			throws LibrarySystemException {
		Periodical p = searchPeriodical(title, issueNum);
		if(p == null) throw new LibrarySystemException("No periodical entitled "
				+ title + ", issue number " + issueNum  
				+ " is in the library collection!");
		p.addCopy();
		return true;
	}	
}
