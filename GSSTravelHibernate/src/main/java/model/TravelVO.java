package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class TravelVO implements java.io.Serializable {

	private String traNo;
	private String traName;
	private String traLoc;
	private java.sql.Date traOn;
	private java.sql.Date traOff;
	private Date traBeg;
	private Date traEnd;
	private int traTotal;
	private int traMax;
	private String traIntr;
	private String traCon;
	private String traAtter;
	private String traFile;
	private Set<TotalAmountVO> totalAmounts = new HashSet<TotalAmountVO>(0);
	private Set<ItemVO> items = new HashSet<ItemVO>(0);
	private Set<DetailVO> details = new HashSet<DetailVO>(0);
	private int signInTotal;
	public TravelVO() {
	}
	public TravelVO(String tra_No) {
		this.traNo=tra_No;
	}

	public TravelVO(String traNo, String traName, String traLoc, java.sql.Date traOn, java.sql.Date traOff, Date traBeg, Date traEnd,
			int traTotal, int traMax, String traIntr, String traCon, String traAtter) {
		this.traNo = traNo;
		this.traName = traName;
		this.traLoc = traLoc;
		this.traOn = traOn;
		this.traOff = traOff;
		this.traBeg = traBeg;
		this.traEnd = traEnd;
		this.traTotal = traTotal;
		this.traMax = traMax;
		this.traIntr = traIntr;
		this.traCon = traCon;
		this.traAtter = traAtter;
	}

	public TravelVO(String traNo, String traName, String traLoc, java.sql.Date traOn, java.sql.Date traOff, Date traBeg, Date traEnd,
			int traTotal, int traMax, String traIntr, String traCon, String traAtter,
			String traFile, Set<TotalAmountVO> totalAmounts, Set<ItemVO> items, Set<DetailVO> details) {
		this.traNo = traNo;
		this.traName = traName;
		this.traLoc = traLoc;
		this.traOn = traOn;
		this.traOff = traOff;
		this.traBeg = traBeg;
		this.traEnd = traEnd;
		this.traTotal = traTotal;
		this.traMax = traMax;
		this.traIntr = traIntr;
		this.traCon = traCon;
		this.traAtter = traAtter;
		this.traFile = traFile;
		this.totalAmounts = totalAmounts;
		this.items = items;
		this.details = details;
	}

	public int getSignInTotal() {
		return signInTotal;
	}

	public void setSignInTotal(int signInTotal) {
		this.signInTotal = signInTotal;
	}

	public String getTraNo() {
		return this.traNo;
	}

	public void setTraNo(String traNo) {
		this.traNo = traNo;
	}

	public String getTraName() {
		return this.traName;
	}

	public void setTraName(String traName) {
		this.traName = traName;
	}

	public String getTraLoc() {
		return this.traLoc;
	}

	public void setTraLoc(String traLoc) {
		this.traLoc = traLoc;
	}

	public java.sql.Date getTraOn() {
		return this.traOn;
	}

	public void setTraOn(java.sql.Date traOn) {
		this.traOn = traOn;
	}

	public java.sql.Date getTraOff() {
		return this.traOff;
	}

	public void setTraOff(java.sql.Date traOff) {
		this.traOff = traOff;
	}

	public Date getTraBeg() {
		return this.traBeg;
	}

	public void setTraBeg(Date traBeg) {
		this.traBeg = traBeg;
	}

	public Date getTraEnd() {
		return this.traEnd;
	}

	public void setTraEnd(Date traEnd) {
		this.traEnd = traEnd;
	}

	public int getTraTotal() {
		return this.traTotal;
	}

	public void setTraTotal(int traTotal) {
		this.traTotal = traTotal;
	}

	public int getTraMax() {
		return this.traMax;
	}

	public void setTraMax(int traMax) {
		this.traMax = traMax;
	}

	public String getTraIntr() {
		return this.traIntr;
	}

	public void setTraIntr(String traIntr) {
		this.traIntr = traIntr;
	}

	public String getTraCon() {
		return this.traCon;
	}

	public void setTraCon(String traCon) {
		this.traCon = traCon;
	}

	public String getTraAtter() {
		return this.traAtter;
	}

	public void setTraAtter(String traAtter) {
		this.traAtter = traAtter;
	}

	public String getTraFile() {
		return this.traFile;
	}

	public void setTraFile(String traFile) {
		this.traFile = traFile;
	}

	public Set<TotalAmountVO> getTotalAmounts() {
		return this.totalAmounts;
	}

	public void setTotalAmounts(Set<TotalAmountVO> totalAmounts) {
		this.totalAmounts = totalAmounts;
	}

	public Set<ItemVO> getItems() {
		return this.items;
	}

	public void setItems(Set<ItemVO> items) {
		this.items = items;
	}

	public Set<DetailVO> getDetails() {
		return this.details;
	}

	public void setDetails(Set<DetailVO> details) {
		this.details = details;
	}

}
