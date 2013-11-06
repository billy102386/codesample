package org.glassfish.jersey.examples.helloworld;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class CustomerStore
{
HashMap<String,Customer> customerData;
	public CustomerStore()
	{
		customerData = new HashMap<String, Customer>();
	}

	public void processCustomer(Customer inCustomer)
	{
		customerData.put(inCustomer.getUserName(), inCustomer);
	}
	public boolean patchCustomer(Customer inCustomer)
	{
		boolean patched = false;
		if(customerData.containsKey(inCustomer.getUserName()))
			{
				Customer updateCustomer = customerData.get(inCustomer.getUserName());
				if(inCustomer.getFirstName()!=null)
				{
					updateCustomer.setFirstName(inCustomer.getFirstName());
				}
				if(inCustomer.getLastName()!=null)
				{
					updateCustomer.setLastName(inCustomer.getLastName());
				}
				if(inCustomer.getPhoneNumber()!=null)
				{
					updateCustomer.setPhoneNumber(inCustomer.getPhoneNumber());
				}
				if(inCustomer.getEmail()!=null)
				{
					updateCustomer.setEmail(inCustomer.getEmail());
				}
			customerData.put(updateCustomer.getUserName(), updateCustomer);
			patched = true;
			}
			return patched;
			
	}
		
	
	public Customer getCustomer(String userName)
	{
		System.out.println("Returning Customer: "+userName);
		
		return customerData.get(userName);
	}
}