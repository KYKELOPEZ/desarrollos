package com.tracing.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseGenericDAO<T, K extends Serializable> {

	T create(T entity);

	T update(T entity);

	void delete(T entity);
	
	void delete();

	T findById(K key);

	Long getMaxId();
	
	List<T> findAll();
	
	List<T> findAllOrderBy(String orderBy);
	
	List<T> findByFk(String cadena);
	
	List<Object> findByFkObject(String cadena);
        
        Long getMaxIdDos(String cadena);
        
        Long getCountId(String cadena);
        
     
        
        
}
