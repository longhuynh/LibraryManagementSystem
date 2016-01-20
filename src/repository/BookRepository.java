package repository;

import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccess;
import model.StorageType;
import model.Book;

public class BookRepository implements IBaseRepository<Book>{
	private static HashMap<String,Book> books;	
	
	@Override
	public Book findBy(String isbn) {
		HashMap<String,Book> allBooks = getAll();
		return allBooks.get(isbn);
	}
	
	@Override
	public void save(Book book) {
		HashMap<String, Book> allBooks = getAll();
		String isbn = book.getIsbn();
		allBooks.put(isbn, book);
		books = allBooks;
		DataAccess.saveToStorage(StorageType.BOOKS, allBooks);	
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
	public void loadEntityMap(List<Book> list) {
		books = new HashMap<String, Book>();
		list.forEach(book -> books.put(book.getIsbn(), book));
		DataAccess.saveToStorage(StorageType.BOOKS, books);
	}	
}