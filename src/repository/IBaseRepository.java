package repository;

import java.util.HashMap;
import java.util.List;

public interface IBaseRepository<T> {
	
	public T search(String searchString);
	
	public void save(T obj);

	public HashMap<?,T> getAll();

	public void loadEntityMap(List<T> list);
}