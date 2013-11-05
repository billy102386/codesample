package org.glassfish.jersey.examples.helloworld;
import java.util.HashMap;
public class CustomerStore
{
	private static HashMap<String,Customer> customerData = new HashMap<String, Customer>();

	public static void processCustomer(Customer inCustomer)
	{
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
			if(inCustomer.geteMail()!=null)
			{
				updateCustomer.seteMail(inCustomer.geteMail());
			}
			customerData.put(updateCustomer.getUserName(), updateCustomer);
		}
		else
		{
			customerData.put(inCustomer.getUserName(), inCustomer);
		}
	}
	public static Customer getCustomer(String userName)
	{
		System.out.println("Returning Customer: "+userName);
		return customerData.get(userName);
	}
}