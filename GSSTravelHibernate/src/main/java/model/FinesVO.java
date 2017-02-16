package model;

public class FinesVO implements java.io.Serializable {

	private int fineDates;
	private double finePer;

	public FinesVO() {
	}

	public FinesVO(int fineDates, double finePer) {
		this.fineDates = fineDates;
		this.finePer = finePer;
	}

	public int getFineDates() {
		return this.fineDates;
	}

	public void setFineDates(int fineDates) {
		this.fineDates = fineDates;
	}

	public double getFinePer() {
		return this.finePer;
	}

	public void setFinePer(double finePer) {
		this.finePer = finePer;
	}

}
