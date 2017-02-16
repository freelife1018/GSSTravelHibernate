package model;

public class ItemIdVO implements java.io.Serializable {

	private int itemNo;
	private String traNo;

	public ItemIdVO() {
	}

	public ItemIdVO(int itemNo, String traNo) {
		this.itemNo = itemNo;
		this.traNo = traNo;
	}

	public int getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getTraNo() {
		return this.traNo;
	}

	public void setTraNo(String traNo) {
		this.traNo = traNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ItemIdVO))
			return false;
		ItemIdVO castOther = (ItemIdVO) other;

		return (this.getItemNo() == castOther.getItemNo())
				&& ((this.getTraNo() == castOther.getTraNo()) || (this.getTraNo() != null
						&& castOther.getTraNo() != null && this.getTraNo().equals(castOther.getTraNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getItemNo();
		result = 37 * result + (getTraNo() == null ? 0 : this.getTraNo().hashCode());
		return result;
	}

}
