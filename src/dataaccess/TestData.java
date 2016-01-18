package dataaccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Address;
import model.Book;
import model.Author;
import model.CheckoutRecord;
import model.CheckoutRecordEntry;
import model.LibraryMember;
import model.Periodical;
import repository.BookRepository;
import repository.MemberRepository;
import repository.PeriodicalRepository;

public class TestData {
	List<LibraryMember> members = new ArrayList<LibraryMember>();
	@SuppressWarnings("serial")
	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
			add(new Address("51 S. George", "Georgetown", "MI", "65434"));
			add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
			add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
			add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
			add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
			add(new Address("501 Central", "Mountain View", "CA", "94707"));
		}
	};
	public List<Author> authors = new ArrayList<Author>() {
		{
			add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he."));
			add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she."));
			add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts."));
			add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books."));
			add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style."));
		}
	};
	
	//Periodical(int issueNumber, String title, int maxCheckoutLength)
	List<Periodical> periodicals = new ArrayList<Periodical>() {
		{
			add(new Periodical(1, "Journal of Skydiving", 3));
			add(new Periodical(4, "Life Magazine", 7));
			add(new Periodical(100, "Journal of Symbolic Logic", 3));
		}
	};
	//Book(int id, String isbn, String title, int maxCheckoutLength, List<Author> authors)
	List<Book> books = new ArrayList<Book>() {
		{
			add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(authors.get(0), authors.get(1))));
			add(new Book("28-12331", "Antartica", 7, Arrays.asList(authors.get(2))));
			add(new Book("99-22223", "Thinking Java", 21, Arrays.asList(authors.get(3))));
			add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(authors.get(4))));
		}
	};
	
	List<CheckoutRecordEntry> checkoutRecordEntries = new ArrayList<CheckoutRecordEntry>() {
		{
			add(new CheckoutRecordEntry(
				books.get(0).getNextAvailableCopy(), LocalDate.of(2011,12,1), LocalDate.of(2011,12,22)));
			add(new CheckoutRecordEntry(
				books.get(0).getNextAvailableCopy(), LocalDate.of(2015,6,22), LocalDate.of(2015,7,13)));
			add(new CheckoutRecordEntry(
				books.get(1).getNextAvailableCopy(), LocalDate.of(2015,6,27), LocalDate.of(2015,7,18)));
			add(new CheckoutRecordEntry(
				books.get(2).getNextAvailableCopy(), LocalDate.of(2015,6,27), LocalDate.of(2015,7,18)));
			
			add(new CheckoutRecordEntry(
				periodicals.get(0).getNextAvailableCopy(), LocalDate.of(2015,6,20), LocalDate.of(2015,6,27)));
			add(new CheckoutRecordEntry(
				periodicals.get(0).getNextAvailableCopy(), LocalDate.of(2015,6,20), LocalDate.of(2015,6,27)));
			add(new CheckoutRecordEntry(
				periodicals.get(1).getNextAvailableCopy(), LocalDate.of(2015,6,22), LocalDate.of(2015,6,29)));
			add(new CheckoutRecordEntry(
				periodicals.get(2).getNextAvailableCopy(), LocalDate.of(2015,6,22), LocalDate.of(2015,6,25)));
			
		}
	};
	
	List<CheckoutRecord> checkoutRecords = new ArrayList<CheckoutRecord>() {
		{
			add(new CheckoutRecord());
			add(new CheckoutRecord());
			add(new CheckoutRecord());
			add(new CheckoutRecord());
			add(new CheckoutRecord());
			add(new CheckoutRecord());
			add(new CheckoutRecord());
		}
	};

	public void generateBookData() {
		books.get(0).addCopy();
		books.get(0).addCopy();
		books.get(2).addCopy();
		books.get(2).addCopy();
		BookRepository repository = new BookRepository();
		repository.loadEntityMap(books);	
	}

	public void generatePeriodicalData() {
		periodicals.get(0).addCopy();
		periodicals.get(1).addCopy();
		periodicals.get(2).addCopy();
		PeriodicalRepository repository = new PeriodicalRepository();
		repository.loadEntityMap(periodicals);		
	}
	
	public void generateLibraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(0));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(4));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", addresses.get(5));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(2));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(5));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(3));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(6));
		members.add(libraryMember);
		
		MemberRepository repository = new MemberRepository();
		repository.loadEntityMap(members);	
	}
	
	public static void main(String[] args) {
		TestData testData = new TestData();
		testData.generateBookData();
		testData.generatePeriodicalData();
		testData.generateLibraryMemberData();		
	}
}