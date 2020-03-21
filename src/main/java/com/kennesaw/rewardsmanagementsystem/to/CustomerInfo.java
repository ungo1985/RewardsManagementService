package com.kennesaw.rewardsmanagementsystem.to;

import java.sql.Date;

public class CustomerInfo {
	
	private String customerId;
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private Date birthday;
	private String goldStatusFlag;
	private int points;
	private int monthlyPoints;
	
	public CustomerInfo() {}

	public CustomerInfo(String customerId, String firstName, String lastName, String streetAddress, String city,
			String state, String zipCode, Date birthday, String goldStatusFlag, int points) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.birthday = birthday;
		this.goldStatusFlag = goldStatusFlag;
		this.points = points;
	}

	public CustomerInfo(String customerId, int monthlyPoints) {
		super();
		this.customerId = customerId;
		this.monthlyPoints = monthlyPoints;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGoldStatusFlag() {
		return goldStatusFlag;
	}

	public void setGoldStatusFlag(String goldStatusFlag) {
		this.goldStatusFlag = goldStatusFlag;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getMonthlyPoints() {
		return monthlyPoints;
	}

	public void setMonthlyPoints(int monthlyPoints) {
		this.monthlyPoints = monthlyPoints;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((goldStatusFlag == null) ? 0 : goldStatusFlag.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + monthlyPoints;
		result = prime * result + points;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((streetAddress == null) ? 0 : streetAddress.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		CustomerInfo other = (CustomerInfo) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (goldStatusFlag == null) {
			if (other.goldStatusFlag != null)
				return false;
		} else if (!goldStatusFlag.equals(other.goldStatusFlag))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (monthlyPoints != other.monthlyPoints)
			return false;
		if (points != other.points)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		} else if (!streetAddress.equals(other.streetAddress))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerInfo [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode
				+ ", birthday=" + birthday + ", goldStatusFlag=" + goldStatusFlag + ", points=" + points
				+ ", monthlyPoints=" + monthlyPoints + "]";
	}
	

}
