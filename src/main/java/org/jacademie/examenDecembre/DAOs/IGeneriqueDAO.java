package org.jacademie.examenDecembre.DAOs;

import java.io.Serializable;
import java.util.List;

public interface IGeneriqueDAO<T> {
	T getById(Serializable id);
	List<T> searchByName(String searchPattern);
	List<T> getAll();
	
	void save(T entity);
	void update(T entity);
	void delete(T entity);
}
