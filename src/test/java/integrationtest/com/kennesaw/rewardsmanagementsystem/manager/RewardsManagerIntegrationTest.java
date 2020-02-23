package integrationtest.com.kennesaw.rewardsmanagementsystem.manager;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kennesaw.rewardsmanagementsystem.Application;
import com.kennesaw.rewardsmanagementsystem.manager.RewardsManager;
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
}
