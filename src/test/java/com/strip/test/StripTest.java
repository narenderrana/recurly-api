package com.strip.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.Card;
import com.stripe.model.Customer;

public class StripTest {
	
	private static String  TEST_SECRET_KEY="sk_test_M27g5aCrS06jsJZOKLpTB09D";
	private static String  TEST_PUBLISH_KEY="pk_test_3mzDKIbPN6NeunhKiVQREa5g";
	private static String  LIVE_SECRET_KEY="sk_live_pgDikDe2BIlWzkd5Zk4Glio5";
	private static String  LIVE_PUBLISH_KEY="pk_live_IyZFupobc3ktTekJW5tW1fWv";

	@Test
	public void createAccount(){
		
		Customer cust=new Customer();
		cust.setId("10047");
		cust.setEmail("narender.rana+10047@netsolutionsindia.com");
		
		Card card=new Card();
		card.setName("Narender");
		card.setCountry("USD");
		card.setCountry("US");
		card.setAddressCity("Irvin");
		card.setAddressCountry("US");
		card.setExpMonth(3);
		card.setExpYear(2013);
		card.setCvcCheck("123");
		card.setLast4("4242424242424242");
		
		
		
		cust.setActiveCard(card);
		
		 
		 
		try {
		 
			Stripe.apiKey =TEST_SECRET_KEY;

			Map<String, Object> customerParams = new HashMap<String, Object>();
			customerParams.put("description", "CUSTOMER: narender.rana+1@netsolutionsindia.com ");
			customerParams.put("email", "narender.rana+1@netsolutionsindia.com");
			 
			cust.create(customerParams);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	
		
		
		 
	}
	@Test
	public void createSubscriptions() throws StripeException{
		
		Customer cust=Customer.retrieve("cus_1FiriUMkNkSjwm");
	     System.out.println(cust.getEmail());
		
		
		
	}
	
}
