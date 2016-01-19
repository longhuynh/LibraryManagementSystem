package business;

import java.util.HashMap;
import java.util.List;

import model.Author;
import model.Book;
import model.CopyStatus;
import model.BookCopy;
import util.LibrarySystemException;

public interface IBookBusiness {
	public HashMap<String, Book> getAll();
	
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException;

	public boolean addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors)
		throws LibrarySystemException;

	public boolean addBookCopy(String isbn) throws LibrarySystemException;
	
	public CopyStatus computeStatus(BookCopy copy);
}