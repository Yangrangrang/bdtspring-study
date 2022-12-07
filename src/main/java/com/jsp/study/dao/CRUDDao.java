package com.jsp.study.dao;

import java.util.List;

public interface CRUDDao<T,P> {
	public List<T> list() throws Exception;	// selectAll
	public List<T> list(int page) throws Exception;
	public T detail(P pk) throws Exception;
	public int delete(P pk) throws Exception;
	public int update(T dto) throws Exception;
	public int insert(T dto) throws Exception;
	
}
