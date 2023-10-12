package com.jsp.study.dao;

import java.util.List;

import com.jsp.study.dto.EmpDto;

public interface EmpDao extends CRUDDao<EmpDto,Integer>{
	// EmpDto login(int empno, String pw);
	// login 서비스 이름 
	// findByEmpnoAndPw sql의 형태
	List<EmpDto> list (int page, int rows) throws Exception ;
	EmpDto findByEmpnoAndPw(int empno, String pw) throws Exception;
	void close();
	
}
