package com.kennesaw.rewardsmanagementsystem.repo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.kennesaw.rewardsmanagementsystem.config.DatabaseConfig;
import com.kennesaw.rewardsmanagementsystem.to.CustomerInfo;
import com.kennesaw.rewardsmanagementsystem.to.Purchase;
import com.kennesaw.rewardsmanagementsystem.to.PurchaseInfo;
import com.kennesaw.rewardsmanagementsystem.util.Constants;

@Component
public class Repository {
	
	private static final Logger LOGGER = Logger.getLogger(Repository.class.getName());
	
	DatabaseConfig dbConfig = new DatabaseConfig();
	
	public CustomerInfo getCustomerInformation(String vipId) throws SQLException {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomerId(vipId);

			Connection con = getDatabaseConnection();
			Statement stmt = getStatement(con);
			ResultSet rs = stmt.executeQuery(buildSelectSqlStatementForCustomerInfo(vipId));

			if(rs.next()){
				customerInfo = new CustomerInfo(vipId, rs.getString("first_name"), rs.getString("last_name"), 
						rs.getString("street_address"), rs.getString("city"), rs.getString("state"), rs.getString("zip_code"), rs.getDate("birthday"), Constants.defaultGoldStatus, rs.getInt("points"));
			}
			closeDatabaseConnection(con, stmt);	
		return customerInfo;
	}
	
	public String buildSelectSqlStatementForCustomerInfo(String vipId){
		String sql =  "select customer_id, first_name, last_name, street_address, city, state, zip_code, birthday, points from customer_info where customer_id in ( '" + vipId.toUpperCase() + "' )";
		LOGGER.info("buildSelectSqlStatement: " + sql);
		return sql;
	}
	
	public String buildInsertSqlStatementForCustomerInfo(CustomerInfo customer) {
		String sql = "INSERT INTO customer_info VALUES( '" 
		+ customer.getCustomerId().toUpperCase() + "', '" 
		+ customer.getFirstName().toUpperCase() + "', '"
		+ customer.getLastName().toUpperCase() + "', '"
		+ customer.getStreetAddress().toUpperCase() + "', '"
		+ customer.getCity().toUpperCase() + "', '"
		+ customer.getState().toUpperCase() + "', '"
		+ customer.getZipCode() + "', '"
		+ customer.getBirthday() + "', "
		+ customer.getPoints() + " )";
		LOGGER.info("buildInsertSqlStatementForCustomerInfo: " + sql);
		return sql;
	}
	
	public String buildUpdateSqlStatementForCustomerInfo(CustomerInfo customer) {
		String sql = "UPDATE customer_info "
		+ "SET first_name = '"+customer.getFirstName().toUpperCase()+"', "
		+ "last_name = '"+customer.getLastName().toUpperCase()+"', "
		+ "street_address = '"+customer.getStreetAddress().toUpperCase()+"', "
		+ "city = '"+customer.getCity().toUpperCase()+"', "
		+ "state = '"+customer.getState().toUpperCase()+"', "
		+ "zip_code = '"+customer.getZipCode()+"', "
		+ "birthday = '"+customer.getBirthday()+"', "
		+ "points = " + customer.getPoints()
		+ " WHERE customer_id = '"+customer.getCustomerId()+"' ";
		LOGGER.info("buildUpdateSqlStatementForCustomerInfo: " + sql);
		return sql;
	}
	
	public String buildSelectSqlStatementForPurchaseInfo(String vipId){
		String sql = "select items.available_item, items.price, items.type, purchased_items.purchased_date, purchased_items.pre_ordered_flag, purchased_items.customer_id\r\n" + 
				     "from rms.items JOIN rms.purchased_items ON items.item_id = purchased_items.item_id and purchased_items.customer_id = '" + vipId.toUpperCase() + "'";
		LOGGER.info("buildSelectSqlStatementForPurchaseInfo: " + sql);
		return sql;
	}
	
	private Connection getDatabaseConnection() {
		Connection con = null;
		con = dbConfig.getConnection();
		return con;
	}
	
	private Statement getStatement(Connection con) throws SQLException {
		Statement stmt = null;
		stmt = con.createStatement();
		return stmt;
	}
	
	private void closeDatabaseConnection(Connection con, Statement stmt) throws SQLException {
		stmt.close();
		con.close();
	}

	public PurchaseInfo getPurchaseInfo(String vipId) throws SQLException {
		
		PurchaseInfo purchaseInfo = new PurchaseInfo();
		purchaseInfo.setCustomerId(vipId);
		List<Purchase> purchaseList = new ArrayList<Purchase>();
		Purchase purchase = new Purchase();

			Connection con = getDatabaseConnection();
			Statement stmt = getStatement(con);
			ResultSet rs = stmt.executeQuery(buildSelectSqlStatementForPurchaseInfo(vipId));

			while(rs.next()){
				purchase = new Purchase(rs.getString("available_item"), rs.getFloat("price"), rs.getString("type"), rs.getDate("purchased_date"), rs.getString("pre_ordered_flag"));
				purchaseList.add(purchase);
			}
			closeDatabaseConnection(con, stmt);
			purchaseInfo.setPurchasedItems(purchaseList);
		return purchaseInfo;
	}
	
	public boolean addNewCustomer(CustomerInfo customer) throws SQLException{
		Connection con = getDatabaseConnection();
		Statement stmt = getStatement(con);
		int numberOfRows = stmt.executeUpdate(buildInsertSqlStatementForCustomerInfo(customer));
		closeDatabaseConnection(con, stmt);
		if(numberOfRows == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean editCustomer(CustomerInfo customer) throws SQLException {
		Connection con = getDatabaseConnection();
		Statement stmt = getStatement(con);
		int numberOfRows = stmt.executeUpdate(buildUpdateSqlStatementForCustomerInfo(customer));
		closeDatabaseConnection(con, stmt);
		if(numberOfRows == 1) {
			return true;
		}
		else {
			return false;
		}
	}

}
