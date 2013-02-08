package com.recurly.v2;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


 


public class RecurlyUtils extends URLBase {

	// private static final Logger logger = LoggerFactory.getLogger(RecurlyUtils.class);

	
 public  static Subscriptions getSubscriptions(String accountCode){
	 
	 
	 try{

			StringBuffer buffer=URLUtils.doGet(LIST_SUBSCRIPTIONS_account_code.replace(":account_code", accountCode), null, REQUEST_HEADERS);
			JAXBContext context = JAXBContext.newInstance(Subscriptions.class);
			context.createUnmarshaller();
			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
			Subscriptions subscriptions =  (com.recurly.v2.Subscriptions) unmarshaller.unmarshal(new StringReader(buffer.toString()));
		    return subscriptions;
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
 }
 
public  static Object addSubscription(String  plan_code,String currency,String account_code,String email,String first_name,String last_name){
	 
	StringBuffer requestXML=new StringBuffer("" +
	 		"<subscription>"+
	 		"<plan_code>"+plan_code+"</plan_code>"+
	 		"<currency>"+currency+"</currency>"+
	 		"<account>"+
	 			"<account_code>"+account_code+"</account_code>"+
	 			"<email>"+email+"</email>"+
	 			"<first_name>"+first_name+"</first_name>"+
	 			"<last_name>"+last_name+"</last_name>"+
	 		/*"<billing_info>"+
	 		    "<number>4111111111111111</number>"+
	 		    "<month>3</month>"+
	 		    "<year>2015</year>"+
	 		    "</billing_info>"+*/
	 		"</account>"+
	 		"</subscription>");
	
	 try{
		 StringBuffer buffer=URLUtils.doPost(CREATE_SUBSCRIPTION, null, REQUEST_HEADERS, requestXML);
		 
		 if(buffer.toString().contains("<errors>")){return errors(buffer);}
		 else if(buffer.toString().contains("<error>")){return error(buffer);}
		 else{
			 
			    JAXBContext context = JAXBContext.newInstance(Subscription.class);
				context.createUnmarshaller();
				javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
				Subscription addedsubscription =  (Subscription) unmarshaller.unmarshal(new StringReader(buffer.toString()));
				return addedsubscription;
		 }
			 
		    
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
 }
public  static Object createSubscription(String  plan_code,String currency,String account_code,String email,String first_name,String last_name,String cardNumber,String card_exp_month,String cart_exp_year){
	 
	StringBuffer requestXML=new StringBuffer("" +
	 		"<subscription>"+
	 		"<plan_code>"+plan_code+"</plan_code>"+
	 		"<currency>"+currency+"</currency>"+
	 		"<account>"+
	 			"<account_code>"+account_code+"</account_code>"+
	 			"<email>"+email+"</email>"+
	 			"<first_name>"+first_name+"</first_name>"+
	 			"<last_name>"+last_name+"</last_name>"+
	 		"<billing_info>"+
	 		    "<number>"+cardNumber+"</number>"+
	 		    "<month>"+card_exp_month+"</month>"+
	 		    "<year>"+cart_exp_year+"</year>"+
	 		    "</billing_info>"+
	 		"</account>"+
	 		"</subscription>");
	 try{
			StringBuffer buffer=URLUtils.doPost(CREATE_SUBSCRIPTION, null, REQUEST_HEADERS, requestXML);
			
			
			 if(buffer.toString().contains("<errors>")){return errors(buffer);}
			 else if(buffer.toString().contains("<error>")){return error(buffer);}
			 else{
			JAXBContext context = JAXBContext.newInstance(Subscription.class);
			context.createUnmarshaller();
			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
			Subscription addedsubscription =  (Subscription) unmarshaller.unmarshal(new StringReader(buffer.toString()));
		    return addedsubscription;}
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
 }
 
public static Object  cancelSubscription(String accountCode,String plan_code){
	 
	 
	 try{
		 Subscriptions subscriptions= getSubscriptions(accountCode);
		 List<Subscription> subscriptionList=subscriptions.subscriptions;
		 String uuid=null;
		 if(subscriptionList!=null){
			for(Subscription subscription:subscriptionList){
				if(plan_code.equalsIgnoreCase(subscription.plan.planCode) ){
					uuid=subscription.uuid;
				}
			}
			 
		 }
		 
	  if(uuid!=null){
		  
		    StringBuffer buffer=URLUtils.doPut(CANCEL_SUBSCRIPTION_uuid.replace(":uuid", uuid),null,REQUEST_HEADERS,"");
		 if(buffer.toString().contains("<errors>")){return errors(buffer);}
		 else if(buffer.toString().contains("<error>")){return error(buffer);}
		 else{
			 
			    JAXBContext context = JAXBContext.newInstance(Subscription.class);
				context.createUnmarshaller();
				javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
				Subscription addedsubscription =  (Subscription) unmarshaller.unmarshal(new StringReader(buffer.toString()));
				return addedsubscription;
		 }
		  
	  }
		   
			
		    return subscriptions;
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
 }



public static Object  cancelSubscriptions(String accountCode){
	 
	 
	 try{
		 Subscriptions subscriptions= getSubscriptions(accountCode);
		 List<Subscription> subscriptionList=subscriptions.subscriptions;
		 List<Subscription> updatedSubscriptionList=new ArrayList<Subscription>();
		 String uuid=null;
		 int count=0;
		 if(subscriptionList!=null){
			for(Subscription subscription:subscriptionList){
				++count;
				if("active".equalsIgnoreCase(subscription.state)){
					uuid=subscription.uuid;
					try{
						 if(uuid!=null){
							  
							 StringBuffer buffer=URLUtils.doPut(CANCEL_SUBSCRIPTION_uuid.replace(":uuid", uuid),null,REQUEST_HEADERS,"");
							 if(buffer.toString().contains("<errors>")){return errors(buffer);}
							 else if(buffer.toString().contains("<error>")){return error(buffer);}
							 else{
								 
								    JAXBContext context = JAXBContext.newInstance(Subscription.class);
									context.createUnmarshaller();
									javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
									Subscription addedsubscription =  (Subscription) unmarshaller.unmarshal(new StringReader(buffer.toString()));
									updatedSubscriptionList.add(count-1, addedsubscription);
							 }
							  
						  }
						
					}
					catch(Exception e){e.printStackTrace();}
					
					
				}
				else updatedSubscriptionList.add(count-1, subscription);
			}
			 
		 }
		 
	 
		   
			
		    return updatedSubscriptionList;
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
}

public static Object  reactivateSubscription(String accountCode,String plan_code){
	 
	 
	 try{
		 Subscriptions subscriptions= getSubscriptions(accountCode);
		 List<Subscription> subscriptionList=subscriptions.subscriptions;
		 String uuid=null;
		 if(subscriptionList!=null){
			for(Subscription subscription:subscriptionList){
				if(plan_code.equalsIgnoreCase(subscription.plan.planCode) ){
					uuid=subscription.uuid;
				}
			}
			 
		 }
		 
	  if(uuid!=null){
		  
		    StringBuffer buffer=URLUtils.doPut(REACTIVATE_SUBSCRIPTION_uuid.replace(":uuid", uuid),null,REQUEST_HEADERS,"");
		    System.out.println(buffer);
		 if(buffer.toString().contains("<errors>")){return errors(buffer);}
		 else if(buffer.toString().contains("<error>")){return error(buffer);}
		 else{
			 
			    JAXBContext context = JAXBContext.newInstance(Subscription.class);
				context.createUnmarshaller();
				javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
				Subscription addedsubscription =  (Subscription) unmarshaller.unmarshal(new StringReader(buffer.toString()));
				return addedsubscription;
		 }
		  
	  }
		   
			
		    return subscriptions;
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
}
 

public static Object  terminateSubscription(String accountCode,String plan_code,String refund_type){
	 
	 
	 try{
		 Subscriptions subscriptions= getSubscriptions(accountCode);
		 List<Subscription> subscriptionList=subscriptions.subscriptions;
		 String uuid=null;
		 if(subscriptionList!=null){
			for(Subscription subscription:subscriptionList){
				if(plan_code.equalsIgnoreCase(subscription.plan.planCode) ){
					uuid=subscription.uuid;
				}
			}
			 
		 }
		 
	  if(uuid!=null){
		  Properties data=new Properties();
		  data.put("refund",refund_type);
		    StringBuffer buffer=URLUtils.doPut(TERMINATE_SUBSCRIPTION_uuid.replace(":uuid", uuid),data,REQUEST_HEADERS,"");
		    System.out.println(buffer);
		 if(buffer.toString().contains("<errors>")){return errors(buffer);}
		 else if(buffer.toString().contains("<error>")){return error(buffer);}
		 else{
			 
			    JAXBContext context = JAXBContext.newInstance(Subscriptions.class);
				context.createUnmarshaller();
				javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
				Subscription addedsubscription =  (Subscription) unmarshaller.unmarshal(new StringReader(buffer.toString()));
				return addedsubscription;
		 }
		  
	  }
		   
			
		    return subscriptions;
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
}
public static Subscriptions  terminateSubscriptions(String accountCode,String refund_type){
	 Subscriptions subscriptions=null;
	 
	 try{
		 subscriptions= getSubscriptions(accountCode);
		 List<Subscription> subscriptionList=subscriptions.subscriptions;
		 if(subscriptionList!=null){
			for(Subscription subscription:subscriptionList){
				if(!"expired".equalsIgnoreCase(subscription.state)){
					 
						  Properties data=new Properties();
						  data.put("refund",refund_type);
						  StringBuffer buffer=URLUtils.doPut(TERMINATE_SUBSCRIPTION_uuid.replace(":uuid", subscription.uuid),data,REQUEST_HEADERS,"");
                          if(buffer.toString().contains("<subscription>")){ 
							    JAXBContext context = JAXBContext.newInstance(Subscription.class);
								context.createUnmarshaller();
								javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
								Subscription expiredSubscription =  (Subscription) unmarshaller.unmarshal(new StringReader(buffer.toString()));  
								subscription=expiredSubscription;
				            			}
                          }
			 
		              }
		 
		 }
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return subscriptions;
}
public static Object updateBillingInfo(String accont_code,
		String first_name,
		String last_name,
		String number,
		String verification_value,
		String month,
		String year
		){
 
StringBuffer xml=new StringBuffer(""
  +"<billing_info>"
  +"<first_name>Verena</first_name>"
  +"<last_name>Example</last_name>"
  +"<number>4111111111111111</number>"
  +"<verification_value>123</verification_value>"
  +"<month>11</month>"
  +"<year>2015</year>"
  +"</billing_info>");
	
	 try{
			StringBuffer buffer=URLUtils.doPost(UPDATE_BILLING_INFO_account_code.replace(":account_code", "3019"), null, REQUEST_HEADERS, xml);
			System.out.println(buffer);
			 if(buffer.toString().contains("<errors>")){return errors(buffer);}
			 else if(buffer.toString().contains("<error>")){return error(buffer);}
			 else if(buffer.toString().contains("<billing_info")) {
			JAXBContext context = JAXBContext.newInstance(BillingInfo.class);
			context.createUnmarshaller();
			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
			BillingInfo billingInfo=  (BillingInfo) unmarshaller.unmarshal(new StringReader(buffer.toString()));
		    return billingInfo;}
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
	
 }

public static Object updateFullBillingInfo(String accont_code,
		String first_name,
		String last_name,
		String number,
		String verification_value,
		String month,
		String year,
		String address1,
		String address2,
		String city,
		String state,
		String zip,
		String cart_type,
		String country,
		String ip_address
		){
 
StringBuffer xml=new StringBuffer(""
  +"<billing_info>"
  +"<first_name>"+first_name+"</first_name>"
  +"<last_name>"+last_name+"</last_name>"
  +"<number>"+number+"</number>"
  +"<verification_value>"+verification_value+"</verification_value>"
  +"<month>"+month+"</month>"
  +"<year>"+year+"</year>"
  +"<address1>"+address1+"</address1>"//not below fields are not mendatory 
  +"<address2>"+address2+"</address2>"
  +"<city>"+city+"</city>"
  +"<state>"+state+"</state>"
  +"<zip>"+zip+"</zip>"
 // +"<card_type>"+cart_type+"</card_type>"
  +"<country>"+country+"</country>"
  +"<ip_address>"+ip_address+"</ip_address>"
  +"</billing_info>");
	
	 try{
			StringBuffer buffer=URLUtils.doPost(UPDATE_BILLING_INFO_account_code.replace(":account_code", accont_code), null, REQUEST_HEADERS, xml);
			System.out.println(buffer);
			
			 if(buffer.toString().contains("<errors>")){return errors(buffer);}
			 else if(buffer.toString().contains("<error>")){return error(buffer);}
			 else if(buffer.toString().contains("<billing_info")){
			
			
			JAXBContext context = JAXBContext.newInstance(BillingInfo.class);
			context.createUnmarshaller();
			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
			BillingInfo billingInfo=  (BillingInfo) unmarshaller.unmarshal(new StringReader(buffer.toString()));
		    return billingInfo;
		    }
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
	
 }
public static Object  clearBillingInformation(String accountCode){
	 
	 
	 
		 try{
 
		  Properties data=new Properties();
	 
		    StringBuffer buffer=URLUtils.doDelete(CLEAR_BILLING_INFO_account_code.replace(":account_code", accountCode),data,REQUEST_HEADERS,"");
		    System.out.println(buffer);
		 if(buffer.toString().contains("<errors>")){return errors(buffer);}
		 else if(buffer.toString().contains("<error>")){return error(buffer);}
		 else if(buffer.toString().contains("<billing_info")){
			 
			    JAXBContext context = JAXBContext.newInstance(Subscriptions.class);
				context.createUnmarshaller();
				javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
				Subscription addedsubscription =  (Subscription) unmarshaller.unmarshal(new StringReader(buffer.toString()));
				return addedsubscription;
		 }
		  
      
		 }catch(Exception e){e.printStackTrace();} 
			
		    
		 
	 return null;
}

private static Errors errors(StringBuffer errorBuff) throws JAXBException{
	
	    
	    JAXBContext context = JAXBContext.newInstance(Errors.class);
		context.createUnmarshaller();
		javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
	 
	    Errors error =  (Errors) unmarshaller.unmarshal(new StringReader(errorBuff.toString()));
	    
	    try{
	    	JAXBContext attributecontext= JAXBContext.newInstance(com.recurly.v2.Messages.class);
	    	attributecontext.createUnmarshaller();
			javax.xml.bind.Unmarshaller attributeMarshller = attributecontext.createUnmarshaller();
			com.recurly.v2.Messages messages=  (com.recurly.v2.Messages) attributeMarshller.unmarshal(new StringReader(errorBuff.toString()));
			error.errorMessage=messages.errorMessage;
	    }catch(Exception e){e.printStackTrace();}
	    
	    return error;
}
private static Error error(StringBuffer errorBuff) throws JAXBException{
	
    System.out.println(errorBuff);
    JAXBContext context = JAXBContext.newInstance(Error.class);
	context.createUnmarshaller();
	javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
 
    Error error =  (Error) unmarshaller.unmarshal(new StringReader(errorBuff.toString()));
    
    return error;
}

public  static Transaction getTransaction(String transactionId){
	 
	 
	 try{

			StringBuffer buffer=URLUtils.doGet(LOOKUP_TRANSACTION_transaction_id.replace(":transaction_id", transactionId), null, REQUEST_HEADERS);
			JAXBContext context = JAXBContext.newInstance(Transaction.class);
			context.createUnmarshaller();
			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
			Transaction transaction =  (com.recurly.v2.Transaction) unmarshaller.unmarshal(new StringReader(buffer.toString()));
		    return transaction;
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
}
public  static Transactions getTransactions(String accountCode){
	 
	 
	 try{

			StringBuffer buffer=URLUtils.doGet(LIST_ACCOUNT_TRANSACTIONS_account_code.replace(":account_code", accountCode), null, REQUEST_HEADERS);
			JAXBContext context = JAXBContext.newInstance(Transactions.class);
			context.createUnmarshaller();
			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
			com.recurly.v2.Transactions transactions =  (com.recurly.v2.Transactions) unmarshaller.unmarshal(new StringReader(buffer.toString()));
		    return transactions;
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
}
public  static Object createTransaction(
		 String account_code,
		 int amount_in_cents,
		 String currency
		){
	 
	StringBuffer requestXML=new StringBuffer("" +
	 		"<transaction>"
	 +"<amount_in_cents>"+amount_in_cents+"</amount_in_cents>"
	 +"<currency>"+currency+"</currency>"
	  +"<account>"
	   +" <account_code>"+account_code+"</account_code>"
	 +"</account>"
	+"</transaction>"
	 );
	 try{
			StringBuffer buffer=URLUtils.doPost(CREATE_TRANSACTION, null, REQUEST_HEADERS, requestXML);
			
			
			 if(buffer.toString().contains("<errors>")){return errors(buffer);}
			 else if(buffer.toString().contains("<error>")){return error(buffer);}
			 else{
			JAXBContext context = JAXBContext.newInstance(Transaction.class);
			context.createUnmarshaller();
			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
			Transaction transaction =  (Transaction) unmarshaller.unmarshal(new StringReader(buffer.toString()));
		    return transaction;}
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
 }
 
public  static Object createTransaction(
		 String account_code,
		 Integer amount_in_cents,
		 String currency,
		 String first_name,
		 String last_name,
		 String number,
		 String verification_value,
		 Integer month,
		 Integer year
		){
	 
	StringBuffer requestXML=new StringBuffer("" +
	 		"<transaction>"
	 +"<amount_in_cents>"+amount_in_cents+"</amount_in_cents>"
	 +"<currency>"+currency+"</currency>"
	  +"<account>"
	   +" <account_code>"+account_code+"</account_code>"
	   +"<billing_info>"
	   +"<first_name>"+first_name+"</first_name>"
	    +"<last_name>"+last_name+"</last_name>"
	    +"<number>"+number+"</number>"
	    +"<verification_value>"+verification_value+"</verification_value>"
	    +"<month>"+month+"</month>"
	    +"<year>"+year+"</year>"
	  +"</billing_info>"
	 +"</account>"
	+"</transaction>"
	 );
 
	 try{
			StringBuffer buffer=URLUtils.doPost(CREATE_TRANSACTION, null, REQUEST_HEADERS, requestXML);
			
			
			 if(buffer.toString().contains("<errors>")){return errors(buffer);}
			 else if(buffer.toString().contains("<error>")){return error(buffer);}
			 else{
			JAXBContext context = JAXBContext.newInstance(Transaction.class);
			context.createUnmarshaller();
			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
			Transaction addedsubscription =  (Transaction) unmarshaller.unmarshal(new StringReader(buffer.toString()));
		    return addedsubscription;}
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
}

public  static Object createAccount(String account_code,String email,String first_name,String last_name)
{
	StringBuffer requestXML=new StringBuffer("" +
	  "<account>"
	  +"<account_code>"+account_code+"</account_code>"
	  +"<email>"+email+"</email>"
	  +"<first_name>"+first_name+"</first_name>"
	  +"<last_name>"+last_name+"</last_name>"
	+"</account>");
	
	 try{
			StringBuffer buffer=URLUtils.doPost(CREATE_ACCOUNT, null, REQUEST_HEADERS, requestXML);
			
			
			 if(buffer.toString().contains("<errors>")){return errors(buffer);}
			 else if(buffer.toString().contains("<error>")){return error(buffer);}
			 else{
			JAXBContext context = JAXBContext.newInstance(Account.class);
			context.createUnmarshaller();
			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
			Account account =  (Account) unmarshaller.unmarshal(new StringReader(buffer.toString()));
		    return account;
		    }
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
 }

public  static Object findAccount(String account_code)
{
 
	 try{
			StringBuffer buffer=URLUtils.doGet(GET_ACCOUNT_account_code.replace(":account_code", account_code),null, REQUEST_HEADERS);
			
			
			 if(buffer.toString().contains("<errors>")){return errors(buffer);}
			 else if(buffer.toString().contains("<error>")){return error(buffer);}
			 else{
			JAXBContext context = JAXBContext.newInstance(Account.class);
			context.createUnmarshaller();
			javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
			Account account =  (Account) unmarshaller.unmarshal(new StringReader(buffer.toString()));
		    return account;
		    }
		}catch(Exception e2){
			e2.printStackTrace();
		
		}
	 return null;
 }
 
}
