package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FamilyVO implements java.io.Serializable {

	private Integer famNo;
	private EmployeeVO employee;
	private String famName;
	private String famRel;
	private Date famBdate;
	private String famSex;
	private String famEat;
	private String famId;
	private String famPhone;
	private String famNote;
	private String famBen;
	private String famBenRel;
	private boolean famCar;
	private String famEmg;
	private String famEmgPhone;
	private String famEmgRel;
	private boolean famBady;
	private boolean famKid;
	private boolean famDis;
	private boolean famMom;
	private Set<DetailVO> details = new HashSet<DetailVO>(0);
	private String checked;
	
	public FamilyVO() {
	}
	public FamilyVO(String fam_No) {
		this.famNo=Integer.parseInt(fam_No);
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public FamilyVO(EmployeeVO employee, String famName, String famRel, Date famBdate, String famSex, String famEat,
			String famId, String famPhone, String famBen, String famBenRel, boolean famCar, String famEmg,
			String famEmgPhone, String famEmgRel, boolean famBady, boolean famKid, boolean famDis,
			boolean famMom) {
		this.employee = employee;
		this.famName = famName;
		this.famRel = famRel;
		this.famBdate = famBdate;
		this.famSex = famSex;
		this.famEat = famEat;
		this.famId = famId;
		this.famPhone = famPhone;
		this.famBen = famBen;
		this.famBenRel = famBenRel;
		this.famCar = famCar;
		this.famEmg = famEmg;
		this.famEmgPhone = famEmgPhone;
		this.famEmgRel = famEmgRel;
		this.famBady = famBady;
		this.famKid = famKid;
		this.famDis = famDis;
		this.famMom = famMom;
	}

	public FamilyVO(EmployeeVO employee, String famName, String famRel, Date famBdate, String famSex, String famEat,
			String famId, String famPhone, String famNote, String famBen, String famBenRel, boolean famCar,
			String famEmg, String famEmgPhone, String famEmgRel, boolean famBady, boolean famKid, boolean famDis,
			boolean famMom, Set<DetailVO> details) {
		this.employee = employee;
		this.famName = famName;
		this.famRel = famRel;
		this.famBdate = famBdate;
		this.famSex = famSex;
		this.famEat = famEat;
		this.famId = famId;
		this.famPhone = famPhone;
		this.famNote = famNote;
		this.famBen = famBen;
		this.famBenRel = famBenRel;
		this.famCar = famCar;
		this.famEmg = famEmg;
		this.famEmgPhone = famEmgPhone;
		this.famEmgRel = famEmgRel;
		this.famBady = famBady;
		this.famKid = famKid;
		this.famDis = famDis;
		this.famMom = famMom;
		this.details = details;
	}

	public Integer getFamNo() {
		return this.famNo;
	}

	public void setFamNo(Integer famNo) {
		this.famNo = famNo;
	}

	public EmployeeVO getEmployee() {
		return this.employee;
	}

	public void setEmployee(EmployeeVO employee) {
		this.employee = employee;
	}

	public String getFamName() {
		return this.famName;
	}

	public void setFamName(String famName) {
		this.famName = famName;
	}

	public String getFamRel() {
		return this.famRel;
	}

	public void setFamRel(String famRel) {
		this.famRel = famRel;
	}

	public Date getFamBdate() {
		return this.famBdate;
	}

	public void setFamBdate(Date famBdate) {
		this.famBdate = famBdate;
	}

	public String getFamSex() {
		return this.famSex;
	}

	public void setFamSex(String famSex) {
		this.famSex = famSex;
	}

	public String getFamEat() {
		return this.famEat;
	}

	public void setFamEat(String famEat) {
		this.famEat = famEat;
	}

	public String getFamId() {
		return this.famId;
	}

	public void setFamId(String famId) {
		this.famId = famId;
	}

	public String getFamPhone() {
		return this.famPhone;
	}

	public void setFamPhone(String famPhone) {
		this.famPhone = famPhone;
	}

	public String getFamNote() {
		return this.famNote;
	}

	public void setFamNote(String famNote) {
		this.famNote = famNote;
	}

	public String getFamBen() {
		return this.famBen;
	}

	public void setFamBen(String famBen) {
		this.famBen = famBen;
	}

	public String getFamBenRel() {
		return this.famBenRel;
	}

	public void setFamBenRel(String famBenRel) {
		this.famBenRel = famBenRel;
	}

	public boolean isFamCar() {
		return this.famCar;
	}

	public void setFamCar(boolean famCar) {
		this.famCar = famCar;
	}

	public String getFamEmg() {
		return this.famEmg;
	}

	public void setFamEmg(String famEmg) {
		this.famEmg = famEmg;
	}

	public String getFamEmgPhone() {
		return this.famEmgPhone;
	}

	public void setFamEmgPhone(String famEmgPhone) {
		this.famEmgPhone = famEmgPhone;
	}

	public String getFamEmgRel() {
		return this.famEmgRel;
	}

	public void setFamEmgRel(String famEmgRel) {
		this.famEmgRel = famEmgRel;
	}

	public boolean isFamBady() {
		return this.famBady;
	}

	public void setFamBady(boolean famBady) {
		this.famBady = famBady;
	}

	public boolean isFamKid() {
		return this.famKid;
	}

	public void setFamKid(boolean famKid) {
		this.famKid = famKid;
	}

	public boolean isFamDis() {
		return this.famDis;
	}

	public void setFamDis(boolean famDis) {
		this.famDis = famDis;
	}

	public boolean isFamMom() {
		return this.famMom;
	}

	public void setFamMom(boolean famMom) {
		this.famMom = famMom;
	}

	public Set<DetailVO> getDetails() {
		return this.details;
	}

	public void setDetails(Set<DetailVO> details) {
		this.details = details;
	}

}
