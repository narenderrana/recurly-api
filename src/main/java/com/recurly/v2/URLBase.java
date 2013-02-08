package com.recurly.v2;

import java.util.Properties;
import java.util.ResourceBundle;


public class URLBase {

public static Properties REQUEST_HEADERS;
public static final ResourceBundle resourse;
public static final String BASEURL="https://api.recurly.com/v2/";
public static String LIST_SUBSCRIPTIONS_account_code=BASEURL+"accounts/:account_code/subscriptions";
public static String LOOKUP_SUBSCRIPTION_uuid=BASEURL+"subscriptions/:uuid";
public static String CREATE_SUBSCRIPTION=BASEURL+"subscriptions";
public static String CANCEL_SUBSCRIPTION_uuid=BASEURL+"subscriptions/:uuid/cancel";
public static String REACTIVATE_SUBSCRIPTION_uuid=BASEURL+"subscriptions/:uuid/reactivate";
public static String TERMINATE_SUBSCRIPTION_uuid=BASEURL+"subscriptions/:uuid/terminate";
public static String UPDATE_BILLING_INFO_account_code=BASEURL+"accounts/:account_code/billing_info";
public static String CLEAR_BILLING_INFO_account_code=BASEURL+"accounts/:account_code/billing_info";

public static String CREATE_ACCOUNT=BASEURL+"accounts";
public static String GET_ACCOUNT_account_code=BASEURL+"/accounts/:account_code";


/*Transaction*/

 
public static String LIST_ALL_TRANSACTIONS=BASEURL+"/transactions";
public static String LOOKUP_TRANSACTION_transaction_id=BASEURL+"/transactions/:transaction_id";
public static String LIST_ACCOUNT_TRANSACTIONS_account_code=BASEURL+"/accounts/:account_code/transactions";
public static String CREATE_TRANSACTION=BASEURL+"/transactions";
public static String REFUND_ACCOUNT_TRANSACTIONS_transaction_id=BASEURL+"/transactions/:transaction_id";

 

	static{
		resourse = ResourceBundle.getBundle("settings");
		REQUEST_HEADERS =new Properties();
		REQUEST_HEADERS.put("Authorization","Basic "+BASE64Encoder.encode(resourse.getString("recurly_username").getBytes()));
		REQUEST_HEADERS.put("Accept", "application/xml");
		REQUEST_HEADERS.put("Content-Type","application/xml; charset=utf-8");
		
	}
	
}
