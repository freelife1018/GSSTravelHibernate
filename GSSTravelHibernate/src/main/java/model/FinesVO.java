package model;

public class FinesVO implements java.io.Serializable {

	private int fineDates;
	private float finePer;

	public FinesVO() {
	}

	public FinesVO(int fineDates, float finePer) {
		this.fineDates = fineDates;
		this.finePer = finePer;
	}

	public int getFineDates() {
		return this.fineDates;
	}

	public void setFineDates(int fineDates) {
		this.fineDates = fineDates;
	}

	public float getFinePer() {
		return this.finePer;
	}

	public void setFinePer(float finePer) {
		this.finePer = finePer;
	}

}
