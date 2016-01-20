package business;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import model.Author;
import model.Book;
import model.CheckoutRecord;
import model.CopyStatus;
import model.LibraryMember;
import repository.BookRepository;
import repository.MemberRepository;
import model.BookCopy;
import util.LibrarySystemException;

public class BookBusiness {
	
	public List<Book> getAll(){
		BookRepository repositoty = new BookRepository();
		List<Book> list = new ArrayList<Book>(repositoty.getAll().values());
		return list;
	}
	
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException {
		MemberBusiness memberBusiness = new MemberBusiness();
		LibraryMember member = memberBusiness.findBy(memberId);
		
		if(member == null) throw new LibrarySystemException("Library member with ID " + memberId + " not found!");
		Book book = searchBy(isbn);
		if(book == null || !book.isAvailable()) throw new LibrarySystemException(
				"Book with ISBN = " + isbn + " is not available for checkout!");
		BookCopy copy = book.getNextAvailableCopy();
		copy.changeAvailability();
		member.checkout(copy, 
				LocalDate.now(), 
				LocalDate.now().plus(book.getMaxCheckoutLength(), ChronoUnit.DAYS));
		MemberRepository repositoty = new MemberRepository();
		repositoty.save(member);
	}
	
	public Book searchBy(String isbn) {
		BookRepository repositoty = new BookRepository();
		return repositoty.findBy(isbn);
	}
	
	public List<Book> search(String searchString){
		BookRepository repositoty = new BookRepository();
		List<Book> list = new ArrayList<Book>();
		List<Book> books = new ArrayList<Book>(repositoty.getAll().values());
		for(Book book : books){
			if(book.getTitle().toLowerCase().contains(searchString.toLowerCase())){
				list.add(book);
			}
		}
		return list;
	}		

	public boolean addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) 
			throws LibrarySystemException {
		Book book = searchBy(isbn);
		if(book != null) throw new LibrarySystemException("Book with isbn " + isbn 
			+ " is already in the library collection!");
		BookRepository repositoty = new BookRepository();
		repositoty.save(new Book(isbn, title, maxCheckoutLength, authors));
		return true;
	}
	
	public boolean updateMemberInfo(String isbn, String title, int maxCheckoutLength, List<Author> authors) 
			throws LibrarySystemException {
		Book book = searchBy(isbn);
		if(book == null) throw new LibrarySystemException("Book with isbn " + isbn 
			+ " is  not already in the library collection!");
		BookRepository repositoty = new BookRepository();
		repositoty.save(new Book(isbn, title, maxCheckoutLength, authors));
		return true;
	}
	
	public boolean addBookCopy(String isbn) throws LibrarySystemException {
		Book book = searchBy(isbn);
		if(book == null) throw new LibrarySystemException("No book with isbn " + isbn 
			+ " is in the library collection!");
		book.addCopy();
		return true;
	}
	
	public CheckoutRecord getCheckoutRecordByMemberId(String memberId) throws LibrarySystemException {
		MemberRepository repository = new MemberRepository();
		LibraryMember member = repository.findBy(memberId);
		if (member == null) {
			throw new LibrarySystemException("This memberId " + memberId + "doesn't exist!");
		}
		return member.getRecord();
	}
	
	public CopyStatus computeStatus(BookCopy copy) {
		MemberRepository repository = new MemberRepository();
		Book item = copy.getBook();
		List<LibraryMember> membersFound = repository.getAll().values()
				         .stream()			      
				         .filter(member ->  
				            {   //returns all members with a checkout record having an entry
				            	//containing a copy that matches input copy
				            	return member.getRecord().getCheckoutRecordEntries()
				            	      .stream()
				            	      .filter(e -> 			                   
				            	            e.getCopy().getBook().equals(item))
				            	      .findAny()
				            	      .isPresent();
				            })
				          .collect(Collectors.toList());
		CopyStatus status = null;
		
		if(!membersFound.isEmpty()) {
			LibraryMember borrower = membersFound.get(0);
			CheckoutRecord record = borrower.getRecord();
			//returns true if the checkout record entry for this copy indicates the item is overdue
			boolean isOverdue = record.getCheckoutRecordEntries().stream()
					                  .filter(e -> e.getCopy().getBook().equals(item) 
					                		  && e.isOverdue())
					                  .findAny().isPresent();
		
			status = new CopyStatus(copy, borrower, isOverdue);
		} else {
			status = new CopyStatus(copy, null, false);
		}				    
		return status;
	}
}
