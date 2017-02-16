package model;

public class ItemVO implements java.io.Serializable {

	private ItemIdVO id;
	private TravelVO travel;
	private String itemName;
	private double itemMoney;

	public ItemVO() {
	}

	public ItemVO(ItemIdVO id, TravelVO travel, String itemName, double itemMoney) {
		this.id = id;
		this.travel = travel;
		this.itemName = itemName;
		this.itemMoney = itemMoney;
	}

	public ItemIdVO getId() {
		return this.id;
	}

	public void setId(ItemIdVO id) {
		this.id = id;
	}

	public TravelVO getTravel() {
		return this.travel;
	}

	public void setTravel(TravelVO travel) {
		this.travel = travel;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemMoney() {
		return this.itemMoney;
	}

	public void setItemMoney(double itemMoney) {
		this.itemMoney = itemMoney;
	}

}
