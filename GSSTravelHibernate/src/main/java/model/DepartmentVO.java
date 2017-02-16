package model;

import java.util.HashSet;
import java.util.Set;

public class DepartmentVO implements java.io.Serializable {

	private String deptNo;
	private String deptName;
	private Set<EmployeeVO> employees = new HashSet<EmployeeVO>(0);

	public DepartmentVO() {
	}

	public DepartmentVO(String deptNo, String deptName) {
		this.deptNo = deptNo;
		this.deptName = deptName;
	}

	public DepartmentVO(String deptNo, String deptName, Set<EmployeeVO> employees) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.employees = employees;
	}

	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<EmployeeVO> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<EmployeeVO> employees) {
		this.employees = employees;
	}

}
