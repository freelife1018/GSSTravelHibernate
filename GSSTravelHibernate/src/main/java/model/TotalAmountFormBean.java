package model;

import java.sql.Date;

public class TotalAmountFormBean {
	private String deptNo;
	private int detNo;
	private int empNo;
	private int famNo;
	private String traName;
	private String traNo;
	private Date detDate;
	private Date detCanDate;
	private float detMoney;
	private String empName;
	private String famName;
	private String detNote;
	private float detNoteMoney;
	private boolean empSub;
	private boolean famRel;
	private double YearMoney;
	private String empSubTra;
	private boolean famSub;

	public TotalAmountFormBean() {
	}

	public TotalAmountFormBean(String deptNo, int detNo, int empNo, int famNo, String traName, String traNo,
			Date detDate, Date detCanDate, float detMoney, String empName, String famName, String detNote,
			float detNoteMoney, boolean empSub, boolean famRel) {
		this.detNo = detNo;
		this.empNo = empNo;
		this.famNo = famNo;
		this.traName = traName;
		this.traNo = traNo;
		this.detDate = detDate;
		this.detCanDate = detCanDate;
		this.detMoney = detMoney;
		this.empName = empName;
		this.famName = famName;
		this.detNote = detNote;
		this.detNoteMoney = detNoteMoney;
		this.empSub = empSub;
		this.famRel = famRel;

	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public int getDetNo() {
		return detNo;
	}

	public void setDetNo(int detNo) {
		this.detNo = detNo;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getFamNo() {
		return famNo;
	}

	public void setFamNo(int famNo) {
		this.famNo = famNo;
	}

	public String getTraName() {
		return traName;
	}

	public void setTraName(String traName) {
		this.traName = traName;
	}

	public String getTraNo() {
		return traNo;
	}

	public void setTraNo(String traNo) {
		this.traNo = traNo;
	}

	public Date getDetDate() {
		return detDate;
	}

	public void setDetDate(Date detDate) {
		this.detDate = detDate;
	}

	public Date getDetCanDate() {
		return detCanDate;
	}

	public void setDetCanDate(Date detCanDate) {
		this.detCanDate = detCanDate;
	}

	public float getDetMoney() {
		return detMoney;
	}

	public void setDetMoney(float detMoney) {
		this.detMoney = detMoney;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getFamName() {
		return famName;
	}

	public void setFamName(String famName) {
		this.famName = famName;
	}

	public String getDetNote() {
		return detNote;
	}

	public void setDetNote(String detNote) {
		this.detNote = detNote;
	}

	public float getDetNoteMoney() {
		return detNoteMoney;
	}

	public void setDetNoteMoney(float detNoteMoney) {
		this.detNoteMoney = detNoteMoney;
	}

	public boolean getEmpSub() {
		return empSub;
	}

	public void setEmpSub(boolean empSub) {
		this.empSub = empSub;
	}

	public boolean getFamRel() {
		return famRel;
	}

	public void setFamRel(boolean famRel) {
		this.famRel = famRel;
	}

	public String getEmpSubTra() {
		return empSubTra;
	}

	public void setEmpSubTra(String empSubTra) {
		this.empSubTra = empSubTra;
	}

	
	public double getYearMoney() {
		return YearMoney;
	}

	public void setYearMoney(double yearMoney) {
		YearMoney = yearMoney;
	}
	public boolean getFamSub() {
		return famSub;
	}

	public void setFamSub(boolean famSub) {
		this.famSub = famSub;
	}

}
