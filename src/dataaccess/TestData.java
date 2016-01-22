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
import model.User;
import repository.BookRepository;
import repository.MemberRepository;
import repository.UserRepository;

public class TestData {
	List<LibraryMember> members = new ArrayList<LibraryMember>();
	@SuppressWarnings("serial")
	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("1000 N 4th", "Fairfield", "IA", "52557"));		
			add(new Address("213 W. Ave", "Seville", "Georgia", "41234"));
			add(new Address("140 N. Baton", "Baton Rouge", "TX", "33556"));
			add(new Address("200 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("42 W 2nd ", "Fairfield", "IA", "52556"));
			add(new Address("501 Mountain", "Mountain View", "CA", "94707"));
		}
	};

	@SuppressWarnings("serial")
	public List<Author> authors = new ArrayList<Author>() {
		{
			add(new Author("Tom", "Thomas", "641123456", addresses.get(0),
					"Don’t think for a second that I actually care what you have to say."));
			add(new Author("Peter", "Thomas", "6413334444", addresses.get(0), "Every storm runs out of rain."));
			add(new Author("Maryam", "Pugh", "6412221111", addresses.get(1),
					"Have lots of hair and like ugly things."));
			add(new Author("Andrew", "Cleveland", "9764452232", addresses.get(2),
					"I have this new theory that human adolescence doesn’t end until your early thirties."));
			add(new Author("Sarah", "Connor", "1234222663", addresses.get(3),
					"I always feel sad for seedless watermelons, because what if they wanted babies?"));
		}
	};

	@SuppressWarnings("serial")
	List<Book> books = new ArrayList<Book>() {
		{
			add(new Book("2312345", "Java Progrming", 7, Arrays.asList(authors.get(0), authors.get(1))));
			add(new Book("2812331", ".NET from begining", 21, Arrays.asList(authors.get(2))));
			add(new Book("4422223", "Thinking Java", 21, Arrays.asList(authors.get(3))));
			add(new Book("4853455", "Business Administor", 7, Arrays.asList(authors.get(4))));
		}
	};

	@SuppressWarnings("serial")
	List<CheckoutRecordEntry> checkoutRecordEntries = new ArrayList<CheckoutRecordEntry>() {
		{
			// Java Progrming - Long
			add(new CheckoutRecordEntry(books.get(0).getNextAvailableCopy(), LocalDate.of(2016, 1, 10),
					LocalDate.of(2016, 1, 17)));
			// Java Progrming - Achyut
			add(new CheckoutRecordEntry(books.get(0).getNextAvailableCopy(), LocalDate.of(2016, 1, 11),
					LocalDate.of(2016, 1, 18)));
			// .NET from begining - Long
			add(new CheckoutRecordEntry(books.get(1).getNextAvailableCopy(), LocalDate.of(2016, 1, 10),
					LocalDate.of(2016, 1, 31)));
			// Thinking Java - Amit
			add(new CheckoutRecordEntry(books.get(2).getNextAvailableCopy(), LocalDate.of(2016, 1, 7),
					LocalDate.of(2016, 1, 28)));
			// Business Administor - Sarah
			add(new CheckoutRecordEntry(books.get(3).getNextAvailableCopy(), LocalDate.of(2016, 1, 20),
					LocalDate.of(2016, 1, 27)));
		}
	};

	List<User> users = new ArrayList<User>() {
		{
			add(new User("long", "Long Huynh", "123", Role.BOTH));
			add(new User("achyut", "AChyut", "123", Role.ADMIN));
			add(new User("amit", "Amit", "123", Role.LIBRARIAN));
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
		}
	};

	public void generateUserData() {
		UserRepository repository = new UserRepository();
		repository.loadEntityMap(users);
	}

	public void generateBookData() {
		books.get(0).addCopy();
		books.get(0).addCopy();
		books.get(2).addCopy();
		books.get(2).addCopy();
		BookRepository repository = new BookRepository();
		repository.loadEntityMap(books);
	}

	public void generateLibraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("984850", "Long", "Huynh", "6411234567", addresses.get(0));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(0));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(3));
		members.add(libraryMember);

		libraryMember = new LibraryMember("984976", "Achyut", "Devkota", "7029982414", addresses.get(1));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(1));
		members.add(libraryMember);

		libraryMember = new LibraryMember("984992", "Amit", "Niroula", "4512348811", addresses.get(2));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(2));
		members.add(libraryMember);

		libraryMember = new LibraryMember("984990", "Sarah", "Obama", "4512348811", addresses.get(3));
		libraryMember.addCheckoutEntry(checkoutRecordEntries.get(4));
		members.add(libraryMember);

		MemberRepository repository = new MemberRepository();
		repository.loadEntityMap(members);
	}

	public static void main(String[] args) {
		TestData testData = new TestData();
		System.out.println("Initialize data ...");
		testData.generateBookData();
		testData.generateLibraryMemberData();
		testData.generateUserData();
		System.out.println("Finish ...");
	}
}