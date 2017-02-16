package model;

public class TotalAmountVO implements java.io.Serializable {

	private TotalAmountIdVO id;
	private EmployeeVO employee;
	private TravelVO travel;
	private double taMoney;

	public TotalAmountVO() {
	}

	public TotalAmountVO(TotalAmountIdVO id, EmployeeVO employee, TravelVO travel, double taMoney) {
		this.id = id;
		this.employee = employee;
		this.travel = travel;
		this.taMoney = taMoney;
	}

	public TotalAmountIdVO getId() {
		return this.id;
	}

	public void setId(TotalAmountIdVO id) {
		this.id = id;
	}

	public EmployeeVO getEmployee() {
		return this.employee;
	}

	public void setEmployee(EmployeeVO employee) {
		this.employee = employee;
	}

	public TravelVO getTravel() {
		return this.travel;
	}

	public void setTravel(TravelVO travel) {
		this.travel = travel;
	}

	public double getTaMoney() {
		return this.taMoney;
	}

	public void setTaMoney(double taMoney) {
		this.taMoney = taMoney;
	}

}
