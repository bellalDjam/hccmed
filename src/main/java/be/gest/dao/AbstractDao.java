package be.gest.dao;

import java.util.List;


public interface AbstractDao<T> {
	
	public T update(T object);
	
	public void save(T object);
	
	public void delete(T object);
	
	public void deleteAll(Class<T> type);
	
	public T getById(Class<T> type, long id);
	
	public List<T> getAll(Class<T> type);

	
}
