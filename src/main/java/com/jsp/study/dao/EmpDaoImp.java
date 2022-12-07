package com.jsp.study.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.jsp.study.dto.DeptDto;
import com.jsp.study.dto.EmpDto;

public class EmpDaoImp implements EmpDao{ // Model
	// 서비스가 무엇이 있을까?? CRUD
	// R : list (paging) -> detail 요청
	// R : detail -> updateForm 요청
	// R : upDateForm -> delete, update (요청)
	// R : insertForm -> Ajax로 empno,mgr 중복 및 참조 체크

	// D : delete
	// U : update
	// C : insert

	private String listsql = "SELECT * FROM EMP ORDER BY empno DESC LIMIT ?,?"; // 페이징도 하고 오더바이도 할꺼니까 (파라미터로 받을거면 ?)
	private String detailsql = "SELECT * FROM EMP LEFT JOIN DEPT USING(deptno) WHERE empno=?";
	private String updatesql = "UPDATE EMP SET ename=?, job=?, mgr=?, hiredate=?, sal=?, comm=?, deptno=? WHERE empno=? ";
	private String deleteSql = "DELETE FROM EMP WHERE empno=?";
	private String insertSql = "INSERT INTO EMP(empno,ename,job,mgr,hiredate,sal,comm,deptno) VALUES (?,?,?,?,?,?,?,?)";	// 순서대로 다 넣을 거라면 () 생략가능
	private String findByEmpnoAndPwSql = "SELECT * FROM EMP WHERE empno=? and pw=?";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public EmpDaoImp() throws Exception {
		conn = ScottDB.getConnection();
	}
	
	@Override
	public int insert(EmpDto emp) throws Exception{
		int insert =0;
		pstmt = conn.prepareStatement(insertSql);
		pstmt.setInt(1, emp.getEmpno());
		pstmt.setString(2, emp.getEname());
		pstmt.setString(3, emp.getJob());
		pstmt.setObject(4, emp.getMgr());
		pstmt.setDate(5, new java.sql.Date(emp.getHiredate().getTime()));
		pstmt.setFloat(6, emp.getSal());
		pstmt.setObject(7, emp.getComm());
		pstmt.setObject(8, emp.getDeptno());
		insert = pstmt.executeUpdate();
		return insert;
	}
	
	@Override
	public int delete(Integer empno) throws Exception {
		int delete = 0;
		pstmt = conn.prepareStatement(deleteSql);
		pstmt.setInt(1, empno);
		delete = pstmt.executeUpdate();
		return delete;
	}
	
	@Override
	public int update(EmpDto emp) throws Exception {
		int update = 0;
		pstmt = conn.prepareStatement(updatesql);
		pstmt.setString(1, emp.getEname());
		pstmt.setString(2, emp.getJob());
		pstmt.setObject(3, emp.getMgr());
		pstmt.setDate(4,new java.sql.Date(emp.getHiredate().getTime()));
		pstmt.setFloat(5, emp.getSal());
		pstmt.setObject(6, emp.getComm());
		pstmt.setObject(7, emp.getDeptno());
		pstmt.setInt(8, emp.getEmpno());
		update = pstmt.executeUpdate();
		return update;
	}
	
	// findByID or findOne or findByEmpno
	@Override
	public EmpDto detail(Integer empno) throws Exception {
		EmpDto emp = null;
		pstmt = conn.prepareStatement(detailsql);
		pstmt.setInt(1, empno);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			emp = new EmpDto();
			emp.setEmpno(rs.getInt("empno"));
			emp.setMgr((Integer) rs.getObject("mgr"));
			emp.setDeptno((Integer) rs.getObject("deptno"));
			emp.setEname(rs.getString("ename"));
			emp.setJob(rs.getString("job"));
			emp.setSal(rs.getFloat("sal"));
			emp.setComm((Float) rs.getObject("comm"));
			emp.setHiredate(rs.getDate("hiredate"));
			if (emp.getDeptno() != null) {
				DeptDto dept = new DeptDto();
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				emp.setDept(dept);
			}
		}
		return emp;
	}
	
	@Override
	public List<EmpDto> list(int page, int rows) throws Exception {
		int startRow = (page - 1) * rows;
		List<EmpDto> empList = null;
		pstmt = conn.prepareStatement(listsql);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, rows);
		rs = pstmt.executeQuery();
		empList = new ArrayList<EmpDto>();
		while (rs.next()) {
			EmpDto emp = new EmpDto();
			emp.setEmpno(rs.getInt("empno"));
			emp.setMgr((Integer) rs.getObject("mgr"));
			emp.setDeptno((Integer) rs.getObject("deptno"));
			emp.setEname(rs.getString("ename"));
			emp.setJob(rs.getString("job"));
			emp.setSal(rs.getFloat("sal"));
			emp.setComm((Float) rs.getObject("comm"));
			emp.setHiredate(rs.getDate("hiredate"));
			empList.add(emp);
		}
		return empList; // 오류가 뜨면 null로
		// Dao객체를 만들어서 list를 호출
	}
	
	@Override
	public void close() {
		try {
			if (rs != null)rs.close();
			if (pstmt != null)pstmt.close();
			if (conn != null)conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public EmpDto findByEmpnoAndPw(int empno, String pw) throws Exception{
		EmpDto emp =null;
		String sql = "SELECT * FROM EMP WHERE empno=? and pw=MD5(?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empno);
		pstmt.setString(2, pw);
		rs = pstmt.executeQuery();
		if (rs.next()) {		// 한번만 할 떄는 반복문 필요 없음
			emp = new EmpDto();
			emp.setEmpno(rs.getInt("empno"));
			emp.setMgr((Integer) rs.getObject("mgr"));
			emp.setDeptno((Integer) rs.getObject("deptno"));
			emp.setEname(rs.getString("ename"));
			emp.setJob(rs.getString("job"));
			emp.setSal(rs.getFloat("sal"));
			emp.setComm((Float) rs.getObject("comm"));
			emp.setHiredate(rs.getDate("hiredate"));
		}
		return emp;
	}
	
	public static void main(String[] args) {
		try {
			EmpDao empDao =new EmpDaoImp();
			System.out.println(empDao.findByEmpnoAndPw(7499, "1234"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public List<EmpDto> list() throws Exception {
		return null;
	}

	@Override
	public List<EmpDto> list(int page) throws Exception {
		return null;
	}
}
