package com.kennesaw.rewardsmanagementsystem.manager;

import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kennesaw.rewardsmanagementsystem.repo.Repository;
import com.kennesaw.rewardsmanagementsystem.to.CustomerInfo;
import com.kennesaw.rewardsmanagementsystem.to.ErrorResponse;
import com.kennesaw.rewardsmanagementsystem.to.PurchaseInfo;
import com.kennesaw.rewardsmanagementsystem.to.RewardsManagementResponse;
import com.kennesaw.rewardsmanagementsystem.util.Constants;

import io.micrometer.core.instrument.util.StringUtils;

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
			CustomerInfo monthlyPointsCustInfo = repo.getMonthlyPointsForCustomer(vipId);
			checkGoldStatus(customerInfo);
			//If customer has gold status, then double the earned points as per requirements
			if(customerInfo.getGoldStatusFlag().equals(Constants.goldStatus)) {
				int doubledMonthlyPoints = monthlyPointsCustInfo.getMonthlyPoints() * 2;
				customerInfo.setMonthlyPoints(doubledMonthlyPoints);
			}
			else {customerInfo.setMonthlyPoints(monthlyPointsCustInfo.getMonthlyPoints());}
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

	public String generateCustomerId(String firstName, String lastName) {
		String firstInitial = firstName.substring(0, 1);
		String lastInitial = lastName.substring(0, 1);
		Random random = new Random();
		String randomFourDigits = String.format("%04d", random.nextInt(10000));
		String vipId = firstInitial.toUpperCase() + "X" + lastInitial.toUpperCase() + randomFourDigits;
		return vipId;
	}

	public RewardsManagementResponse processCustomer(CustomerInfo customer) {
		RewardsManagementResponse response = new RewardsManagementResponse();
		LOGGER.info("Begin to process customer");
		//If customerId is empty/null, then generate ID and add customer
		//Else update customer information with given ID
		if(StringUtils.isEmpty(customer.getCustomerId())) {
			LOGGER.info("Adding new customer: " + customer.getFirstName() + " " + customer.getLastName());
			String newCustomerId = generateCustomerId(customer.getFirstName(), customer.getLastName());
			customer.setCustomerId(newCustomerId);
			try {
				response.setCustomerId(newCustomerId);
				response.setCustomerInfo(customer);
				repo.addNewCustomer(customer);
			}
			catch(SQLException e) {
				LOGGER.info("Exception occurred in processCustomer method during insertion: " + e.getMessage());
				response.setErrorResponse(getErrorResponse(Constants.CODE_SERVICE_ERROR, Constants.MESSAGE_SERVICE_ERROR));
			}
		}
		else {
			String customerId = customer.getCustomerId();
			LOGGER.info("Updating customer: " + customerId);
			try {
				response.setCustomerId(customerId);
				response.setCustomerInfo(customer);
				boolean result = repo.editCustomer(customer);
				if(!result) {response.setErrorResponse(getErrorResponse(Constants.CODE_SERVICE_ERROR, Constants.MESSAGE_SERVICE_ERROR));}
			}
			catch(SQLException e) {
				LOGGER.info("Exception occurred in processCustomer method during update: " + e.getMessage());
				response.setErrorResponse(getErrorResponse(Constants.CODE_SERVICE_ERROR, Constants.MESSAGE_SERVICE_ERROR));
			}
		}
		
		LOGGER.info("End customer processing");
		return response;
	}
	
	public RewardsManagementResponse deleteCustomer(String vipId) {
		RewardsManagementResponse response = new RewardsManagementResponse();
		response.setCustomerId(vipId);
		boolean result = repo.deleteCustomer(vipId);
		if(!result) {response.setErrorResponse(getErrorResponse(Constants.CODE_SERVICE_ERROR, Constants.MESSAGE_SERVICE_ERROR));}
		return response;
	}
	
	public RewardsManagementResponse generateDailyPurchaseReport() {
		RewardsManagementResponse response = new RewardsManagementResponse();
		try {
			PurchaseInfo purchaseInfo = repo.getDailyPurchases();
			response.setPurchaseInfo(purchaseInfo);
			if(purchaseInfo.getPurchasedItems().size()==0) {response.setErrorResponse(getErrorResponse(Constants.CODE_RESOURCE_NOT_AVAILABLE, Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));}
		}
		catch(SQLException e) {
			LOGGER.info("Exception occurred during generateDailyPurchaseReport: " + e.getMessage());
			response.setErrorResponse(getErrorResponse(Constants.CODE_SERVICE_ERROR, Constants.MESSAGE_SERVICE_ERROR));
		}
		
		return response;
	}
	
	

}
