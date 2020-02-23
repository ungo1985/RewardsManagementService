package com.kennesaw.rewardsmanagementsystem.to;

import java.util.List;

public class PurchaseInfo {
	
	private String customerId;
	private List<Purchase> purchasedItems;
	
	public PurchaseInfo(){}

	public PurchaseInfo(String customerId, List<Purchase> purchasedItems) {
		super();
		this.customerId = customerId;
		this.purchasedItems = purchasedItems;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<Purchase> getPurchasedItems() {
		return purchasedItems;
	}

	public void setPurchasedItems(List<Purchase> purchasedItems) {
		this.purchasedItems = purchasedItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((purchasedItems == null) ? 0 : purchasedItems.hashCode());
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
		PurchaseInfo other = (PurchaseInfo) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (purchasedItems == null) {
			if (other.purchasedItems != null)
				return false;
		} else if (!purchasedItems.equals(other.purchasedItems))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PurchaseInfo [customerId=" + customerId + ", purchasedItems=" + purchasedItems + "]";
	}

}
