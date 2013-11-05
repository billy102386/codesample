package org.glassfish.jersey.examples.helloworld;
public class Customer
{

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String eMail;
	private String userName;

	public Customer(String inFirstName,String inLastName,String inPhoneNumber,String inEMail,String inUserName)
	{
		firstName = inFirstName;
		lastName = inLastName;
		phoneNumber = inPhoneNumber;
		eMail = inEMail;
		userName=inUserName;
	}
	public String toJson()
	{
		String resp = "{\"firstName\":\""+firstName+"\",\"lastName\":\""+lastName+"\",\"phoneNumber\":\""+phoneNumber+"\",\"eMail\":\""+eMail+"\"}";
		return resp;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
