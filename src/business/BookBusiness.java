package business;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import model.Author;
import model.Book;
import model.CheckoutRecord;
import model.CheckoutRecordEntry;
import model.LibraryMember;
import repository.BookRepository;
import repository.MemberRepository;
import model.BookCopy;
import util.LibrarySystemException;

public class BookBusiness {

	public List<Book> getAll() {
		BookRepository repositoty = new BookRepository();
		List<Book> list = new ArrayList<Book>(repositoty.getAll().values());
		return list;
	}

	public List<BookCopy> getTotalBooks() {
		BookRepository repositoty = new BookRepository();
		List<Book> books = new ArrayList<Book>(repositoty.getAll().values());
		List<BookCopy> copies = new ArrayList<BookCopy>();
		for (Book book : books) {
			copies.addAll(Arrays.asList(book.getBookCopies()));
		}
		return copies;
	}

	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException {
		MemberBusiness memberBusiness = new MemberBusiness();
		LibraryMember member = memberBusiness.findBy(memberId);

		if (member == null)
			throw new LibrarySystemException("Library member with ID " + memberId + " not found!");
		Book book = searchBy(isbn);
		if (book == null || !book.isAvailable())
			throw new LibrarySystemException("Book with ISBN = " + isbn + " is not available for checkout!");
		BookCopy copy = book.getNextAvailableCopy();
		copy.changeAvailability();
		member.checkout(copy, LocalDate.now(), LocalDate.now().plus(book.getMaxCheckoutLength(), ChronoUnit.DAYS));
		MemberRepository repositoty = new MemberRepository();
		repositoty.save(member);
	}

	public Book searchBy(String isbn) {
		BookRepository repositoty = new BookRepository();
		return repositoty.findBy(isbn);
	}

	public List<Book> search(String searchString) {
		BookRepository repositoty = new BookRepository();
		List<Book> list = new ArrayList<Book>();
		List<Book> books = new ArrayList<Book>(repositoty.getAll().values());
		for (Book book : books) {
			if (book.getTitle().toLowerCase().contains(searchString.toLowerCase())) {
				list.add(book);
			}
		}
		return list;
	}

	public boolean addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors)
			throws LibrarySystemException {
		Book book = searchBy(isbn);
		if (book != null)
			throw new LibrarySystemException("Book with isbn " + isbn + " is already in the library collection!");
		BookRepository repositoty = new BookRepository();
		repositoty.save(new Book(isbn, title, maxCheckoutLength, authors));
		return true;
	}

	public boolean updateBookInfo(String isbn, String title, int maxCheckoutLength, List<Author> authors)
			throws LibrarySystemException {
		Book book = searchBy(isbn);
		if (book == null)
			throw new LibrarySystemException("Book with isbn " + isbn + " is  not already in the library collection!");
		BookRepository repositoty = new BookRepository();
		repositoty.save(new Book(isbn, title, maxCheckoutLength, authors));
		return true;
	}

	public boolean addBookCopy(String isbn) throws LibrarySystemException {
		Book book = searchBy(isbn);
		if (book == null)
			throw new LibrarySystemException("No book with isbn " + isbn + " is in the library collection!");
		book.addCopy();
		return true;
	}

	public CheckoutRecord getCheckoutRecordByMemberId(String memberId) throws LibrarySystemException {
		MemberRepository repository = new MemberRepository();
		LibraryMember member = repository.findBy(memberId);
		if (member == null) {
			throw new LibrarySystemException("This memberId " + memberId + "doesn't exist!");
		}
		return member.getCheckoutRecord();
	}

	public int getTotalBookCheckouted() {
		int total = 0;
		MemberRepository repositoty = new MemberRepository();
		List<LibraryMember> memebers = new ArrayList<LibraryMember>(repositoty.getAll().values());

		for (LibraryMember memeber : memebers) {
			total += memeber.getCheckoutRecord().getCheckoutRecordEntries().size();
		}
		return total;
	}

	public int getTotalBookOverDue() {
		int total = 0;
		MemberRepository repositoty = new MemberRepository();
		List<LibraryMember> memebers = new ArrayList<LibraryMember>(repositoty.getAll().values());

		for (LibraryMember memeber : memebers) {
			List<CheckoutRecordEntry> entries = memeber.getCheckoutRecord().getCheckoutRecordEntries();
			for (CheckoutRecordEntry checkoutRecordEntry : entries) {
				if (checkoutRecordEntry.isOverdue()) {
					total++;
				}
			}
		}
		return total;
	}
}
