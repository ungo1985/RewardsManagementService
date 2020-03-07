package integrationtest.com.kennesaw.rewardsmanagementsystem.manager;

import java.sql.Date;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kennesaw.rewardsmanagementsystem.Application;
import com.kennesaw.rewardsmanagementsystem.manager.RewardsManager;
import com.kennesaw.rewardsmanagementsystem.to.CustomerInfo;
import com.kennesaw.rewardsmanagementsystem.to.RewardsManagementResponse;
import com.kennesaw.rewardsmanagementsystem.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardsManagerIntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(RewardsManagerIntegrationTest.class.getName());
	
	@Autowired
	RewardsManager manager;
	
	@Test
	public void test_retrieveCustomerAndPurchaseInfo_Success() {
		String vipId = "EXA6777";
		RewardsManagementResponse response = manager.retrieveCustomerAndPurchaseInfo(vipId);
		LOGGER.info("test_retrieveCustomerAndPurchaseInfo_Success: " + response.getCustomerInfo().toString() + " purchase info: " + response.getPurchaseInfo().toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertNotNull(response.getCustomerInfo().getCustomerId());
		Assert.assertTrue(response.getCustomerInfo().getCustomerId().equalsIgnoreCase(vipId));
		Assert.assertNotNull(response.getPurchaseInfo());
		Assert.assertNotNull(response.getPurchaseInfo().getPurchasedItems());
		Assert.assertTrue(response.getPurchaseInfo().getPurchasedItems().size()>0);
		Assert.assertNull(response.getErrorResponse());
	}
	
	@Test
	public void test_retrieveCustomerAndPurchaseInfo_Failure() {
		String vipId = "TEST";
		RewardsManagementResponse response = manager.retrieveCustomerAndPurchaseInfo(vipId);
		LOGGER.info("test_retrieveCustomerAndPurchaseInfo_Failure: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getErrorResponse());
		Assert.assertTrue(response.getErrorResponse().getCode()==Constants.CODE_RESOURCE_NOT_AVAILABLE);
		Assert.assertTrue(response.getErrorResponse().getMessage().equals(Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));
	}
	
	@Test
	public void test_generateVipId() {
		String firstName = "Mariana";
		String lastName = "Sayago";
		String generatedVipId = manager.generateCustomerId(firstName, lastName);
		LOGGER.info("test_generateVipId: " + generatedVipId);
		Assert.assertTrue(!generatedVipId.isEmpty());
		Assert.assertTrue(generatedVipId.startsWith("M"));
	}
	
	@Test
	public void test_processCustomer_AddCustomer_Success() {
		String firstName = "Mariana";
		String lastName = "Sayago";
		CustomerInfo customer = getValidCustomerInfo(null, firstName, lastName);
		RewardsManagementResponse response = manager.processCustomer(customer);
		LOGGER.info("test_processCustomer_AddCustomer_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerId());
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertNull(response.getErrorResponse());
	}
	
	@Test
	public void test_processCustomer_UpdateCustomer_Success() {
		String firstName = "Mariana";
		String lastName = "Sayago";
		String streetAddress = "2728 Paces Ferry Road";
		CustomerInfo customer = getValidCustomerInfo("MXS4825", firstName, lastName);
		customer.setStreetAddress(streetAddress);
		RewardsManagementResponse response = manager.processCustomer(customer);
		LOGGER.info("test_processCustomer_UpdateCustomer_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerId());
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertNull(response.getErrorResponse());
		Assert.assertTrue(response.getCustomerInfo().getStreetAddress().equalsIgnoreCase(streetAddress));
	}
	
	@Test
	public void test_processCustomer_UpdateCustomer_Failure() {
		String firstName = "Mariana";
		String lastName = "Sayago";
		String streetAddress = "9999 Paces Ferry Road";
		CustomerInfo customer = getValidCustomerInfo("TEST", firstName, lastName);
		customer.setStreetAddress(streetAddress);
		RewardsManagementResponse response = manager.processCustomer(customer);
		LOGGER.info("test_processCustomer_UpdateCustomer_Failure: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerId());
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertNotNull(response.getErrorResponse());
	}
	
	@Test
	public void test_deleteCustomer_Success() {
		String vipId = "PXN6778";
		RewardsManagementResponse response = manager.deleteCustomer(vipId);
		LOGGER.info("test_deleteCustomer_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNull(response.getCustomerInfo());
		Assert.assertNull(response.getPurchaseInfo());
		Assert.assertNull(response.getErrorResponse());
	}
	
	@Test
	public void test_deleteCustomer_Failure() {
		String vipId = "PXN6778";
		RewardsManagementResponse response = manager.deleteCustomer(vipId);
		LOGGER.info("test_deleteCustomer_Failure: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNull(response.getCustomerInfo());
		Assert.assertNull(response.getPurchaseInfo());
		Assert.assertNotNull(response.getErrorResponse());
	}
	
	public CustomerInfo getValidCustomerInfo(String vipId, String firstName, String lastName) {
		Date birthdate = Date.valueOf("1992-09-20");
		CustomerInfo customer = new CustomerInfo(vipId, firstName, lastName, "1234 Cumberland Parkway", "Atlanta",
				"GA", "30040", birthdate, "N", 0);
		return customer;
	}
}
