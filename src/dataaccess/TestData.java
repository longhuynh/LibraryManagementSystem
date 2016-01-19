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
import model.Role;
import repository.BookRepository;
import repository.MemberRepository;

public class TestData {
	List<LibraryMember> members = new ArrayList<LibraryMember>();
	@SuppressWarnings("serial")
	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("1000 N 4th", "Fairfield", "IA", "52557"));
			add(new Address("500 S. George", "Georgetown", "MI", "65434"));
			add(new Address("213 Headley Ave", "Seville", "Georgia", "41234"));
			add(new Address("140 N. Baton", "Baton Rouge", "LA", "33556"));
			add(new Address("200 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("135 Channing Ave", "Palo Alto", "CA", "94301"));
			add(new Address("42 W 2nd ", "Fairfield", "IA", "52556"));
			add(new Address("501 Mountain", "Mountain View", "CA", "94707"));
		}
	};
	
	@SuppressWarnings("serial")
	public List<Author> authors = new ArrayList<Author>() {
		{
			add(new Author("Tom", "Thomas", "641-123-456", addresses.get(0), "Don’t think for a second that I actually care what you have to say."));
			add(new Author("Peter", "Thomas", "641-333-4444", addresses.get(0), "Every storm runs out of rain."));
			add(new Author("Maryam", "Pugh", "641-222-1111", addresses.get(1), "Have lots of hair and like ugly things."));
			add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "I have this new theory that human adolescence doesn’t end until your early thirties."));
			add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "I always feel sad for seedless watermelons, because what if they wanted babies?"));
		}
	};
	
	
	@SuppressWarnings("serial")
	List<Book> books = new ArrayList<Book>() {
		{
			add(new Book("23-12345", "Java Progrming", 21, Arrays.asList(authors.get(0), authors.get(1))));
			add(new Book("28-12331", ".NET from begining", 7, Arrays.asList(authors.get(2))));
			add(new Book("44-22223", "Thinking Java", 21, Arrays.asList(authors.get(3))));
			add(new Book("48-53455", "Business Administor", 7, Arrays.asList(authors.get(4))));
		}
	};
	
	@SuppressWarnings("serial")
	List<CheckoutRecordEntry> checkoutRecordEntries = new ArrayList<CheckoutRecordEntry>() {
		{
			add(new CheckoutRecordEntry(
				books.get(0).getNextAvailableCopy(), LocalDate.of(2016, 1,21), LocalDate.of(2016,12,28)));
			add(new CheckoutRecordEntry(
				books.get(0).getNextAvailableCopy(), LocalDate.of(2016,1,22), LocalDate.of(2016,7,29)));
			add(new CheckoutRecordEntry(
				books.get(1).getNextAvailableCopy(), LocalDate.of(2016,1,22), LocalDate.of(2016,1,29)));
			add(new CheckoutRecordEntry(
				books.get(2).getNextAvailableCopy(), LocalDate.of(2016,1,20), LocalDate.of(2016,1,27)));			
		}
	};
	
	@SuppressWarnings("serial")
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

	public void generateLibraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("1001", "Long", "Huynh", "641-123-4567", addresses.get(0), Role.BOTH);
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(0));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(3));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1002", "Achyut", "Devkota", "702-998-2414", addresses.get(5), Role.ADMIN);
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(1));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(2));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1003", "Amit", "Niroula", "451-234-8811", addresses.get(6), Role.LIBRARIAN);
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(0));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(1));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1004", "Sarah", "Obama", "451-234-8811", addresses.get(1), Role.LIBRARIAN);
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(1));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(3));
		members.add(libraryMember);
		
		MemberRepository repository = new MemberRepository();
		repository.loadEntityMap(members);	
	}
	
	public static void main(String[] args) {
		TestData testData = new TestData();
		System.out.println("Initialize data ...");
		testData.generateBookData();
		testData.generateLibraryMemberData();	
		System.out.println("Finish ...");
	}
}