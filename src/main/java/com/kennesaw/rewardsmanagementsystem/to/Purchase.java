package com.kennesaw.rewardsmanagementsystem.to;

import java.sql.Date;

public class Purchase {

	private String availableItem;
	private Float price;
	private String type;
	private Date purchasedDate;
	private String preOrderedFlag;
	private String customerId;
	
	public Purchase(){}

	public Purchase(String availableItem, Float price, String type, Date purchasedDate, String preOrderedFlag) {
		super();
		this.availableItem = availableItem;
		this.price = price;
		this.type = type;
		this.purchasedDate = purchasedDate;
		this.preOrderedFlag = preOrderedFlag;
	}
	
	public Purchase(String availableItem, Float price, String type, Date purchasedDate, String preOrderedFlag, String customerId) {
		super();
		this.availableItem = availableItem;
		this.price = price;
		this.type = type;
		this.purchasedDate = purchasedDate;
		this.preOrderedFlag = preOrderedFlag;
		this.customerId = customerId;
	}

	public String getAvailableItem() {
		return availableItem;
	}

	public void setAvailableItem(String availableItem) {
		this.availableItem = availableItem;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public String getPreOrderedFlag() {
		return preOrderedFlag;
	}

	public void setPreOrderedFlag(String preOrderedFlag) {
		this.preOrderedFlag = preOrderedFlag;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((availableItem == null) ? 0 : availableItem.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((preOrderedFlag == null) ? 0 : preOrderedFlag.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((purchasedDate == null) ? 0 : purchasedDate.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		if (availableItem == null) {
			if (other.availableItem != null)
				return false;
		} else if (!availableItem.equals(other.availableItem))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (preOrderedFlag == null) {
			if (other.preOrderedFlag != null)
				return false;
		} else if (!preOrderedFlag.equals(other.preOrderedFlag))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (purchasedDate == null) {
			if (other.purchasedDate != null)
				return false;
		} else if (!purchasedDate.equals(other.purchasedDate))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Purchase [availableItem=" + availableItem + ", price=" + price + ", type=" + type + ", purchasedDate="
				+ purchasedDate + ", preOrderedFlag=" + preOrderedFlag + ", customerId=" + customerId + "]";
	}
}
