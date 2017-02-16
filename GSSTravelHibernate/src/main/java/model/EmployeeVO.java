package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EmployeeVO implements java.io.Serializable {

	private int empNo;
	private DepartmentVO department;
	private String empName;
	private String empPhone;
	private String empId;
	private String empSex;
	private Date empBdate;
	private String empEat;
	private String empEmg;
	private String empEmgPhone;
	private String empEmgRel;
	private java.sql.Date empHireDate;
	private boolean empSub;
	private byte[] empPw;
	private String empBen;
	private String empBenRel;
	private String empNote;
	private String empMail;
	private boolean empRole;
	private String empSubTra;
	private Set<DetailVO> details = new HashSet<DetailVO>(0);
	private Set<FamilyVO> families = new HashSet<FamilyVO>(0);
	private Set<TotalAmountVO> totalAmounts = new HashSet<TotalAmountVO>(0);

	public EmployeeVO() {
	}
	public EmployeeVO(int emp_No) {
		this.empNo=emp_No;
	}

	public EmployeeVO(int empNo, DepartmentVO department, String empName, String empPhone, String empId, String empSex,
			Date empBdate, String empEat, String empEmg, String empEmgPhone, String empEmgRel, java.sql.Date empHireDate,
			boolean empSub, byte[] empPw, String empBen, String empBenRel, String empMail,
			boolean empRole) {
		this.empNo = empNo;
		this.department = department;
		this.empName = empName;
		this.empPhone = empPhone;
		this.empId = empId;
		this.empSex = empSex;
		this.empBdate = empBdate;
		this.empEat = empEat;
		this.empEmg = empEmg;
		this.empEmgPhone = empEmgPhone;
		this.empEmgRel = empEmgRel;
		this.empHireDate = empHireDate;
		this.empSub = empSub;
		this.empPw = empPw;
		this.empBen = empBen;
		this.empBenRel = empBenRel;
		this.empMail = empMail;
		this.empRole = empRole;
	}

	public EmployeeVO(int empNo, DepartmentVO department, String empName, String empPhone, String empId, String empSex,
			Date empBdate, String empEat, String empEmg, String empEmgPhone, String empEmgRel, java.sql.Date empHireDate,
			boolean empSub, byte[] empPw, String empBen, String empBenRel, String empNote,
			String empMail, boolean empRole, String empSubTra, Set<DetailVO> details, Set<FamilyVO> families,
			Set<TotalAmountVO> totalAmounts) {
		this.empNo = empNo;
		this.department = department;
		this.empName = empName;
		this.empPhone = empPhone;
		this.empId = empId;
		this.empSex = empSex;
		this.empBdate = empBdate;
		this.empEat = empEat;
		this.empEmg = empEmg;
		this.empEmgPhone = empEmgPhone;
		this.empEmgRel = empEmgRel;
		this.empHireDate = empHireDate;
		this.empSub = empSub;
		this.empPw = empPw;
		this.empBen = empBen;
		this.empBenRel = empBenRel;
		this.empNote = empNote;
		this.empMail = empMail;
		this.empRole = empRole;
		this.empSubTra = empSubTra;
		this.details = details;
		this.families = families;
		this.totalAmounts = totalAmounts;
	}

	public int getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public DepartmentVO getDepartment() {
		return this.department;
	}

	public void setDepartment(DepartmentVO department) {
		this.department = department;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPhone() {
		return this.empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpSex() {
		return this.empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public Date getEmpBdate() {
		return this.empBdate;
	}

	public void setEmpBdate(Date empBdate) {
		this.empBdate = empBdate;
	}

	public String getEmpEat() {
		return this.empEat;
	}

	public void setEmpEat(String empEat) {
		this.empEat = empEat;
	}

	public String getEmpEmg() {
		return this.empEmg;
	}

	public void setEmpEmg(String empEmg) {
		this.empEmg = empEmg;
	}

	public String getEmpEmgPhone() {
		return this.empEmgPhone;
	}

	public void setEmpEmgPhone(String empEmgPhone) {
		this.empEmgPhone = empEmgPhone;
	}

	public String getEmpEmgRel() {
		return this.empEmgRel;
	}

	public void setEmpEmgRel(String empEmgRel) {
		this.empEmgRel = empEmgRel;
	}

	public java.sql.Date getEmpHireDate() {
		return this.empHireDate;
	}

	public void setEmpHireDate(java.sql.Date empHireDate) {
		this.empHireDate = empHireDate;
	}

	public boolean isEmpSub() {
		return this.empSub;
	}

	public void setEmpSub(boolean empSub) {
		this.empSub = empSub;
	}

	public byte[] getEmpPw() {
		return this.empPw;
	}

	public void setEmpPw(byte[] empPw) {
		this.empPw = empPw;
	}

	public String getEmpBen() {
		return this.empBen;
	}

	public void setEmpBen(String empBen) {
		this.empBen = empBen;
	}

	public String getEmpBenRel() {
		return this.empBenRel;
	}

	public void setEmpBenRel(String empBenRel) {
		this.empBenRel = empBenRel;
	}

	public String getEmpNote() {
		return this.empNote;
	}

	public void setEmpNote(String empNote) {
		this.empNote = empNote;
	}

	public String getEmpMail() {
		return this.empMail;
	}

	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}

	public boolean isEmpRole() {
		return this.empRole;
	}

	public void setEmpRole(boolean empRole) {
		this.empRole = empRole;
	}

	public String getEmpSubTra() {
		return this.empSubTra;
	}

	public void setEmpSubTra(String empSubTra) {
		this.empSubTra = empSubTra;
	}

	public Set<DetailVO> getDetails() {
		return this.details;
	}

	public void setDetails(Set<DetailVO> details) {
		this.details = details;
	}

	public Set<FamilyVO> getFamilies() {
		return this.families;
	}

	public void setFamilies(Set<FamilyVO> families) {
		this.families = families;
	}

	public Set<TotalAmountVO> getTotalAmounts() {
		return this.totalAmounts;
	}

	public void setTotalAmounts(Set<TotalAmountVO> totalAmounts) {
		this.totalAmounts = totalAmounts;
	}

}
