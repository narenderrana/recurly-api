package com.recurly.v2;

import org.junit.Test;
 

public class RecurlyTest {

    @Test
	public void test0()  {
		
		try {
			
		 
			
			//Object account=RecurlyUtils.createAccount("10056", "narender.rana+10056", "narender", "Rana");
			//System.out.println(account);
			com.recurly.v2.Transactions transactions=RecurlyUtils.getTransactions("1013");
			System.out.println("transaction:"+transactions);
		
			//sub.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
	}

	 
  
		
}
