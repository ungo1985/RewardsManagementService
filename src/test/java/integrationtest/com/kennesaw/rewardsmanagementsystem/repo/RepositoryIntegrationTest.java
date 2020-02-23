package integrationtest.com.kennesaw.rewardsmanagementsystem.repo;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kennesaw.rewardsmanagementsystem.Application;
import com.kennesaw.rewardsmanagementsystem.repo.Repository;
import com.kennesaw.rewardsmanagementsystem.to.CustomerInfo;
import com.kennesaw.rewardsmanagementsystem.to.PurchaseInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryIntegrationTest {
	
	private static final Logger LOGGER = Logger.getLogger(RepositoryIntegrationTest.class.getName());

	@Autowired
	Repository repo;
	
	@Test
	public void test_getCustomerInfo_Success() throws SQLException {
		String vipId = "EXA6777";
		CustomerInfo customerInfo = repo.getCustomerInformation(vipId);
		LOGGER.info("test_getCustomerInfo_Success: " + customerInfo.getCustomerId());
		Assert.assertTrue(customerInfo.getCustomerId().equalsIgnoreCase(vipId));
	}
	
	@Test
	public void test_getCustomerInfo_Failure() throws SQLException {
		String vipId = "TEST";
		CustomerInfo customerInfo = repo.getCustomerInformation(vipId);
		LOGGER.info("test_getCustomerInfo_Failure: " + customerInfo.getCustomerId());
		Assert.assertNull(customerInfo.getCustomerId());
	}
	
	@Test
	public void test_getPurchaseInfo_Success() throws SQLException {
		String vipId = "EXA6777";
		PurchaseInfo purchaseInfo = repo.getPurchaseInfo(vipId);
		LOGGER.info("test_getPurchaseInfo_Success for: " + purchaseInfo.getCustomerId() + " " + purchaseInfo.getPurchasedItems().toString());
		Assert.assertNotNull(purchaseInfo.getPurchasedItems());
		Assert.assertTrue(purchaseInfo.getPurchasedItems().size()>0);
	}
	
	@Test
	public void test_getPurchaseInfo_Failure() throws SQLException {
		String vipId = "TEST2";
		PurchaseInfo purchaseInfo = repo.getPurchaseInfo(vipId);
		LOGGER.info("test_getPurchaseInfo_Failure for: " + purchaseInfo.getCustomerId() + " " + purchaseInfo.getPurchasedItems());
		Assert.assertTrue(purchaseInfo.getPurchasedItems().size()==0);
	}
}
