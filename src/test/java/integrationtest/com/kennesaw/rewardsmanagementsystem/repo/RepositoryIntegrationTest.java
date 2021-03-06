package integrationtest.com.kennesaw.rewardsmanagementsystem.repo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kennesaw.rewardsmanagementsystem.Application;
import com.kennesaw.rewardsmanagementsystem.repo.Repository;
import com.kennesaw.rewardsmanagementsystem.to.CustomerInfo;
import com.kennesaw.rewardsmanagementsystem.to.PurchaseInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepositoryIntegrationTest {
	
	private static final Logger LOGGER = Logger.getLogger(RepositoryIntegrationTest.class.getName());

	@Autowired
	Repository repo;
	
	@Test
	public void test01_addNewCustomer_Success() throws SQLException {
		Date birthdate = Date.valueOf("1992-09-20");
		CustomerInfo customer = new CustomerInfo("FJS1234", "Francisco", "Sayago", "8273 APPLE ORCHARD WAY", "ATLANTA", "GA", "30822", birthdate, "N", 0);
		boolean result = repo.addNewCustomer(customer);
		LOGGER.info("test_addNewCustomer_Success: " + result);
		Assert.assertTrue(result);
	}
	
	@Test
	public void test02_addNewCustomer_Failure() throws SQLException { //throws an SQLException
		Date birthdate = Date.valueOf("1992-09-20");
		CustomerInfo customer = new CustomerInfo("FJS1234", "Francisco", "Sayago", "8273 APPLE ORCHARD WAY", "ATLANTA", "GA", "30822", birthdate, "N", 0);
		try {
			repo.addNewCustomer(customer);
		}
		catch(SQLException e) {
			String result = e.getMessage();
			LOGGER.info("test_addNewCustomer_Failure: " + result);
			Assert.assertTrue(result.contains("Duplicate entry"));
		}
	}
	
	@Test
	public void test03_getCustomerInfo_Success() throws SQLException {
		String vipId = "FJS1234";
		CustomerInfo customerInfo = repo.getCustomerInformation(vipId);
		LOGGER.info("test_getCustomerInfo_Success: " + customerInfo.getCustomerId());
		Assert.assertTrue(customerInfo.getCustomerId().equalsIgnoreCase(vipId));
	}
	
	@Test
	public void test04_getCustomerInfo_Failure() throws SQLException {
		String vipId = "TEST";
		CustomerInfo customerInfo = repo.getCustomerInformation(vipId);
		LOGGER.info("test_getCustomerInfo_Failure: " + customerInfo.getCustomerId());
		Assert.assertNull(customerInfo.getFirstName());
	}
	
	@Test
	public void test05_getPurchaseInfo_Success() throws SQLException {
		String vipId = "EXA6777";
		PurchaseInfo purchaseInfo = repo.getPurchaseInfo(vipId);
		LOGGER.info("test_getPurchaseInfo_Success for: " + purchaseInfo.getCustomerId() + " " + purchaseInfo.getPurchasedItems().toString());
		Assert.assertNotNull(purchaseInfo.getPurchasedItems());
		Assert.assertTrue(purchaseInfo.getPurchasedItems().size()>0);
	}
	
	@Test
	public void test06_getPurchaseInfo_Failure() throws SQLException {
		String vipId = "TEST2";
		PurchaseInfo purchaseInfo = repo.getPurchaseInfo(vipId);
		LOGGER.info("test_getPurchaseInfo_Failure for: " + purchaseInfo.getCustomerId() + " " + purchaseInfo.getPurchasedItems());
		Assert.assertTrue(purchaseInfo.getPurchasedItems().size()==0);
	}
	
	@Test
	public void test07_editCustomer_Success() throws SQLException {
		Date birthdate = Date.valueOf("1992-09-20");
		CustomerInfo customer = new CustomerInfo("FJS1234", "Francisco", "Sayago", "8273 APPLE ORCHARD WAY", "NEW YORK", "NY", "30822", birthdate, "N", 0);
		boolean result = repo.editCustomer(customer);
		LOGGER.info("test_editCustomer_Success: " + result);
		Assert.assertTrue(result);
	}
	
	@Test
	public void test08_editCustomer_Failure() throws SQLException { //returns false
		Date birthdate = Date.valueOf("1992-09-20");
		CustomerInfo customer = new CustomerInfo("TEST", "Francisco", "Sayago", "8273 APPLE ORCHARD WAY", "NEW YORK", "NY", "30822", birthdate, "N", 0);
		boolean result = repo.editCustomer(customer);
		LOGGER.info("test_editCustomer_Failure: " + result);
		Assert.assertFalse(result);
		
	}
	
	@Test
	public void test09_deleteCustomer_Success() throws SQLException {
		String vipId = "FJS1234";
		boolean result = repo.deleteCustomer(vipId);
		LOGGER.info("test_deleteCustomer_Success: " + result);
		Assert.assertTrue(result);
	}
	
	@Test
	public void test10_deleteCustomer_Failure() throws SQLException {
		String vipId = "FJS1234";
		boolean result = repo.deleteCustomer(vipId);
		LOGGER.info("test_deleteCustomer_Failure: " + result);
		Assert.assertFalse(result);
	}
	
	@Test
	public void test11_getDailyPurchases_Success() throws SQLException {
		PurchaseInfo purchaseInfo = repo.getDailyPurchases();
		LOGGER.info("test_getDailyPurchases_Success: " + purchaseInfo.toString());
		Assert.assertTrue(purchaseInfo.getPurchasedItems().size()>0);
	}
	
	@Test
	@Ignore
	public void test12_getDailyPurchases_Failure() throws SQLException {
		PurchaseInfo purchaseInfo = repo.getDailyPurchases();
		LOGGER.info("test_getDailyPurchases_Failure: " + purchaseInfo.toString());
		Assert.assertTrue(purchaseInfo.getPurchasedItems().size()==0);
	}
	
	@Test
	public void test13_getMonthlyPointsForCustomer_Success() throws SQLException {
		String vipId = "EXA6777";
		CustomerInfo customerInfo = repo.getMonthlyPointsForCustomer(vipId);
		LOGGER.info("test_getMonthlyPointsForCustomer_Success: " + customerInfo.getMonthlyPoints());
		Assert.assertTrue(customerInfo.getCustomerId().equalsIgnoreCase(vipId));
		Assert.assertTrue(customerInfo.getMonthlyPoints()>0);
	}
	
	@Test
	public void test14_getMonthlyPointsForCustomer_Failure() throws SQLException {
		String vipId = "TEST";
		CustomerInfo customerInfo = repo.getMonthlyPointsForCustomer(vipId);
		LOGGER.info("test_getMonthlyPointsForCustomer_Failure: " + customerInfo.getMonthlyPoints());
		Assert.assertTrue(customerInfo.getCustomerId().equalsIgnoreCase(vipId));
		Assert.assertTrue(customerInfo.getMonthlyPoints()==0);
	}
}
