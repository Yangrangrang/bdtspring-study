package com.jsp.study.dao;

import java.util.List;

import com.jsp.study.dto.DeptDto;

// 인터페이스를 사용하는 이유
// 1. 선입 개발자가 설계한 내역 -> 구현
// 2. 타입의 재사용
// 3. 추상화를 이용한 라이브러리 이용, mybaits,jpa


public interface DeptDao extends CRUDDao<DeptDto, Integer>{
	
	void close();
}
