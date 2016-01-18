package repository;

import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccess;
import model.StorageType;
import model.Book;
import model.LibraryMember;

public class BookRepository implements IBaseRepository<Book>{
	private static HashMap<String,Book> books;	
	
	@Override
	public Book search(String isbn) {
		HashMap<String,Book> booksMap =  getAll();
		return booksMap.get(isbn);
	}
	
	@Override
	public void save(Book book) {
		HashMap<String, Book> bookMap = getAll();
		String isbn = book.getIsbn();
		bookMap.put(isbn, book);
		books = bookMap;
		DataAccess.saveToStorage(StorageType.BOOKS, bookMap);	
	}	

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String,Book> getAll() {
		if(books == null) {
			books = (HashMap<String,Book>) DataAccess.readFromStorage(StorageType.BOOKS);
		}
		return books;
	}	

	@Override
	public void loadEntityMap(List<Book> bookList) {
		books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		DataAccess.saveToStorage(StorageType.BOOKS, books);
	}	
}