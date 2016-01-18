package business;

import java.util.List;

import model.Author;
import model.CopyStatus;
import model.IEntityCopy;
import util.LibrarySystemException;

public interface IBookBusiness {
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException;

	public boolean addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors)
		throws LibrarySystemException;

	public boolean addBookCopy(String isbn) throws LibrarySystemException;
	
	public CopyStatus computeStatus(IEntityCopy copy);
}