package model;

public class TotalAmountIdVO implements java.io.Serializable {

	private String traNo;
	private int empNo;

	public TotalAmountIdVO() {
	}

	public TotalAmountIdVO(String traNo, int empNo) {
		this.traNo = traNo;
		this.empNo = empNo;
	}

	public String getTraNo() {
		return this.traNo;
	}

	public void setTraNo(String traNo) {
		this.traNo = traNo;
	}

	public int getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TotalAmountIdVO))
			return false;
		TotalAmountIdVO castOther = (TotalAmountIdVO) other;

		return ((this.getTraNo() == castOther.getTraNo()) || (this.getTraNo() != null && castOther.getTraNo() != null
				&& this.getTraNo().equals(castOther.getTraNo()))) && (this.getEmpNo() == castOther.getEmpNo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTraNo() == null ? 0 : this.getTraNo().hashCode());
		result = 37 * result + this.getEmpNo();
		return result;
	}

}
