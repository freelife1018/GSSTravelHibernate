package model;

public class DetailVO implements java.io.Serializable {

	private Integer detNo;
	private EmployeeVO employee;
	private FamilyVO family;
	private TravelVO travel;
	private java.sql.Timestamp detDate;
	private java.sql.Timestamp detCanDate;
	private double detMoney;
	private String detNote;
	private Double detNoteMoney;

	public DetailVO() {
	}

	public DetailVO(EmployeeVO employee, TravelVO travel, java.sql.Timestamp detDate, double detMoney) {
		this.employee = employee;
		this.travel = travel;
		this.detDate = detDate;
		this.detMoney = detMoney;
	}

	public DetailVO(EmployeeVO employee, FamilyVO family, TravelVO travel, java.sql.Timestamp detDate, java.sql.Timestamp detCanDate, double detMoney,
			String detNote, Double detNoteMoney) {
		this.employee = employee;
		this.family = family;
		this.travel = travel;
		this.detDate = detDate;
		this.detCanDate = detCanDate;
		this.detMoney = detMoney;
		this.detNote = detNote;
		this.detNoteMoney = detNoteMoney;
	}

	public Integer getDetNo() {
		return this.detNo;
	}

	public void setDetNo(Integer detNo) {
		this.detNo = detNo;
	}

	public EmployeeVO getEmployee() {
		return this.employee;
	}

	public void setEmployee(EmployeeVO employee) {
		this.employee = employee;
	}

	public FamilyVO getFamily() {
		return this.family;
	}

	public void setFamily(FamilyVO family) {
		this.family = family;
	}

	public TravelVO getTravel() {
		return this.travel;
	}

	public void setTravel(TravelVO travel) {
		this.travel = travel;
	}

	public java.sql.Timestamp getDetDate() {
		return this.detDate;
	}

	public void setDetDate(java.sql.Timestamp detDate) {
		this.detDate = detDate;
	}

	public java.sql.Timestamp getDetCanDate() {
		return this.detCanDate;
	}

	public void setDetCanDate(java.sql.Timestamp detCanDate) {
		this.detCanDate = detCanDate;
	}

	public double getDetMoney() {
		return this.detMoney;
	}

	public void setDetMoney(double detMoney) {
		this.detMoney = detMoney;
	}

	public String getDetNote() {
		return this.detNote;
	}

	public void setDetNote(String detNote) {
		this.detNote = detNote;
	}

	public Double getDetNoteMoney() {
		return this.detNoteMoney;
	}

	public void setDetNoteMoney(Double detNoteMoney) {
		this.detNoteMoney = detNoteMoney;
	}

}
