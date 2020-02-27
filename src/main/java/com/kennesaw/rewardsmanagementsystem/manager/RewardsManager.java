package com.kennesaw.rewardsmanagementsystem.manager;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kennesaw.rewardsmanagementsystem.repo.Repository;
import com.kennesaw.rewardsmanagementsystem.to.CustomerInfo;
import com.kennesaw.rewardsmanagementsystem.to.ErrorResponse;
import com.kennesaw.rewardsmanagementsystem.to.PurchaseInfo;
import com.kennesaw.rewardsmanagementsystem.to.RewardsManagementResponse;
import com.kennesaw.rewardsmanagementsystem.util.Constants;

@Component
public class RewardsManager {
	
	private static final Logger LOGGER = Logger.getLogger(RewardsManager.class.getName());
	
	@Autowired
	Repository repo;

	public RewardsManagementResponse retrieveCustomerAndPurchaseInfo(String vipId) {
		RewardsManagementResponse response = new RewardsManagementResponse();
		response.setCustomerId(vipId);
		
		try {
			CustomerInfo customerInfo = repo.getCustomerInformation(vipId);
			checkGoldStatus(customerInfo);
			PurchaseInfo purchaseInfo = repo.getPurchaseInfo(vipId);
			response.setCustomerInfo(customerInfo);
			response.setPurchaseInfo(purchaseInfo);
			
			if(customerInfo.getFirstName() == null && purchaseInfo.getPurchasedItems().isEmpty()) {
				response.setErrorResponse(getErrorResponse(Constants.CODE_RESOURCE_NOT_AVAILABLE, Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));
			}
		}
		catch(SQLException e) {
			LOGGER.info("Exception occurred in retrieveCustomerAndPurchaseInfo method " + e.getClass());
			response.setErrorResponse(getErrorResponse(Constants.CODE_SERVICE_ERROR, Constants.MESSAGE_SERVICE_ERROR));
		}
		
		return response;
	}
	
	public ErrorResponse getErrorResponse(int code, String message) {
		return new ErrorResponse(code, message);
	}
	
	public void checkGoldStatus(CustomerInfo customer) {
		if(customer.getPoints() >= Constants.goldStatusThreshold) {
			customer.setGoldStatusFlag(Constants.goldStatus);
		}
	}
	
	

}
