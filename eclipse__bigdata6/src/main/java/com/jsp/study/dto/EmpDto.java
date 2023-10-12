package com.jsp.study.dto;

import java.util.Date;

public class EmpDto {	// DataTransferObject == VariableObject == getter setter == Plan Old Java Object
	private int empno;
	private String ename;
	private String job;
	private Integer mgr;	// FK Emp.mepno(null허용)
	private Date hiredate;
	private float sal;	
	private Float comm;		// null 허용
	private Integer deptno;	// FK Emp.mepno(null허용)
	private DeptDto dept;	// N:1 = Emp:Dept (deptno)
	
	public DeptDto getDept() {
		return dept;
	}
	public void setDept(DeptDto dept) {
		this.dept = dept;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Integer getMgr() {
		return mgr;
	}
	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	public Float getComm() {
		return comm;
	}
	public void setComm(Float comm) {
		this.comm = comm;
	}
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	@Override
	public String toString() {
		return "{empno:" + empno + ", ename:" + ename + ", job:" + job + ", mgr:" + mgr + ", hiredate:" + hiredate
				+ ", sal:" + sal + ", comm:" + comm + ", deptno:" + deptno + " }";
	}
	
	
	

}
