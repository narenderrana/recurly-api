package com.recurly.v2.utils;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.recurly.v2.Account;
import com.recurly.v2.Account.AccountState;
import com.recurly.v2.Accounts;
import com.recurly.v2.URLBase;
import com.recurly.v2.URLUtils;

public class AccountUtil  extends URLBase{
	
	private static final Logger logger =Logger.getLogger(AccountUtil.class);
 
	public  static Response listAccounts(AccountState state,String cursor,Integer noOfRecordsPerPage)
	{
		  Properties data=new Properties();
 
		  if(state!=null)
		  data.put("state", state);
		  else if(cursor!=null)
		  data.put("cursor", cursor);
		  else if(noOfRecordsPerPage !=null)
		  data.put("per_page", noOfRecordsPerPage);
		
		 try{
			 Response response=URLUtils.doGet(LIST_ACCOUNT, data, REQUEST_HEADERS);
			 response.setType(Accounts.class );
			 return response;
		 
			}catch(Exception e2){
				e2.printStackTrace();
			
			}
		 return null;
	 }
	
	public  static Response createAccount(String account_code,String email,String first_name,String last_name)
	{
		StringBuffer requestXML=new StringBuffer("" +
		  "<account>"
		  +"<account_code>"+account_code+"</account_code>"
		  +"<email>"+email+"</email>"
		  +"<first_name>"+first_name+"</first_name>"
		  +"<last_name>"+last_name+"</last_name>"
		+"</account>");
		
		 try{
			 Response response=URLUtils.doPost(CREATE_ACCOUNT, null, REQUEST_HEADERS, requestXML);
			 response.setType(Account.class );
			 return response;
		 
			}catch(Exception e2){
				e2.printStackTrace();
			
			}
		 return null;
	 }

	public  static Response findAccount(String account_code)
	{
	 
		 try{
			    Response response=URLUtils.doGet(GET_ACCOUNT_account_code.replace(":account_code", account_code),null, REQUEST_HEADERS);
			    response.setType(Account.class );
			    return response;
 
			}catch(Exception e2){
				e2.printStackTrace();
			
			}
		 return null;
	 }
	
	 
}
