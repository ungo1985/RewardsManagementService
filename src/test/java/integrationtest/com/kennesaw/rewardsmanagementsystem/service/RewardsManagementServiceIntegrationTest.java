package integrationtest.com.kennesaw.rewardsmanagementsystem.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.kennesaw.rewardsmanagementsystem.Application;
import com.kennesaw.rewardsmanagementsystem.service.RewardsManagementService;
import com.kennesaw.rewardsmanagementsystem.to.CustomerInfo;
import com.kennesaw.rewardsmanagementsystem.to.ErrorResponse;
import com.kennesaw.rewardsmanagementsystem.to.Purchase;
import com.kennesaw.rewardsmanagementsystem.to.PurchaseInfo;
import com.kennesaw.rewardsmanagementsystem.to.RewardsManagementResponse;
import com.kennesaw.rewardsmanagementsystem.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardsManagementServiceIntegrationTest {
	
	private static final Logger LOGGER = Logger.getLogger(RewardsManagementServiceIntegrationTest.class.getName());
	
	@Autowired
	RewardsManagementService service;
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate testRestTemplate = new TestRestTemplate();
	HttpHeaders httpHeaders = new HttpHeaders();
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void testGetCustomerAndPurchaseInfo_Success() {
		String vipId = "EXA6777";
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
		
		RewardsManagementResponse expectedResponse = getSuccessResponse(vipId);
		
		ResponseEntity<RewardsManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/getCustomerAndPurchaseInfo?vipId=" + vipId),
						HttpMethod.GET, entity, RewardsManagementResponse.class);
		
		LOGGER.info("testGetCustomerAndPurchaseInfo_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testGetCustomerAndPurchaseInfo_Success expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getCustomerInfo().getFirstName().equals(expectedResponse.getCustomerInfo().getFirstName()));
	}
	
	@Test
	public void testGetCustomerAndPurchaseInfo_Failure_ResourceNotAvailable() {
		String vipId = "TEST";
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
		
		RewardsManagementResponse expectedResponse = getFailureResponse_204(vipId);
		
		ResponseEntity<RewardsManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/getCustomerAndPurchaseInfo?vipId=" + vipId),
						HttpMethod.GET, entity, RewardsManagementResponse.class);
		
		LOGGER.info("testGetCustomerAndPurchaseInfo_Failure_ResourceNotAvailable: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testGetCustomerAndPurchaseInfo_Failure_ResourceNotAvailable expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertNotNull(responseEntity.getBody().getErrorResponse());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getErrorResponse().getCode() == Constants.CODE_RESOURCE_NOT_AVAILABLE);
		Assert.assertTrue(responseEntity.getBody().getErrorResponse().getMessage().equals(Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));
	}
	
	@Test
	public void testPostCustomer_AddCustomer_Success() {	
		CustomerInfo customerInfo = getCustomerInfo(null);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(customerInfo, httpHeaders);
		
		RewardsManagementResponse expectedResponse = getSuccessResponse(null);
		
		ResponseEntity<RewardsManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/postCustomer"),
						HttpMethod.POST, entity, RewardsManagementResponse.class);
		
		LOGGER.info("testPostCustomer_AddCustomer_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testPostCustomer_AddCustomer_Success expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getCustomerInfo().getFirstName().equals(expectedResponse.getCustomerInfo().getFirstName()));
	}
	
	@Test
	public void testPostCustomer_AddCustomer_BadRequest() {	
		CustomerInfo customerInfo = getCustomerInfo(null);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
		
		ResponseEntity<RewardsManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/postCustomer"),
						HttpMethod.POST, entity, RewardsManagementResponse.class);
		
		LOGGER.info("testPostCustomer_AddCustomer_BadRequest: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void testPostCustomer_UpdateCustomer_Success() {	
		String customerId = "EXA6777";
		String city = "AUSTELL";
		CustomerInfo customerInfo = getCustomerInfo(customerId);
		customerInfo.setCity(city);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(customerInfo, httpHeaders);
		
		RewardsManagementResponse expectedResponse = getSuccessResponse(customerId);
		expectedResponse.getCustomerInfo().setCity(city);
		
		ResponseEntity<RewardsManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/postCustomer"),
						HttpMethod.POST, entity, RewardsManagementResponse.class);
		
		LOGGER.info("testPostCustomer_UpdateCustomer_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testPostCustomer_UpdateCustomer_Success expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getCustomerInfo().getCity().equals(expectedResponse.getCustomerInfo().getCity()));
	}
	
	@Test
	public void testPostCustomer_UpdateCustomer_Failure() {	
		String customerId = "TEST";
		String city = "AUSTELL";
		CustomerInfo customerInfo = getCustomerInfo(customerId);
		customerInfo.setCity(city);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(customerInfo, httpHeaders);
		
		RewardsManagementResponse expectedResponse = getSuccessResponse(customerId);
		expectedResponse.getCustomerInfo().setCity(city);
		
		ResponseEntity<RewardsManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/postCustomer"),
						HttpMethod.POST, entity, RewardsManagementResponse.class);
		
		LOGGER.info("testPostCustomer_UpdateCustomer_Failure: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testPostCustomer_UpdateCustomer_Failure expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getCustomerInfo().getCity().equals(expectedResponse.getCustomerInfo().getCity()));
		Assert.assertNotNull(responseEntity.getBody().getErrorResponse());
	}
	
	public RewardsManagementResponse getSuccessResponse(String vipId) {
		RewardsManagementResponse response = new RewardsManagementResponse();
		response.setCustomerId(vipId);
		
		String firstName = "Eric";
		String lastName = "Acevedo";
		String streetAddress = "8888 VININGS VINTAGE WAY";
		String city="ATLANTA";
		String state="GA";
		String zipCode="30080";
		String birthday = "1999-02-18";
		Date birthDate = Date.valueOf(birthday);
		String goldStatusFlag="Y";
		int points=300;
		
		CustomerInfo customerInfo = new CustomerInfo(vipId, firstName, lastName, streetAddress, city,
			 state, zipCode, birthDate, goldStatusFlag, points);
		
		List<Purchase> purchases = new ArrayList<Purchase>();
		Purchase p1 = new Purchase("CHOCOLATE", new Float(2.5), "ICE CREAM", Date.valueOf("2020-02-19"), "N");
		Purchase p2 = new Purchase("VANILLA", new Float(1.5), "ICE CREAM", Date.valueOf("2020-01-19"), "Y");
		Purchase p3 = new Purchase("STRAWBERRY", new Float(3.0), "ICE CREAM", Date.valueOf("2020-02-17"), "N");
		Purchase p4 = new Purchase("VANILLA", new Float(3.5), "YOGURT", Date.valueOf("2020-01-29"), "Y");
		
		purchases.add(p1);
		purchases.add(p2);
		purchases.add(p3);
		purchases.add(p4);
		
		PurchaseInfo purchaseInfo = new PurchaseInfo(vipId, purchases);
		
		response.setCustomerInfo(customerInfo);
		response.setPurchaseInfo(purchaseInfo);
		return response;
	}
	
	public RewardsManagementResponse getFailureResponse_204(String vipId) {
		RewardsManagementResponse response = new RewardsManagementResponse();
		response.setCustomerId(vipId);
		response.setErrorResponse(new ErrorResponse(Constants.CODE_RESOURCE_NOT_AVAILABLE, Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));
		return response;
	}
	
	public CustomerInfo getCustomerInfo(String vipId) {

		String firstName = "Eric";
		String lastName = "Acevedo";
		String streetAddress = "8888 VININGS VINTAGE WAY";
		String city="ATLANTA";
		String state="GA";
		String zipCode="30080";
		String birthday = "1999-02-18";
		Date birthDate = Date.valueOf(birthday);
		String goldStatusFlag="Y";
		int points=300;
		
		return new CustomerInfo(vipId, firstName, lastName, streetAddress, city,
			 state, zipCode, birthDate, goldStatusFlag, points);
	}

}
