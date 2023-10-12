package com.jsp.study.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jsp.study.dto.DeptDto;

public class DeptDaoImp implements DeptDao{
	// crud
	// R 리스트 페이징
	// R 상세
	// R 수정 폼
	// U 수정, D 삭제, C 등록
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String listSql = "SELECT * FROM DEPT";
	private String detailSql = "SELECT * FROM DEFT WHERE deptno=?";
	private String deleteSql = "DELETE FROM DEPT WHERE deptno=?";
	private String insertSql = "INSERT INTO DEPT (deptno,dname,loc) VALUES (?,?,?)";
	private String updateSql = "UPDATE DEPT SET dname=?, loc=? WHERE deptno=?";
	
	public DeptDaoImp() throws Exception{
		conn = ScottDB.getConnection();
	}
	
	public static void main(String[] args) {
		// test용 (서버 메인과는 별도의 jvm을 실행
		try {
//			System.out.println(new DeptDaoImp().list());
//			System.out.println(new DeptDaoImp().detail(30));
			DeptDto dept = new DeptDto();
			dept.setDeptno(40);
			dept.setDname("오퍼레이션");
			dept.setLoc("보스턴");
//			System.out.println(new DeptDaoImp().insert(dept));
//			System.out.println(new DeptDaoImp().delete(50));
			System.out.println(new DeptDaoImp().update(dept));
			System.out.println(new DeptDaoImp().list());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<DeptDto> list() throws Exception {
		List<DeptDto> list = null;
		pstmt = conn.prepareStatement(listSql);
		rs = pstmt.executeQuery();
		list = new ArrayList<DeptDto>();
		while (rs.next()) {
			DeptDto dept = new DeptDto();
			dept.setDeptno(rs.getInt("deptno"));
			dept.setDname(rs.getString("dname"));
			dept.setLoc(rs.getString("loc"));
			list.add(dept);
		}
		return list;
	}

	@Override
	public List<DeptDto> list(int page) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeptDto detail(Integer pk) throws Exception{
		DeptDto detail = null;
		pstmt = conn.prepareStatement(detailSql);
		pstmt.setInt(1, pk);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			detail = new DeptDto();
			detail.setDeptno(rs.getInt("deptno"));
			detail.setDname(rs.getString("dname"));
			detail.setLoc(rs.getString("loc"));
		}
		return detail;
	}

	@Override
	public int delete(Integer pk) throws Exception {
		int delete = 0;
		pstmt = conn.prepareStatement(deleteSql);
		pstmt.setInt(1, pk.intValue());
		delete = pstmt.executeUpdate();
		return delete;
	}

	@Override
	public int update(DeptDto dto) throws Exception {
		int update = 0;
		pstmt = conn.prepareStatement(updateSql);
		pstmt.setString(1, dto.getDname());
		pstmt.setString(2, dto.getLoc());
		pstmt.setInt(3, dto.getDeptno());
		update = pstmt.executeUpdate();
		
		return update;
	}

	@Override
	public int insert(DeptDto dto) throws Exception {
		int insert = 0;
		pstmt = conn.prepareStatement(insertSql);
		pstmt.setInt(1, dto.getDeptno());
		pstmt.setString(2, dto.getDname());
		pstmt.setString(3, dto.getLoc());
		insert = pstmt.executeUpdate();
		
		return insert;
	}

	@Override
	public void close() {
		try {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
