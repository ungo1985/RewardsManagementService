package com.kennesaw.rewardsmanagementsystem.to;

public class RewardsManagementResponse {
	
	private String customerId;
	private CustomerInfo customerInfo;
	private PurchaseInfo purchaseInfo;
	private ErrorResponse errorResponse;
	
	public RewardsManagementResponse() {}

	public RewardsManagementResponse(String customerId, CustomerInfo customerInfo, PurchaseInfo purchaseInfo,
			ErrorResponse errorResponse) {
		super();
		this.customerId = customerId;
		this.customerInfo = customerInfo;
		this.purchaseInfo = purchaseInfo;
		this.errorResponse = errorResponse;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public PurchaseInfo getPurchaseInfo() {
		return purchaseInfo;
	}

	public void setPurchaseInfo(PurchaseInfo purchaseInfo) {
		this.purchaseInfo = purchaseInfo;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
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
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((customerInfo == null) ? 0 : customerInfo.hashCode());
		result = prime * result + ((errorResponse == null) ? 0 : errorResponse.hashCode());
		result = prime * result + ((purchaseInfo == null) ? 0 : purchaseInfo.hashCode());
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
		RewardsManagementResponse other = (RewardsManagementResponse) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerInfo == null) {
			if (other.customerInfo != null)
				return false;
		} else if (!customerInfo.equals(other.customerInfo))
			return false;
		if (errorResponse == null) {
			if (other.errorResponse != null)
				return false;
		} else if (!errorResponse.equals(other.errorResponse))
			return false;
		if (purchaseInfo == null) {
			if (other.purchaseInfo != null)
				return false;
		} else if (!purchaseInfo.equals(other.purchaseInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RewardsManagementResponse [customerId=" + customerId + ", customerInfo=" + customerInfo
				+ ", purchaseInfo=" + purchaseInfo + ", errorResponse=" + errorResponse + "]";
	}

}
