package com.bloomerang.beans;

public class PersonInRecord implements BloomerangRecord {
	String name;
	String dob;
	String email;
	String phone;
	String contactType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	
	public String toString() {
		return "[" + name 
				   + "," + dob
				   + "," + email
				   + "," + phone
				   + "," + contactType
				   +"]";
		
	}
	
	
}
